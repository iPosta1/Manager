<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Prime time rivalry</title>
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/resources/script.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<script src="https://www.google.com/recaptcha/api.js" async defer></script>

</head>


<body>

<div id="indexTopTable">
 <table cellspacing="0" cellpadding="0"  width="1000px" height="800px" align="center">
   <tr>
      <td width="300px">
          <div id="logo">
            <table cellspacing="0" cellpadding="0"  width="90%" height="100px" align="right" id="main_info">
              <tr>
                  <td>Leagues: </td>
                  <td>  ${allLeagues.size()}</td>
              </tr>
              
                <tr>
                  <td>Players: </td>
                  <td>  ${usersCount}</td>
              </tr>
              
               <tr>
                  <td>Online: </td>
                  <td>  ${onlineCount}</td>
              </tr>
              <a href="<c:url value="${pageContext.request.contextPath}/j_spring_security_logout" />" > LOG OUT</a>
            </table>
          
         </div>
      </td>
      
      <td width=700px>
             <table cellspacing="0" cellpadding="0"  width="700px" height="200px" align="center">
                 <tr height="200px">
                 <c:if test="${empty pageContext.request.userPrincipal}">
                      <td width="350px">
                          <a href="#login"><div id="signin_button"></div></a>
                      </td >
                      
                      <td width="350px">
                           <a href="#register"><div id="register_button"></div></a>
                      </td>
                      </c:if>
                      <c:if test="${!empty pageContext.request.userPrincipal}">
                        <td width="700px">
                           <a href="#"> <div id="home_button"> ${pageContext.request.userPrincipal.name} </div></a>
                        </td >
                      </c:if>
                 </tr>
              </table>
              
              <table cellspacing="0" cellpadding="0"  width="700px" height="600px" align="center">
                 <tr height="600px">
                     <td width="300px">
                         <a href="leagues"> <div id="leagues_button"></div> </a>
                     </td>
                     
                      <td  width="400px">
                             <table cellspacing="0" cellpadding="0"  width="400px" height="300px" align="center">
                                  <tr height="300px">
                                      <td width="400px">
                                           <div id="top_players_button"></div>
                                      </td>
                                  </tr>
                             </table>
                             
                               <table cellspacing="0" cellpadding="0"  width="400px" height="300px" align="center">
                                  <tr height="300px">
                                      <td width="200px">
                                             <div id="rules_button"></div>
                                      </td>
                                      
                                       <td width="200px">
                                             <div id="contacts_button"></div>
                                      </td>
                                  </tr>
                             </table>
                     </td>
                 
                 </tr>
             </table>
      </td>
   </tr>
 </table>
</div>



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
    
        <a class="close" title="Close" href=""></a>
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



</body>
</html>