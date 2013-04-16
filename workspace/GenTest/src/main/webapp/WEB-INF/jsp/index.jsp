<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/widget" prefix="widget"%>
<!DOCTYPE html>
<html>
<head>
<title>funreco</title>
<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<widget:header />
        <div>
            <form action="/solve">
                <div>
                    <input type="text" name="a" value="${a}"/> X^2 + 
                    <input type="text" name="b" value="${b}"/> X + 
                    <input type="text" name="c" value="${c}"/>
                </div>
                <button class="btn btn-primary" type="submit" style="margin-top: 20px">Solve</button>
            </form>
        </div>
        <div id="solution">
            <c:if test="${not empty sol1}">
                ${sol1} - ${sol2}
            </c:if>
            <c:if test="${not empty sol0}">
                ${sol0}
            </c:if>
        </div>
</body>
</html>



