
<%@page import="com.it.impldao.musicAnalysisImpl"%>
<%@page import="com.it.dao.musicAnalysis"%>
<%
   String name = request.getParameter("name");
   musicAnalysisImpl m = new musicAnalysis();
   JSONArray array = m.QuerySong(name,50);
   out.print(array);
    %>
<%@page import="org.json.JSONArray"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.it.wyy.WyySpider"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  
