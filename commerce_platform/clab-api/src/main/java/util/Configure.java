package util;

public class Configure {
	public static final String WX_PlACE_ORDER="https://api.mch.weixin.qq.com/pay/unifiedorder";//微信统一下单接口
	
	public static final String NOTIFY_URL="/wxpay/notify"; //微信支付回调地址
	
	public static final String APP_ID="";//微信公众号appid
	
	public static final String APP_SECRET=""; //微信公众号密钥
	
	public static final String WX_GET_CODE_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APP_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect";//微信获取codeurl
	
	public static final String WX_GET_ACCESS_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APP_ID&secret=APP_SECRET&code=USE_CODE&grant_type=authorization_code";//获取用户openid地址
	
	public static final String WX_GET_USER_INFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPEN_ID&lang=zh_CN";
	
	public static final String KEY="";//支付密钥
	
	public static final String PAY_ID="";//商户id
	
	public static final String redis_admin_photo_randm_code="REDIS_ADMIN_PHOTO_RANDM_CODE";//图片验证码前缀
	
	public static final String email_randm_code="EMAIL_RANDM_CODE";//邮箱验证码前缀
	
	public static final String upload_progress="UPLOAD_PROGRESS";//上传进度查询前缀
	
	
	 
}
