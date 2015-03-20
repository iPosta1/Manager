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
<script src="${pageContext.request.contextPath}/resources/jquery-1.11.2.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery.tablesorter.js" type="text/javascript"></script>
</head>
<body>

<form:form method="post" action="add" commandName="user" id="reg_form" onsubmit="validateForm(); return false;" >
            <table width="100%" height="100%">
              <tr height="50px"> 
                  <td width="35%"> USERNAME </td>
                  <td width="30%"><form:input path="username" id="usrname" /> </td>
                  <td width="35%"><div id="warnings">
                       <label id="username_error">Incorrect username</label>
                       <label id="userexists_error">User exists</label>
                 </div></td>
              </tr>
              
              <tr height="50px"> 
                  <td width="35%"> PASSWORD </td>
                  <td width="55%"><form:input path="password" id="pssword" type="password"/></td>
                  <td width="35%"><div id="warnings"><label id="password_error">Password too short</label></div></td>
              </tr>
              
              <tr height="50px">
                  <td width="35%"> REPEATE PASSWORD </td>
                  <td width="55%"><input id="rpssword" type="password"/></td>
                  <td width="35%"><div id="warnings"><label id="rpassword_error">Passwords don't match</label></div></td>
              </tr>
              
              <tr height="50px">
                  <td width="35%"> EMAIL ADDRESS </td>
                  <td width="55%"><form:input path="email" type="text" id="email" size ="20" /> </td>
                  <td width="35%"><div id="warnings"><label id="email_error">Incorrect email</label></div></td>
              </tr>
              
            </table>
            <!-- CAPTCHA -->
            <div class="g-recaptcha" data-sitekey="6Le6VgITAAAAAMu1UKCZaIhw9JqCUU9d4fOcls2r" ></div>
              
               <div id="sumbit_btn">
               <table width="30px" height="40px" align="center">
                 <tr>
                   <td>
                       <input type="submit" value="SUBMIT" class="sumbit_style" /> </td>
                 </tr>
               </table>
              </div>
         </form:form>

</body>
</html>