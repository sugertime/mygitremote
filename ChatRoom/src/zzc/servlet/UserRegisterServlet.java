package zzc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import zzc.beans.User;
import zzc.service.impl.UserServiceImpl;


@WebServlet("/UserRegisterServlet")
@MultipartConfig(location="E:\\总程序\\Tomcat\\apache-tomcat-8.5.38\\wtpwebapps\\UserPhoto")
public class UserRegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private UserServiceImpl userservice = new UserServiceImpl();
    private static int NUMBER = 1000000;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		boolean targetAddOp;
		PrintWriter out = response.getWriter();
		User u = new User();
		
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();
		
		String id = String.valueOf(year)+NUMBER;
		NUMBER++;
		
		String registerday = String.valueOf(year)+"年"+month+"月"+day+"日";
		String name = request.getParameter("uname");
		String password = request.getParameter("upwd");
		Part photo = request.getPart("uphoto");
		
		/*
		 *	组装成javabean操作。
		 */
		u.setUid(id);
		u.setUname(name);
		u.setUpassword(password);
		u.setRegisterday(registerday);
		
		/*
		 *	插入数据操作。 
		 */
		targetAddOp = userservice.addUser(u);
		
		/*
		 *	获取文件类型操作。 
		 */
		String h = photo.getHeader("content-disposition");
		int temp = h.lastIndexOf('.');
		
		if(photo!=null&&temp>=0) {
			
			String filetype = h.substring(temp,h.length()-1);
			System.out.println(filetype.toLowerCase());
			System.out.println(".jpg".equals(filetype.toLowerCase()));
			
			if(".jpg".equals(filetype.toLowerCase())||".png".equals(filetype.toLowerCase())) {
				
				String filename = name + filetype;
				photo.write(filename);
			
			}else {
				out.print("<!doctype html>");
				out.print("<html>");
				out.print("<head>");
				out.print("<meta charset='utf-8'>");
				out.print("<title>图片格式错误页面</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<h3>图片格式错误，请重试！</h3><br/>");
				out.print("<a href='http://localhost:8888/ChatRoom/UserRegisterPage.html'>返回注册页面</a>");
				out.print("</body>");
				out.print("</html>");
			}
		}
		
		if(targetAddOp) {
			
			out.print("<!doctype html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<meta charset='utf-8'>");
			out.print("<title>成功页面</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h3>注册成功</h3><br/>");
			out.print("您的id值为:"+id+"<br/>");
			out.print("<a href='http://localhost:8888/ChatRoom/MainPage.html'>返回登录页面</a>");
			out.print("</body>");
			out.print("</html>");
			
		}else {
			out.print("<!doctype html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<meta charset='utf-8'>");
			out.print("<title>失败页面</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h3>注册失败,用户可能已经存在，请更换用户名重试</h3><br/>");
			out.print("<a href='http://localhost:8888/ChatRoom/MainPage.html'>返回登录页面</a>");
			out.print("</body>");
			out.print("</html>");
			return;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
