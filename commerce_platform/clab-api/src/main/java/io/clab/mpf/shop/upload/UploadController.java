package io.clab.mpf.shop.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import util.JsonResponse;
import constant.SystemCode;
import io.clab.mpf.shop.controller.AbstractController;


/**上传文件controller*/
@RequestMapping("/upload")
@Controller
public class UploadController extends AbstractController{
	/**图片上传
	 * 目录结构：项目名/商户名/模块/年月日/
	 * 返回结构：一致
	 * */
	@ResponseBody
	@RequestMapping(value = "/uploadimg/{project}/{module}", method = RequestMethod.POST)
	public JsonResponse<String> uploadImg(HttpServletRequest request,HttpSession session,
			@PathVariable String project,@PathVariable String module) throws Exception {
		String param=request.getParameter("param");
		JsonResponse<String> jr=new JsonResponse<String>(SystemCode.NULL_ARGUMENT,"上传文件无法获取");
		MultipartHttpServletRequest r=(MultipartHttpServletRequest)request;
		MultipartFile mFile=null;
		if(StringUtils.isEmpty(param)){
			mFile=r.getFile("file");
		}else{
			mFile=r.getFile(param);
		}
		String OriginalfileName=mFile.getOriginalFilename();
		String fileFix = OriginalfileName.substring(OriginalfileName.lastIndexOf(".")+1);
		if(mFile!=null){
			 if(mFile.getSize()>5242880){
				//如果文件大于5M就不给上传
				jr=new JsonResponse(SystemCode.INNER_ERROR,"文件大小不能超过5M");
				return jr;
			}else{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
				String month=sdf.format(new Date());
				String fileDirPath=PathConstant.REMOTE_IMG+"/"+project+"/"+module+"/"+month;
				File file=new File(fileDirPath);
				if(!file.exists()){
					file.mkdirs();
				}
				String fileName=saveFile(mFile.getBytes(),fileFix,fileDirPath);
				//上传完后删除上次文件
				String preImg=request.getParameter("preImg");
				if(StringUtils.isNotEmpty(preImg)){
					removeFile(preImg);
				}
				jr=new JsonResponse(SystemCode.SUCCESS, fileDirPath+"/"+fileName);
				logger.info(request.getRemoteAddr()+"上传图片："+fileName+"成功，图片大小为："+mFile.getSize());
			}
		}
		return jr;
	}
	public String saveFile(byte[] content, String fileFix,String path)
			throws IOException {
		File fileDir=new File(path);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		String saveFileName=System.currentTimeMillis()+"."+fileFix;
		FileOutputStream fo=new FileOutputStream(new File(path)+"/"+saveFileName);
		try {
			fo.write(content);
		} catch (Exception e) {
			throw new IOException(e);
		}finally{
			fo.flush();
			fo.close();
		}
		return saveFileName;
	}
	@RequestMapping("/removeFile")
	@ResponseBody
	public  JsonResponse<String> remove(String preImg) throws IOException {
		removeFile(preImg);
		return new JsonResponse<String>(1,"删除成功");
	}
	public  void removeFile(String filename) throws IOException {
		File file=new File(filename);
		if(file.exists()){
			file.delete();
		}
	}
	/**
	 * 下载
	 * @param path
	 * @param response
	 * @return
	 */
	@RequestMapping("/downLoad")
	public HttpServletResponse download(String preImg,String fileName, HttpServletResponse response) {
        try {
        	  // perImg是指欲下载的文件的路径。
        	//preImg=preImg.replaceAll("file","pic");
    		File file=new File(preImg);
            // 取得文件名。
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(preImg));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" +fileName);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
	/**
	 * 
	 * @param preImg
	 * @param response
	 * @return 批量下载
	 *  perImg 文件路径 以','隔开，fileName zip包的名字
	 */
	@RequestMapping("/zipDownLoad")
	public HttpServletResponse zipDownLoad(String preImg, HttpServletResponse response) {
        try {
        	String strs[]=preImg.split(",");
        	File[] file1 = new File[strs.length];
        	long length=0;
	   		 for(int i=0;i<file1.length;i++){
	   			file1[i]=new File(strs[i]);
	   			length=length+file1[i].length();
	   		 }
	   		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			String month=sdf.format(new Date());
        	//fileName = URLEncoder.encode(fileName,"UTF-8");
	        String strZipName = month+".zip";
	    	response.addHeader("Content-Disposition", "attachment;filename=" +strZipName);
            //response.addHeader("Content-Length", ""+length);
            response.setContentType("application/octet-stream");
    		 ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
    		
    		for(int i=0;i<file1.length;i++) {
    			FileInputStream fis = new FileInputStream(file1[i]);
    			byte[] buffer = new byte[fis.available()];
    			out.putNextEntry(new ZipEntry(file1[i].getName()));
    			int len;
    			while((len = fis.read(buffer))>0) {
    				out.write(buffer,0,len);
    			}
    			out.closeEntry();
    			fis.close();
    		}
    		out.flush();
    		out.close();
	      }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
