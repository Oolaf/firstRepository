package com.kh.websocket.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/bcDelUser.do")
public class BroadcastDeleteUserServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public BroadcastDeleteUserServlet() { }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String user = request.getParameter("chat_id");
      
      ServletContext application = request.getServletContext();
      
      Set<String> userList = (HashSet<String>) application.getAttribute("userList");
      
      if(userList.isEmpty()) {
         
         userList = new HashSet<String>();
         
      }
      
      userList.remove(user);
      
      application.setAttribute("userList", userList);
      
      response.setContentType("application/json; charset=UTF-8");
      new Gson().toJson(userList, response.getWriter());
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
