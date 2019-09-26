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
@MultipartConfig(location="E:\\�ܳ���\\Tomcat\\apache-tomcat-8.5.38\\wtpwebapps\\UserPhoto")
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
		
		String registerday = String.valueOf(year)+"��"+month+"��"+day+"��";
		String name = request.getParameter("uname");
		String password = request.getParameter("upwd");
		Part photo = request.getPart("uphoto");
		
		/*
		 *	��װ��javabean������
		 */
		u.setUid(id);
		u.setUname(name);
		u.setUpassword(password);
		u.setRegisterday(registerday);
		
		/*
		 *	�������ݲ����� 
		 */
		targetAddOp = userservice.addUser(u);
		
		/*
		 *	��ȡ�ļ����Ͳ����� 
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
				out.print("<title>ͼƬ��ʽ����ҳ��</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<h3>ͼƬ��ʽ���������ԣ�</h3><br/>");
				out.print("<a href='http://localhost:8888/ChatRoom/UserRegisterPage.html'>����ע��ҳ��</a>");
				out.print("</body>");
				out.print("</html>");
			}
		}
		
		if(targetAddOp) {
			
			out.print("<!doctype html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<meta charset='utf-8'>");
			out.print("<title>�ɹ�ҳ��</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h3>ע��ɹ�</h3><br/>");
			out.print("����idֵΪ:"+id+"<br/>");
			out.print("<a href='http://localhost:8888/ChatRoom/MainPage.html'>���ص�¼ҳ��</a>");
			out.print("</body>");
			out.print("</html>");
			
		}else {
			out.print("<!doctype html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<meta charset='utf-8'>");
			out.print("<title>ʧ��ҳ��</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h3>ע��ʧ��,�û������Ѿ����ڣ�������û�������</h3><br/>");
			out.print("<a href='http://localhost:8888/ChatRoom/MainPage.html'>���ص�¼ҳ��</a>");
			out.print("</body>");
			out.print("</html>");
			return;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
