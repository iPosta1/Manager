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

<%@include  file="top.jsp" %>


       
       <table width="500px" height="200px" align="center"> 
           <tr>
               <td width ="500px">
             
<!--------------------------CREATE TEAM ------------------------  -->

 <div id="createTeam">
  <div id="createTeamHeader"> Create team in ${leaguename}</div>
         <form:form method="post" action="add" commandName="team" id="createTeamForm" >
            <table width="100%">
              
             
              <tr height="50px"> 
                    <td id="text1" width="50%"> Team name (max 25 symbols)  </td>
                    <td id="info" width="50%"><form:input path="name" id="teamname"/> 
                    </td>
              </tr>
                        <tr>
                        <td></td>
                        <td>
                           <div id="warnings">
                            <label id="createteam_error">Incorrect name</label></div>
                       </td>
                       
                       </tr>
            </table>
          
               <div id="sumbit_btn_l">
               <table width="30px" height="40px" align="center">
                 <tr>
                   <td>
                       <input type="button" onclick="validateTeamForm('${leaguename}')" value="SUBMIT" class="sumbit_style" />
                       
                        </td>
                       
                 </tr>
               </table>
             
              </div>
         </form:form>
         
   
    </div> 
<!--------------------------CREATE TEAM END------------------------->            
               </td>
       
           </tr>
       </table>



</body>


</html>
