<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Web Template - Web 2.0!</title>
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/resources/script.js"></script>

</head>

<body>


 <table width="100%" height="100%" align="center">
      <tr height="50px"><div id="profilename"><c:out value="${pageContext.request.userPrincipal.name}" /></div></tr>
      <tr>
      <td><a href="<c:url value="${pageContext.request.contextPath}/j_spring_security_logout" />" > LOG OUT</a></td>

      </tr>
       </table>

</body>
</html>