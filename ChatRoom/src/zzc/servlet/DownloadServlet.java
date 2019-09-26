package zzc.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String path = request.getServletContext().getRealPath(".");
		String filename = "record.txt";
		response.addHeader("content-type", "application/octet-stream");
		response.addHeader("content-disposition", "attachment;filename="+filename);
		
		ServletOutputStream out = response.getOutputStream();
		InputStream in = new FileInputStream(path+"/RES/"+filename);
		byte[] bytes = new byte[100];
		int i = -1;
		while((i=in.read(bytes))!=-1) {
			out.write(bytes,0,i);
		}
		in.close();
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
