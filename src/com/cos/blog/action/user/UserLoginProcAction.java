package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.dao.UserDao;
import com.cos.blog.model.User;
import com.cos.blog.utils.Script;

public class UserLoginProcAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("UserLoginProcAction : "+request.getParameter("remember"));
		
		User user = User.builder()
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.build();

		System.out.println(user);
		UserDao userDao = UserDao.getInstance();
		User userEntity = userDao.로그인(user);
		
		// 로그인 성공
		if(userEntity != null) {
			
			if(request.getParameter("remember") != null) {
				response.setHeader("Set-Cookie", "remember="+userEntity.getUsername());
			}else {
				Cookie cookie = new Cookie("remember", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			 
			HttpSession session = request.getSession();
			session.setAttribute("principal", userEntity);			
			Script.href(response, "/", "로그인 성공");
		}else {
			Script.back(response, "아이디 혹은 비밀번호가 틀렸습니다.");
		}

	}
}
