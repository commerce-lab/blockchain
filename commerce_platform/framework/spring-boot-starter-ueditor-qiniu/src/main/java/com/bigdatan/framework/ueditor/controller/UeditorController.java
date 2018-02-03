package com.bigdatan.framework.ueditor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigdatan.framework.ueditor.ActionEnter;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ueditor")
public class UeditorController {

	@Autowired 
	private ActionEnter actionEnter;

	@RequestMapping("/upload")
	public String exe(HttpServletRequest request){
		return actionEnter.exec(request);
	}
	
}
