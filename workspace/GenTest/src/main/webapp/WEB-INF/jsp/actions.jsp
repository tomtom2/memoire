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
		<c:if test="${not empty flashMessage}">
			<div class="alert">${flashMessage}</div>
		</c:if>

		
		<div class="row" style="margin-top: 100px">
			<div class="span12">
				<legend>
					All actions 
				</legend>
				<ul id="actions">
					<c:forEach var="action" items="${actions}">

						<li>
							<p>${action}</p>
						</li>
					</c:forEach>

				</ul>
			</div>
		</div>
		
	</div>
</body>
</html>



