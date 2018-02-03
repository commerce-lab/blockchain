package com.bigdatan.framework.ueditor.hunter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bigdatan.framework.ueditor.define.AppInfo;
import com.bigdatan.framework.ueditor.define.BaseState;
import com.bigdatan.framework.ueditor.define.MultiState;
import com.bigdatan.framework.ueditor.define.State;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

public class FileManager {


	private int count = 0;
	
	public static String accessKey;
	public static String secretKey;
	public static String baseUrl;
	public static String bucket;
	public static String uploadDirPrefix;
	
	 
	public FileManager ( Map<String, Object> conf ) {
//
//		this.rootPath = (String)conf.get( "rootPath" );
//		this.dir = this.rootPath + (String)conf.get( "dir" );
//		this.allowFiles = this.getAllowFiles( conf.get("allowFiles") );
		this.count = (Integer)conf.get( "count" );
		
	}
	
	public State listFile ( int index,String marker ) {
		Auth auth = Auth.create(accessKey, secretKey);
		Configuration cfg = new Configuration();
		BucketManager bucketManager = new BucketManager(auth,cfg);
		FileListing fileListing;
		State state = null;
		try {
			fileListing = bucketManager.listFiles(bucket,null,marker!=null&&!"".equals(marker)?marker:null,count,null);
			List<String> fileList = new ArrayList<String>();
			for (FileInfo fileInfo : fileListing.items) {
				fileList.add(fileInfo.key);
			}
			state = this.getState( fileList.toArray(new String[fileList.size()]) );
			state.putInfo( "start", index );
			state.putInfo( "isLast", fileListing.isEOF()+"" );
			state.putInfo( "marker", fileListing.marker );
			state.putInfo( "total", Integer.MAX_VALUE );
		} catch (QiniuException e) {
			e.printStackTrace();
			state = new BaseState( false, AppInfo.NOT_EXIST );
		}

		
		return state;
		
	}
	

	private State getState ( String[] files ) {
		
		MultiState state = new MultiState( true );
		BaseState fileState = null;
		
		
		for ( String url : files ) {
			if ( url == null ) {
				break;
			}
			fileState = new BaseState( true );
			fileState.putInfo( "url", baseUrl + "/" + url );
			state.addState( fileState );
		}
		
		return state;
		
	}

	
}
