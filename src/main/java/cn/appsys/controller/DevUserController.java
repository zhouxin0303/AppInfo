package cn.appsys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appsys.mapper.DevUserMapper;
import cn.appsys.pojo.DevUser;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping(value="/dev")
public class DevUserController {
@Autowired
private DevUserMapper devUserMapper;
@RequestMapping(value="login",method=RequestMethod.GET)
public String login(){
	return "devlogin";
}
@RequestMapping(value="dologin",method=RequestMethod.POST)
public String dologin(DevUser devUser,HttpServletRequest request,HttpSession session){
	String devCode=request.getParameter("devCode");
	String devPassword=request.getParameter("devPassword");
	devUser=devUserMapper.login(devCode, devPassword);
	session.setAttribute(Constants.DEV_SESSION, devUser);
	if(devUser!=null){
		return "developer/mian";
	}else{
		session.setAttribute("error", "用户名或密码不正确");
		return "devlogin";
	}
}
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.removeAttribute(Constants.DEV_SESSION);
		return "devlogin";
	}
}
