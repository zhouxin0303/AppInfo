package cn.appsys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appsys.mapper.BackendUserMapper;
import cn.appsys.pojo.BackendUser;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping(value="/manager")
public class BackendUserController {
@Autowired
private BackendUserMapper backendUserMapper;
@RequestMapping(value="login",method=RequestMethod.GET)
public String  login(){
	return "baclogin";
	
}
@RequestMapping(value="dologin",method=RequestMethod.POST)
public String dologin(BackendUser backendUser,HttpServletRequest request,HttpSession session){
	String userCode=request.getParameter("userCode");
	String userPassword=request.getParameter("userPassword");
	backendUser=backendUserMapper.login(userCode, userPassword);
	session.setAttribute(Constants.bak_SESSION, backendUser);
	if(backendUser!=null){
		return "backend/mian";
	}else{
		request.setAttribute("error", "用户名或密码不正确");
		return "baclogin";
	}
}

@RequestMapping("logout")
public String logout(HttpSession session){
	session.removeAttribute(Constants.bak_SESSION);
	return "baclogin";
}
}
