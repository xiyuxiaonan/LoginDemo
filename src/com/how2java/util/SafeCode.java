package com.how2java.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SafeCode extends HttpServlet{
	private static final long serialVersionUID = 1626673439350527994L;
	public SafeCode() {
		super();
	}
	public void destroy() {
		super.destroy();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg") ;
        response.setHeader("Cache-Control", "no-cache") ;
        int width = 60,height = 20 ;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB) ;
        Graphics g = image.getGraphics() ;
        Random random = new Random() ;
        String s = "" ;
        for(int i=0;i<4;i++){
        s += random.nextInt(10) ;
        }
        HttpSession session = request.getSession() ;
        session.setAttribute("code",s) ;//code的记录了验证码的数值
        Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)) ;
        String a = null ;
        Font font = new Font(a,Font.ITALIC,18) ;
        g.setColor(color) ;
        g.setFont(font);
        g.drawString(s,10,height-5) ;
        g.dispose() ;
        ServletOutputStream output = response.getOutputStream() ;
        ImageIO.write(image,"JPEG",output) ;
        output.flush(); 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request,response) ;
	}
	public void init() throws ServletException {
	}
	
}
