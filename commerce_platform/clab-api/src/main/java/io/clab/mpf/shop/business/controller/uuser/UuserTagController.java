package io.clab.mpf.shop.business.controller.uuser;

import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.entity.uuser.AssetsCount;
import io.clab.mpf.shop.business.entity.uuser.DataItem;
import io.clab.mpf.shop.business.entity.uuser.DiTag;
import io.clab.mpf.shop.business.entity.uuser.DiUuser;
import io.clab.mpf.shop.business.entity.uuser.ShopConditiontCount;
import io.clab.mpf.shop.business.entity.uuser.ShopNewData;
import io.clab.mpf.shop.business.entity.uuser.Tag;
import io.clab.mpf.shop.business.entity.uuser.UploadUserTagExcelState;
import io.clab.mpf.shop.business.entity.uuser.UserAssetsParameter;
import io.clab.mpf.shop.business.entity.uuser.Uuser;
import io.clab.mpf.shop.business.entity.uuser.UuserTag;
import io.clab.mpf.shop.business.service.uuser.IDataItemService;
import io.clab.mpf.shop.business.service.uuser.IDiTagService;
import io.clab.mpf.shop.business.service.uuser.IDiUuserService;
import io.clab.mpf.shop.business.service.uuser.ITagService;
import io.clab.mpf.shop.business.service.uuser.IUuserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.baomidou.mybatisplus.plugins.Page;

import util.Configure;
import util.JsonResponse;
import util.RedisUtil;
import util.SessionUtil;
import constant.SystemCode;


@Controller
@RequestMapping("/uuserTag")
@Api(value = "用户标签", description = "用户标签")
public class UuserTagController {
	
	@Resource
	private IDataItemService dataItemService;
	
	@Resource
	private IUuserService uuserService;
	
	@Resource
	private IDiUuserService iDiUuserService;
	
	@Resource
	private ITagService tagService;
	
	@Resource
	private IDiTagService diTagService;

	@ResponseBody
	@PostMapping("/uploadUserTagExcel")
	@ApiOperation("上传用户标签(仅支持excel)")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "randomCode", value = "由前端生成的一个唯一码，用来查询上传进度。（必传）", required = true,paramType="query"),
		})
	private JsonResponse<DataItem> uploadUserTagExcel(HttpServletRequest request,DataItem dataItem,String randomCode) {
		
		JsonResponse<DataItem> result = new JsonResponse<DataItem>();
		
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
                //文件名
                String fileName = file.getOriginalFilename();
                
                try {
                    InputStream is = file.getInputStream();
                    Workbook hssfWorkbook = null;
                    if (fileName.endsWith("xlsx")){
                       hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
                    }else if (fileName.endsWith("xls")){
                        hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
                    }
                    
                    UuserTag uuserTag = null;
                    List<UuserTag> list = new ArrayList<UuserTag>();
                    List<Tag> tagTemps = new ArrayList<Tag>();
                    
                    Integer rightNum = 0;
                    Integer errorNum = 0;
                    
                    // 循环工作表Sheet
                    for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
                        Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                        if (hssfSheet == null) {
                            continue;
                        }
                        
                        //总行数
                        Integer maxNum = hssfSheet.getLastRowNum();
                        
                        //保存数据项
                        if(maxNum > 0 && (dataItem.getDiId() == null || dataItem.getDiId() <= 0)){
                        	Merchants merchants = SessionUtil.getMerchantsUser(request);
                        	if(merchants != null && merchants.getMerchantsId() != null && merchants.getMerchantsId() > 0){
                        		dataItem.setMerchantsId(merchants.getMerchantsId());  //商家ID
                            	dataItem.setDiSource(1);  //数据项来源(1:上传,2:购买)
                            	dataItem.setDiName(fileName.substring(0,fileName.lastIndexOf(".")));  //数据项名称
                            	dataItem.setUploadFileName(fileName);  //上传文件名
                            	Integer diId = dataItemService.insertSelectiveEntity(dataItem);
                            	dataItem.setDiId(diId);
                        	}
                        }
                        
                        if(dataItem.getDiId() == null || dataItem.getDiId() <= 0){
                        	// 失败
                            result.setRes(SystemCode.FAILURE);
                            result.setResult("上传失败");  
                            return result;
                        }
                        
                        // 循环行Row
                        for (int rowNum = 1; rowNum <= maxNum; rowNum++) {
                        	try {
                        		 Row hssfRow = hssfSheet.getRow(rowNum);
                                 if (hssfRow != null) {
                                 	uuserTag = new UuserTag();
                                     Cell name = hssfRow.getCell(0);
                                     Cell phone = hssfRow.getCell(1);
                                     Cell sex = hssfRow.getCell(2);
                                     Cell age = hssfRow.getCell(3);
                                     Cell birthday = hssfRow.getCell(4);
                                     
                                     if(name == null || phone == null){
                                    	 errorNum++;
                                    	 continue;
                                     }

                                     //存对象
                                     Uuser uuser = new Uuser();
                                     
                                     uuser.setName(name.getStringCellValue());
                                     
                                     //手机号码
                                     String phoneStr = phone.getStringCellValue().trim();
                                     uuser.setPhone(Long.valueOf(phoneStr));

                                     try {
                                    	 if(sex != null){
                                    		//性别（非必传）
                                             String sexStr = sex.getStringCellValue().trim();
                                             if("男".equals(sexStr)){
                                           	  uuser.setSex(1);
                                             }
                                             if("女".equals(sexStr)){
                                           	  uuser.setSex(0);
                                             } 
                                    	 }
                                     	
 									} catch (Exception e) {
 										
 									}
                                    try {
                                    	if(age != null){
                                    		//年龄（非必传）
                                        	 String ageStr = age.getStringCellValue().trim();
                                        	uuser.setAge( Integer.valueOf(ageStr));
                                    	}
                                     	
 									} catch (Exception e) {
 										
 									}
                                    if(birthday != null){
                                    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" ); 
                                    	uuser.setBirthday(sdf.parse(birthday.getStringCellValue().trim()) );
                                    }

                                    uuserTag.setUuser(uuser);
                                    
                                    List<Tag> tags = new ArrayList<Tag>();
                                    for(int i=5;i<15;i++){
                                    	if(hssfRow.getCell(i) != null){
                                    		String tagStr = hssfRow.getCell(i).getStringCellValue().trim().replaceAll(" ", "");
                                        	if(tagStr != null && !"".equals(tagStr)){
                                        		Tag tag = new Tag();
                                        		tag.setTagName(tagStr);
                                        		tags.add(tag);
                                        	}
                                    	}
                                    }
                                    uuserTag.setTags(tags);
	                                 list.add(uuserTag);
	                                 rightNum ++ ;
                                 }else{
                                	 errorNum++;
                                 }
							} catch (Exception e) {
								errorNum++;
								e.printStackTrace();
							}
                        	
                        	//当达到500条，写入数据库
                        	if(rowNum % 500 == 0){
                        		saveUuser(list, dataItem, tagTemps, rowNum, maxNum,rightNum,errorNum, randomCode);
                        		list.clear();
                        	}
                        }
                        
                        saveUuser(list, dataItem, tagTemps, maxNum, maxNum,rightNum,errorNum, randomCode);
                		list.clear();
                    }
                    
                    dataItem.setRightNum(rightNum);
                    dataItem.setErrorNum(errorNum);
                    dataItemService.updateByPrimaryKey(dataItem);
                    
				} catch (Exception e) {
					e.printStackTrace();
					// 失败
				     result.setRes(SystemCode.FAILURE);
				     result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));  
				     return result;
				}
            }  
        } 
        
     // 成功
     result.setRes(SystemCode.SUCCESS);
     result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));
     result.setObj(dataItem);
     return result;
	}
	
	@ResponseBody
	@PostMapping("/getUploadUserTagExcelState")
	@ApiOperation("查询上传用户标签情况")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "randomCode", value = "由前端生成的一个唯一码，用来查询上传进度。（必传）", required = true,paramType="query"),
		})
	private JsonResponse<UploadUserTagExcelState> getUploadUserTagExcelState(HttpServletRequest request,String randomCode) {
		JsonResponse<UploadUserTagExcelState> result = new JsonResponse<UploadUserTagExcelState>();
		
		String percent = RedisUtil.getInstance().strings().get(Configure.upload_progress+"_RANDOMCODE"+randomCode);
		String rightNum = RedisUtil.getInstance().strings().get(Configure.upload_progress+"_RIGHTNUM"+randomCode);
		String errorNum = RedisUtil.getInstance().strings().get(Configure.upload_progress+"_ERRORNUM"+randomCode);
		UploadUserTagExcelState uutes = new UploadUserTagExcelState();
		if(percent != null && rightNum != null && errorNum != null && !"".equals(percent) && !"".equals(rightNum) && !"".equals(errorNum)){
			uutes.setPercent(Float.parseFloat(percent));
			uutes.setRightNum(Integer.valueOf(rightNum));
			uutes.setErrorNum(Integer.valueOf(errorNum));
		}
		
		result.setObj(uutes);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
		
	}
	
	@ResponseBody
	@PostMapping("/assetsCount")
	@ApiOperation("商家资产统计")
	private JsonResponse<AssetsCount> assetsCount(HttpServletRequest request) {
		JsonResponse<AssetsCount> result = new JsonResponse<AssetsCount>();
		
		Long merchantsId = 1l;
		AssetsCount data = dataItemService.selectAssetsStatisticalByMerchantsId(merchantsId);
		result.setObj(data);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
		
	}
	
	@ResponseBody
	@PostMapping("/assetsList")
	@ApiOperation("商家资产列表")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "查询第几页", required = true,paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "返回条数", required = true,paramType="query"),
		})
	private JsonResponse<Page<DataItem>> assetsList(HttpServletRequest request,Integer pageNow,Integer pageSize) {
		JsonResponse<Page<DataItem>> result = new JsonResponse<Page<DataItem>>();
		
		Long merchantsId = 1l;
		
		Page<DataItem> page = dataItemService.selectByMerchantsIdList(pageNow, pageSize, merchantsId);
		result.setObj(page);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
		
	}
	
	@ResponseBody
	@PostMapping("/selectLikeTagNameList")
	@ApiOperation("根据标签名模糊查询（分页）")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "查询第几页", required = true,paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "返回条数", required = true,paramType="query"),
		@ApiImplicitParam(name = "tagName", value = "标签名称", required = true,paramType="query"),
		})
	private JsonResponse<Page<Tag>> selectLikeTagNameList(HttpServletRequest request,Integer pageNow,Integer pageSize,String tagName) {
		JsonResponse<Page<Tag>> result = new JsonResponse<Page<Tag>>();

		Page<Tag> page = tagService.selectLikeTagNameList(pageNow, pageSize, tagName);
		result.setObj(page);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
		
	}
	
	@ResponseBody
	@PostMapping("/selectByCategoryList")
	@ApiOperation("根据商家类目获取标签")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "查询第几页", required = true,paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "返回条数", required = true,paramType="query"),
		@ApiImplicitParam(name = "categoryId", value = "类目ID", required = true,paramType="query"),
		})
	private JsonResponse<Page<Tag>> selectByCategoryList(HttpServletRequest request,Integer pageNow,Integer pageSize,Integer categoryId) {
		JsonResponse<Page<Tag>> result = new JsonResponse<Page<Tag>>();

		Page<Tag> page = tagService.selectByCategoryList(pageNow, pageSize, categoryId);
		result.setObj(page);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
	}
	
	@ResponseBody
	@PostMapping("/selectAllCount")
	@ApiOperation("上传用户总数")
	private JsonResponse<Integer> selectAllCount(HttpServletRequest request) {
		JsonResponse<Integer> result = new JsonResponse<Integer>();
		Integer num = uuserService.selectAllCount();
		result.setObj(num);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
	}
	
	@ResponseBody
	@PostMapping("/selectConditionCount")
	@ApiOperation("筛选用户统计")
	public JsonResponse<ShopConditiontCount> selectConditionCount(){
		JsonResponse<ShopConditiontCount> result = new JsonResponse<ShopConditiontCount>();
		ShopConditiontCount shopConditiontCount = uuserService.selectConditionCount();
		result.setObj(shopConditiontCount);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
	}
	
	@ResponseBody
	@PostMapping("/selectShopNewData")
	@ApiOperation("获取最新上传数据信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "查询第几页", required = true,paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "返回条数", required = true,paramType="query"),
		})
	public JsonResponse<Page<ShopNewData>> selectShopNewData(int pageNow, int pageSize){
		JsonResponse<Page<ShopNewData>> result = new JsonResponse<Page<ShopNewData>>();
		Page<ShopNewData> page = uuserService.selectShopNewData(pageNow, pageSize);
		result.setObj(page);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
	}
	
	@ResponseBody
	@PostMapping("/selectConditionTwo")
	@ApiOperation("筛选用户随机获取用户信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "查询第几页", required = true,paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "返回条数", required = true,paramType="query"),
		@ApiImplicitParam(name = "categoryId", value = "类目ID",paramType="query"),
		@ApiImplicitParam(name = "tags", value = "标签集",paramType="query"),
		@ApiImplicitParam(name = "filterHaveUser", value = "过滤已有用户",paramType="query"),
		@ApiImplicitParam(name = "filterHaveUserTag", value = "过滤已有标签",paramType="query"),
		})
	public JsonResponse<Page<Uuser>> selectConditionTwo(HttpServletRequest request,int pageNow, int pageSize,DataItem dataItem,Integer categoryId,List<Tag> tags,Integer filterHaveUser,Integer filterHaveUserTag){
		JsonResponse<Page<Uuser>> result = new JsonResponse<Page<Uuser>>();
		Page<Uuser> page = uuserService.selectConditionTwo(pageNow, pageSize, dataItem, 1L, categoryId, tags, filterHaveUser, filterHaveUserTag);
		result.setObj(page);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
	}
	
	
	
	@ResponseBody
	@PostMapping("/selectUserAssetsList")
	@ApiOperation("查询用户资产")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "查询第几页", required = true,paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "返回条数", required = true,paramType="query"),
		})
	public JsonResponse<Page<Uuser>> selectUserAssetsList(int pageNow, int pageSize,UserAssetsParameter userAssetsParameter){
		JsonResponse<Page<Uuser>> result = new JsonResponse<Page<Uuser>>();
		Page<Uuser> page = uuserService.selectUserAssetsList(pageNow, pageSize, userAssetsParameter);
		result.setObj(page);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
	}

	
	private Boolean saveUuser(List<UuserTag> list,DataItem dataItem,List<Tag> tagTemps,Integer rowNum,Integer maxNum,Integer rightNum,Integer errorNum,String randomCode){
		try {
			//保存用户基本信息
			for(UuserTag ut : list ){
				//查询用户是否存在
				Uuser uuserOld = uuserService.selectByPhone(ut.getUuser().getPhone());
				if(uuserOld != null && uuserOld.getUuserId() > 0){
					ut.getUuser().setUuserId(uuserOld.getUuserId());
					uuserService.updateByPrimaryKey(ut.getUuser());
				}else{
					uuserService.insertSelectiveEntity(ut.getUuser());
				}
				
				//保存数据项与用户关系
	    		DiUuser diUuser = new DiUuser();
	    		diUuser.setDiId(dataItem.getDiId());
	    		diUuser.setUuserId(ut.getUuser().getUuserId());
	    		diUuser.setMerchantsId(dataItem.getMerchantsId());
	    		diUuser.setDiSource(1); //数据项来源(1:上传,2:购买)
	    		diUuser.setPayPoints(dataItem.getSendPoints());
	    		diUuser.setPayState(0);  //支付状态（0：未支付，1：已支付）
	    		iDiUuserService.insertSelectiveEntity(diUuser);
	    		
	    		//标签保存
	    		for(Tag tag : ut.getTags()){
	    			//缓存是否存在
	    			for(Tag t : tagTemps){
	    				if(tag.getTagName().equals(t.getTagName())){
	    					tag.setTagId(t.getTagId());
	    					break;
	    				}
	    			}
	    			
	    			if(tag.getTagId() == null || tag.getTagId() <= 0){
	    				Tag tagData = tagService.selectByTagName(tag.getTagName());
	    				if(tagData != null && tagData.getTagId() > 0){
	    					tag.setTagId(tagData.getTagId());
	    					tagTemps.add(tagData);
	    				}
	    			}
	    			
	    			if(tag.getTagId() == null || tag.getTagId() <= 0){
	    				tagService.insertSelectiveEntity(tag);
	    				//tag.setTagId(tagId);
	    				tagTemps.add(tag);
	    			}
	    			
	    			//
	    			if(tag.getTagId() != null || tag.getTagId() > 0){
	    				DiTag diTag = new DiTag();
	    				diTag.setDiId(dataItem.getDiId());
	    				diTag.setTagId(tag.getTagId());
	    				diTag.setUuserId(ut.getUuser().getUuserId());
	    				diTag.setMerchantsId(dataItem.getMerchantsId());
	    				diTagService.insertSelectiveEntity(diTag);
	    			}	
	    		}
			}
			//上传进度到redis
			float ff = (float)rowNum / maxNum;
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数 
			String s = df.format(ff);//返回的是String类型 
			//存redis
			RedisUtil.getInstance().strings().setEx(Configure.upload_progress+"_RANDOMCODE"+randomCode, 3 * 60 * 60, s);
			RedisUtil.getInstance().strings().setEx(Configure.upload_progress+"_RIGHTNUM"+randomCode, 3 * 60 * 60, rightNum+"");
			RedisUtil.getInstance().strings().setEx(Configure.upload_progress+"_ERRORNUM"+randomCode, 3 * 60 * 60, errorNum+"");
				
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
