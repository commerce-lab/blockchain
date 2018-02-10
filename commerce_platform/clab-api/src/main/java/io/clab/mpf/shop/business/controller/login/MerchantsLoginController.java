package io.clab.mpf.shop.business.controller.login;

import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.service.merchants.IMerchantsService;
import io.clab.mpf.shop.entity.admin.Admin;
import io.clab.mpf.shop.entity.module.Module;
import io.clab.mpf.shop.entity.module.ModuleVo;
import io.clab.mpf.shop.service.admin.IAdminService;
import io.clab.mpf.shop.service.module.IModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.Configure;
import util.JsonResponse;
import util.RedisUtil;
import util.SessionUtil;
import util.Encryp.MD5Util;
import constant.SystemCode;

/**
 *
 *
 *  商家用户登陆退出
 *
 */
@Controller
@RequestMapping("/merchantsLogin")
@Api(value = "商家用户登陆退出", description = "登陆退出api")
public class MerchantsLoginController {
	
	@Resource
	private IAdminService adminService;
	
	@Resource
	private IModuleService moduleService;
	
	@Resource
	private IMerchantsService merchantsService;
	
	
	/**
	 * @param request
	 * @param admin 
	 *  登录
	 * @return adminName,password
	 */
	@ResponseBody
	//@RequestMapping(value ="/toLogin",method = RequestMethod.POST)
	@PostMapping("/toLogin")
	@ApiOperation(value = "登录", notes = "商家用户登陆")
	private JsonResponse<Merchants> toLogin(HttpServletRequest request,Merchants merchants) {
		JsonResponse<Merchants> result = new JsonResponse<Merchants>(SystemCode.FAILURE);
		
		Merchants model = merchantsService.longin(merchants);
		
		if (model != null) {
			// 登录成功
			String password = MD5Util.encoder(merchants.getPassword(),model.getRand());
			if (password.equals(model.getPassword())) {
				
				//首先判断用户状态是否为关闭
				if(model.getState() == 0) {
					result.setRes(SystemCode.ON_AUDIT);
					result.setResult(SystemCode.GetErrorDesc(SystemCode.ON_AUDIT));
					return result;
				}else if(model.getState() == 2){
					result.setRes(SystemCode.AUDIT_NO_PASS);
					result.setResult(SystemCode.GetErrorDesc(SystemCode.AUDIT_NO_PASS));
					return result;
				}

				SessionUtil.setMerchantsUser(request, merchants);
				// 登陆成功
				result.setRes(SystemCode.SUCCESS);
				result.setObj(model);
				result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));
				return result;
			}
		}
		// 登录失败
		result.setRes(SystemCode.NO_OBJ_ERROR_PASS);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_OBJ_ERROR_PASS));
		return result;
	}

	@ResponseBody
	@PostMapping("/loginOut")
	@ApiOperation(value = "退出登录", notes = "商家用户退出登陆")
	public JsonResponse<String> loginOut(HttpServletRequest request) {
		SessionUtil.setMerchantsUser(request, null);
		JsonResponse<String> result = new JsonResponse<String>();
		request.getSession().invalidate();
		result.setRes(SystemCode.SUCCESS);
		return result;
	}

	@ResponseBody
	@PostMapping("/isLogin")
	@ApiOperation(value = "判断是否登录", notes = "判断商家用户是否登录")
	public JsonResponse<String> isLogin(HttpServletRequest request) {
		JsonResponse<String> result = new JsonResponse<String>(SystemCode.FAILURE);
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if (merchants != null) {
			result.setRes(SystemCode.SUCCESS);
		}
		return result;
	}
	
	@ResponseBody
	@PostMapping("/resetPassword")
	//@ApiOperation(value = "重置密码", notes = "重置密码")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "email", value = "邮箱", required = true,paramType="query"),
		@ApiImplicitParam(name = "newPassword", value = "新密码", required = true,paramType="query"),
		})
	public JsonResponse<String> resetPassword(HttpServletRequest request,String email,String newPassword) {
		JsonResponse<String> result = new JsonResponse<String>(SystemCode.FAILURE);
		Admin admin = SessionUtil.getAdminUser(request);
		if (admin != null) {
			result.setRes(SystemCode.SUCCESS);
		}
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @return 获取管理员信息
	 */
	@ResponseBody
	@PostMapping("/getAdminInfo")
	//@ApiOperation(value = "获取管理员信息", notes = "获取管理员信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "adminId", value = "账号ID", required = true,paramType="query"),
		})
	public JsonResponse<Admin> getAdminInfo(Integer adminId, HttpServletRequest request) {
		JsonResponse<Admin> result = new JsonResponse<Admin>();
		Admin admin = adminService.selectByPrimaryKey(adminId);
		result.setRes(SystemCode.SUCCESS);
		result.setObj(admin);
		return result;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("/queryAllValidModule")
	//@ApiOperation(value = "获取角色菜单", notes = "获取角色菜单")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "roleId", value = "角色ID", required = true,paramType="query"),
		})
	public JsonResponse<List<ModuleVo>> queryAllValidModule(HttpServletRequest request, Integer roleId) {
		JsonResponse<List<ModuleVo>> result = new JsonResponse<List<ModuleVo>>();
		List<Module> modules = moduleService.queryAllValidModule();
		List<ModuleVo> moduleVos = new ArrayList<ModuleVo>();
		ModuleVo moduleVo = null;
		if (null != modules) {
			// 查询用户拥有的高级支付方式
			List<Module> roleModules = moduleService.queryRoleModule(roleId);

			if (null != roleModules) {
				for (Module module : modules) {
					moduleVo = new ModuleVo();
					moduleVo.setCreateTime(module.getCreateTime());
					moduleVo.setDescription(module.getDescription());
					moduleVo.setModuleId(module.getModuleId());
					moduleVo.setName(module.getName());
					moduleVo.setParentId(module.getParentId());
					moduleVo.setState(module.getState());
					moduleVo.setModuleId(module.getModuleId());
					moduleVo.setUpdateTime(module.getUpdateTime());
					moduleVo.setUrl(module.getUrl());
					moduleVo.setIsOwnered((byte) 2);

					for (Module roleModule : roleModules) {
						if (module.getModuleId() == roleModule.getModuleId()) {
							moduleVo.setIsOwnered((byte) 1);
						}
					}
					moduleVos.add(moduleVo);
				}
			}
		}
		result.setObj(moduleVos);
		result.setRes(SystemCode.SUCCESS);
		return result;
	}
	

	//@PostMapping("/createCode")
	//@RequestMapping("/createCode")
	@GetMapping("/createCode")
	@ApiOperation(value = "生成验证码", notes = "生成验证码")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //禁止缓存

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        // 指定生成的响应是图片

        response.setContentType("image/jpeg");
        int width = 80;        //指定验证码的宽度

        int height = 25;        //指定验证码的高度

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();    //获取Graphics类的对象

        Random random = new Random();        //实例化一个Random对象

        Font mFont = new Font("宋体", Font.ITALIC, 26);    //通过Font构造字体

        g.setColor(new Color(230, 244, 254)); //设置颜色

        g.fillRect(0, 0, width, height);    //绘制验证码背景

        g.setFont(mFont);       //设置字体

        g.setColor(new Color(0xFF0001));     //设置文字的颜色为红色

	
	/*生成随机字符串sRand*/
        String sRand = "";
        int itmp = 0;
        for (int i = 0; i < 4; i++) {
            if (random.nextInt(2) == 1) {
                boolean cuto = false;
                while (!cuto) {
                    itmp = random.nextInt(26) + 65;   //生成A~Z(去掉O,I)

                    cuto = true;
                    if (79 == itmp || 73 == itmp) {
                        cuto = false;
                    }
                }

            } else {
                //itmp=random.nextInt(10)+48;

                itmp = random.nextInt(7) + 50;//0~9 48--57(去掉0,1这两个与字母相混:50--57)

            }
            char ctmp = (char) itmp;
            sRand += String.valueOf(ctmp);
            //drawString(String str,int x,int y) str - 要绘制的 string。x - x 坐标。y - y 坐标。

            g.drawString(String.valueOf(ctmp), width / 6 * i + 5, height - 4);

        }

    	//存redis
		RedisUtil.getInstance().sets().sadd(Configure.redis_admin_photo_randm_code+sRand, sRand);
		RedisUtil.getInstance().keys().expire(Configure.redis_admin_photo_randm_code+sRand, 10 * 60);

        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

}
