package io.clab.mpf.shop.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.clab.mpf.shop.common.util.FileUploadUtil;
import io.clab.mpf.shop.common.util.PropertiesUtil;
import io.clab.mpf.shop.common.vto.FileUploadVO;
import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/fileIo")
@Api(value = "文件上传读取", description = "文件上传读取")
public class FileIoController {

private String filePath = PropertiesUtil.getPropertiesVal("common.properties", "file.upload.location");
	
	@ResponseBody
	@PostMapping("/uploadFile")
	@ApiOperation(value = "文件上传(只支持单文件)", notes = "图片格式限制：gif，jpg，jpeg，png，bmp；语音格式限制：mp3")
	public JsonResponse<FileUploadVO> uploadFile(HttpServletRequest request){
		
		JsonResponse<FileUploadVO> result = new JsonResponse<FileUploadVO>(SystemCode.SUCCESS);;
		
		//获取解析器  

        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断是否是文件  

        if(resolver.isMultipart(request)){  
            //进行转换  

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);  
            //获取所有文件名称  

            Iterator<String> it = multiRequest.getFileNames();  
            while(it.hasNext()){
                //根据文件名称取文件  

                MultipartFile file = multiRequest.getFile(it.next());
				try {
					FileUploadVO dto = FileUploadUtil.doUpload(file,filePath);
					if (dto == null) {
					}else{
						if(StringUtils.isNotEmpty(dto.getError())){
						
						}else{
							result.setObj(dto);
							
							return result;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }  
        } 
        
     // 失败
     result.setRes(SystemCode.FAILURE);
     result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));  
     return result;
	}
	
	/**
	 * IO流读取图片 by:long
	 * @return
	 */
	@GetMapping("/ioReadImage/**")
	@ApiOperation(value = "IO流读取图片", notes = "图片格式限制：gif，jpg，jpeg，png，bmp")
	public String ioReadImage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String servletPath = request.getServletPath();
		String imageName = servletPath.replaceFirst("/fileIo/ioReadImage/", "");
		
		
		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			String imageExt = imageName.substring(imageName.lastIndexOf(".") + 1).toLowerCase();
			//获取文件存放路径
			String path = filePath + "/" + imageName;
			ips = new FileInputStream(new File(path));
			response.setContentType("image/"+imageExt);
			out = response.getOutputStream();
			//读取文件流
			int len = 0;
			byte[] buffer = new byte[1024 * 10];
			while ((len = ips.read(buffer)) != -1){
				out.write(buffer,0,len);
			}
			
			out.flush();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			out.close();
			ips.close();
		}
		return null;
	}
}
