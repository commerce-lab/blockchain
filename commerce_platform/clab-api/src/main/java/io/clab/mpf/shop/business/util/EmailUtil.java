package io.clab.mpf.shop.business.util;

import io.clab.mpf.shop.common.util.PropertiesUtil;

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailUtil {

	private static final String HOST = PropertiesUtil.getPropertiesVal("common.properties", "email.host");
    private static final String PORT = PropertiesUtil.getPropertiesVal("common.properties", "email.port");
    private static final String EMAILFORM = PropertiesUtil.getPropertiesVal("common.properties", "email.email.from");
    private static final String PASSWORD = PropertiesUtil.getPropertiesVal("common.properties", "email.password");
    private static JavaMailSenderImpl mailSender = createMailSender();
    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(Integer.parseInt(PORT));
        sender.setUsername(EMAILFORM);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.smtp.auth", "true");

        p.setProperty("mail.smtp.port", PORT);
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.socketFactory.fallback", "false");
        p.setProperty("mail.smtp.socketFactory.port", PORT);
        
        
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param to 接受人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendHtmlMail(String to, String subject, String html) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // Cc: 抄送（可选）
        mimeMessage.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(EMAILFORM, "clab", "UTF-8"));
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(EMAILFORM, "系统名称");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }
    
    public static void main(String[] args) {
    	try {
			EmailUtil.sendHtmlMail("644198561@qq.com", "测试标题", "内容");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
