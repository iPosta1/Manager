<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Prime time rivalry</title>
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery.datetimepicker.css"/>

<script src="${pageContext.request.contextPath}/resources/script.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<script src="https://www.google.com/recaptcha/api.js" async defer></script>

</head>

<div id="topPanel">
<table cellspacing="0" cellpadding="0" width="1000px" height="50px" align="center">

  <tr>
   <td class="tpd" width="200px"> 
         <a href="${pageContext.request.contextPath}"><div id="small_logo"></div></a>
     </td> 
     <td class="tpd" width="120px"> 
         <a class="tp" href="${pageContext.request.contextPath}">HOME</a>
     </td> 
     <td class="tpd" width="120px"> 
          <a class="tp" href="${pageContext.request.contextPath}/leagues">LEAGUES</a>
     </td>
      <td  width="460px"></td>
    
  </tr>     
</table>

</div>



<body>

</body>
</html>