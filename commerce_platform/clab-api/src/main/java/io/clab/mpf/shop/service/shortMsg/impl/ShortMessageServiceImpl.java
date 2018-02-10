package io.clab.mpf.shop.service.shortMsg.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import util.AvoidSubmit;
import util.JsonResponse;
import util.SendMessage;
import constant.SystemCode;
import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.dao.shortMsg.IShortMessageDao;
import io.clab.mpf.shop.entity.shortMsg.ShortMessageModel;
import io.clab.mpf.shop.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.service.shortMsg.IShortMessageService;

@Service
public class ShortMessageServiceImpl extends BaseServiceImpl<ShortMessageModel> implements IShortMessageService {
	@Resource
	private IShortMessageDao shortMessageDao;
	private static Logger log = Logger.getLogger(ShortMessageServiceImpl.class);

	@Override
	protected IBaseDao<ShortMessageModel> getMapper() {
		return shortMessageDao;
	}

	@Override
	public ShortMessageModel getCodeByPhone(String phone) {
		return shortMessageDao.getCodeByPhone(phone);
	}

	/**
	 * 
	 * @param request
	 * @param phone
	 * @return 发送短息
	 */
	public JsonResponse<String> sendMessage(HttpServletRequest request, String phone) {
		JsonResponse<String> result = new JsonResponse<String>(SystemCode.FAILURE);
		if (!AvoidSubmit.invokeNum(request)) {
			// 请求通过
			int randCode = new Random().nextInt(888888) + 111111;
//				String str = "121";
				String str = SendMessage.regist(phone, randCode);
			if (!("".equals(str))) {
				// 短信下发成功
				ShortMessageModel messageModel = new ShortMessageModel();
				messageModel.setPhone(phone);
				messageModel.setCode(randCode);
				messageModel.setCreateTime(new Date());
				messageModel.setUpdateTime(new Date());
				try {
					shortMessageDao.insertSelective(messageModel);
					result.setRes(SystemCode.SUCCESS);
				} catch (Exception e) {
					log.error("短信验证码插入失败", e);
				}
			}
		} else {
			result.setRes(SystemCode.OFTEN);
		}
		return result;
	}

	/**
	 * 
	 * @param phone
	 * @param code
	 * @return 检验短信验证码
	 */
	public JsonResponse<String> checkCode(String phone, Integer code) {
		JsonResponse<String> result = new JsonResponse<String>(SystemCode.INNER_ERROR);
		ShortMessageModel model = shortMessageDao.getCodeByPhone(phone);
		if (model != null) {
			// 验证码正确
			if (model.getState() == 2) {
				// 该验证码已经验证过了 不可重复使用
				result.setRes(SystemCode.OBJ_EXISTS);
			} else {
				if (new Date().getTime() - model.getCreateTime().getTime() > 60 * 1000) {
					// 超过10分钟 已失效
					result.setRes(SystemCode.TIME_OUT);
				} else {
					// 判断用户输入的code和短信表里的code是否相同
					if (!(code.equals(model.getCode()))) {
						return result;
					}

					int id = model.getMessageId();
					model = new ShortMessageModel();
					model.setUpdateTime(new Date());
					model.setMessageId(id);
					model.setState((byte) 2);
					try {
						shortMessageDao.updateByPrimaryKeySelective(model);
					} catch (Exception e) {
						log.error("[更新验证码状态失败]", e);
					}
					result.setRes(SystemCode.SUCCESS);
				}
			}
		}
		return result;
	}

	private static String sendCode(String phone, int randCode) {

		String content = "欢迎加入安德互联,您的验证码是" + randCode + "(10分钟内输入有效)";
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://api.wxduanxin.com/?");
		// 向StringBuffer追加用户名
		sb.append("ac=send&uid=70309528");
		String pwd = toMD5("sms20151105pwd");
		// 向StringBuffer追加密码（密码采用MD5 32位 小写）
		sb.append("&pwd=" + pwd);
		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);
		// 向StringBuffer追加消息内容转URL标准码
		try {
			sb.append("&content=" + URLEncoder.encode(content, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			log.error("短信发送异常-编码异常", e1);
		}
		sb.append("&encode=utf8");
		// 创建url对象
		String inputline = "";
		URL url;
		try {
			url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// 返回发送结果
			inputline = in.readLine();
		} catch (Exception e) {
			log.error("短信发送异常", e);
		}
		return inputline;
	}

	private static String toMD5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			// 生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要。
			md.update(plainText.getBytes());
			// 通过执行诸如填充之类的最终操作完成哈希计算。
			byte b[] = md.digest();
			// 生成具体的md5密码到buf数组
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		sendCode("18757127055", 405786);
	}
}
