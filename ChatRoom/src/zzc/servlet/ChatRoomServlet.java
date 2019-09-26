package zzc.servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zzc.beans.User;

@WebServlet("/ChatRoomServlet")
public class ChatRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String path = request.getServletContext().getRealPath(".");
		FileWriter fw = new FileWriter(path+"/RES/record.txt",true);
		
		String userinputmes = request.getParameter("mes");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		System.out.println(user);
		System.out.println(userinputmes);
		//组合信息
		if(!"".equals(userinputmes)) {
			message = message + "用户id:" + user.getUid() + "\n" + userinputmes + "\n";
		}
		if(message.length()>500) {
			fw.write(message);
			message = "";
			out.write("聊天记录已保存");
		}
		fw.close();
		out.write(message);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
