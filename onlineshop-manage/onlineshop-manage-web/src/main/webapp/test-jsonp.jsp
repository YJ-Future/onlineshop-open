<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String callback = request.getParameter("callback");
	out.print(callback+"({\"abc\":\"123\"})");
%>
