package util;

import io.clab.mpf.shop.vo.common.FileUploadVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cenxi
 * @time 2015年6月16日 下午3:18:35
 */
public class FileUploadUtil {

	protected static final Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

	// 上传公共路径
	public static final String UPLOAD_PATH = "uploads";
	// 图片检查失败时，返回错误信息的key值
	public static final String ERROR_KEY = "error";
	// 成功上传图片后，返回的相对路径地址
	public static final String FILE_URL = "fileUrl";
	public static final String FILE_SIZE = "fileSize";

	public static final String NO_FILE = "上传文件不能为空";
	public static final String UNKNOWN = "未知错误";
	
	public static final String FILE_NAME = "fileName";
	
	// 图片上传文件夹
	public static final String IMAGE = "image";
	// 语音上传文件夹
	public static final String VOICE = "voice";
	
	// 上传的图片最大不超过5M，单位是以bytes
	public static long maxSize = 5 * 1024 * 1024;

	// 定义允许上传的文件扩展名
	private static HashMap<String, String> extMap = new HashMap<String, String>();
	static {
		extMap.put(IMAGE, "gif.jpg.jpeg.png.bmp");
		extMap.put(VOICE, "mp3");
	}

	private static final String DATE_PATTERN = "yyyyMMdd";
	private static final String TIME_PATTERN = "yyyyMMddHHmmss";

	public static SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
	public static SimpleDateFormat df = new SimpleDateFormat(TIME_PATTERN);
	
	//private static String BASE_PATH = PropertiesUtil.getPropertiesVal("commonBASE.properties", "uploads_file_url");
	
	
	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static FileUploadVO doUpload( MultipartFile file) throws Exception {

		String BASE_PATH = FileUploadUtil.class.getResource("/").getPath();
		
		if (file != null) {
			FileUploadVO dto = new FileUploadVO();

			String filePathName = file.getOriginalFilename();
			// 检查扩展名
			String fileExt = filePathName.substring(filePathName.lastIndexOf(".") + 1).toLowerCase();
			
			//获取文件路径
			String url = FileUploadUtil.class.getClassLoader().getResource("commonBASE.properties").getPath();
			
			
			// 文件保存目录URL
			String savePath = BASE_PATH + UPLOAD_PATH;

			File uploadDir = new File(UPLOAD_PATH);
			if (!uploadDir.isDirectory()) {
				uploadDir.mkdir();
			}
			if (!uploadDir.canWrite()) {
				dto.setError("上传文件没有写权限！");
				return dto;
			}
			
			String dirName = null;
			
			//识别文件类型
			for (Map.Entry<String, String> entry : extMap.entrySet()) {
				if(entry.getValue().indexOf(fileExt) > -1){
					dirName = entry.getKey();
				}
			}
			if (dirName == null) {
				dto.setError("上传文件扩展名是不允许的扩展名。");
				return dto;
			}

			// 创建文件夹

			if (StringUtils.isNotBlank(savePath) && !savePath.endsWith("/")) {
				savePath += "/";
			}
			savePath += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}

			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			File dest = new File(savePath, newFileName);
			try {
				
				byte[] data = file.getBytes();
				
				//创建输出流   
				FileOutputStream outStream = new FileOutputStream(dest);  
				//写入数据   
				outStream.write(data);  
				//关闭输出流   
				outStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("FileUploadUtil 上传文件不成功");
				
			}
			log.info("The file [" + filePathName
					+ "] had been upload to path [" + dest.getAbsolutePath()
					+ "] successfully.");
			System.out.println("The file [" + filePathName
					+ "] had been upload to path [" + dest.getAbsolutePath()
					+ "] successfully.");
			dto.setFileName(newFileName);
			
			savePath = savePath.replace(BASE_PATH, "");
			dto.setFileUrl(savePath + newFileName);
			return dto;
		}
		return null;
	}
	
	
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
	
}