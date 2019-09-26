package zzc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zzc.beans.User;
import zzc.service.IUserService;
import zzc.service.impl.UserServiceImpl;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private IUserService userservice = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("uid");
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		
		PrintWriter out = response.getWriter();
		User u = new User();
		
		u.setUid(id);
		u.setUname(name);
		u.setUpassword(pwd);
		
		HttpSession session = request.getSession();
		
		if(userservice.isExist(u)) {
			session.setAttribute("User", u);
			request.getRequestDispatcher("UserWelcomePage.jsp").forward(request,response);
		}else {
			out.print("<!doctype html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<meta charset='utf-8'>");
			out.print("<title>登录信息页面</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h3>登录失败，请确认您的用户名和密码正确。</h3><br/>");
			out.print("<a href='http://localhost:8888/ChatRoom/MainPage.html'>返回登录页面</a>");
			out.print("</body>");
			out.print("</html>");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
