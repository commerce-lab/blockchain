package io.clab.mpf.shop.business.controller.common;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.clab.mpf.shop.business.entity.common.Region;
import io.clab.mpf.shop.business.service.common.IRegionService;
import io.clab.mpf.shop.vo.common.FileUploadVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import constant.SystemCode;
import util.FileUploadUtil;
import util.JsonResponse;
import util.StringUtil;


@Controller
@RequestMapping("/common")
@Api(value = "公共API", description = "公共API")
public class CommonController {
	
	@Resource
	private IRegionService regionService;
	
	@ResponseBody
	@PostMapping("/uploadFile")
	@ApiOperation(value = "文件上传(只支持单文件)", notes = "图片格式限制：gif，jpg，jpeg，png，bmp；语音格式限制：mp3")
	public JsonResponse<FileUploadVO> uploadFile(HttpServletRequest request){
		
		JsonResponse<FileUploadVO> result = new JsonResponse<FileUploadVO>();
		
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
					FileUploadVO dto = FileUploadUtil.doUpload(file);
					if (dto == null) {
					}else{
						if(StringUtil.isNotNull(dto.getError())){
						
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
	
	@ResponseBody
	@PostMapping("/getRegionBycode")
	@ApiOperation("根据行政区划代码查询地区")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "regionCode", value = "行政区划代码(查询省默认传100000)", required = true,paramType="query"),
		})
	private JsonResponse<List<Region>> getRegionBycode(HttpServletRequest request,Integer regionCode) {
		JsonResponse<List<Region>> result = new JsonResponse<List<Region>>(SystemCode.SUCCESS);
		try {
			if(regionCode != null && regionCode > 0){
				List<Region> list = regionService.getRegionBycode(regionCode);
				result.setObj(list);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
	}
}
