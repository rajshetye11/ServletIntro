package com.bridgelabz.logindemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		description = "Login Servlet",
		urlPatterns = { "/LoginServlet" },
		initParams = {
				@WebInitParam (name="user", value="Raaj"),
				@WebInitParam (name="password", value="Raaj@123")
		}
)
public class Login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		
		if(userID.equals(user) && password.equals(pwd) && user.matches("^[A-Z][a-z]{2,}") && pwd.matches("[[A-Z]{1,}[a-z]{1,}[0-9]{1,}[@$]{1}]{8,}")){
			request.setAttribute("user", user);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red> Username or Password is wrong</font>");
			rd.include(request, response);
		}
	}
	
}
