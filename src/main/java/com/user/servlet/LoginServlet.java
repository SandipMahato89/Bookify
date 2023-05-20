package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
import com.mysql.cj.Session;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
			HttpSession session = req.getSession();
			
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			if("sandip@gmail.com".equals(email) && "admin".equals(password)) {
				User us = new User();
				us.setName("Sandip");
				session.setAttribute("userobj", us);
				resp.sendRedirect("admin/home.jsp");
			}
			else {
				//first check weather correct user from db
				User us = dao.login(email, password);
				if(us!=null) {
					session.setAttribute("userobj", us);
					resp.sendRedirect("index.jsp");
				}
				else {
					session.setAttribute("failedMsg", "Invalid Credentials");
					resp.sendRedirect("login.jsp");
				}
				
				resp.sendRedirect("home.jsp"); 
			}
			
			//System.out.println(email+", "+password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
