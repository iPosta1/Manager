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

<body>


 <table width="100%" height="100%" align="center">
      <tr height="120px"> </tr>
      <tr>
      <td>  <a href="#login"> SIGN IN </a>  </td>
      <td>  <a href="#register"> REGISTER </a> </td>
      </tr>
       </table>
       
            
       <!-- registration form -->
        <a href="#register" class="overlay" id="register"></a>
        <div class="popup">
            <form:form method="post" action="add" commandName="user" id="reg_form" onsubmit="validateForm(); return false;" >
            <table width="100%" height="100%">
              <tr height="50px"> 
                  <td width="35%"> USERNAME </td>
                  <td width="30%"><form:input path="username" id="usrname" onkeyup="if(/[^a-aZ-Z\s]/.test(this.value))this.value=this.value.replace(/[^a-zA-Z\s]+/g,'')"/> </td>
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
    
        <a class="close" title="Close" href="#close"></a>
       </div>
        <!---------- END OF REGISTRATION------------ -->
        
        
        <!---------- SIGNING IN------------ -->
        <a href="#login" class="overlay" id="login"></a>
        <div class="popup">
        
          <c:url value="/j_spring_security_check" var="login" />
          <form action="${login}" method="post" >
        
           <table width="60%" height="100%" align="center">
   
            <div id="warnings"> <label id="login_error">Incorrect username or password</label></div>
                <tr> <td width="50%">
                         Username 
                     </td>
                     <td width="50%">
                          <input id="j_username" name="j_username" size="20" maxlength="30" type="text" />
                     </td>
               </tr>

               <tr> 
                     <td width="50%">
                        Password
                     </td>
                     <td width="50%">
                           <input id="j_password" name="j_password" size="20" maxlength="30" type="password" />
                     </td>
                </tr>           
           </table>

            <div id="sumbit_btn">
            <table width="30px" height="40px" align="center">
               <tr>
                  <td> <input type="submit" value="SIGN IN" class="sumbit_style" /> </td>
               </tr>
            </table>
            </div>
          </form>

         <a class="close" title="Close" href=""></a>
        </div>
 <!---------- END OF SIGNIN IN------------ -->
 
</body>
</html>