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
 <div id="tfon">
  <table  width="1000px" height="100%" align="center" id="leagues_table">

     <tr>
        <td width="300px"> <div id="teams">
   <!----------- LEague name, pt and etc. --------------->
    
    <table  width="100%" height="200px" align="center" > 
  
         <tr>   
            <td><div id="leagueHeader"> ${leaguename} </div>  </td>   
         </tr> 
         
          <tr>   
            <td><div id="info">Start date: <fmt:formatDate value="${league.startDate}" pattern="yyyy-MM-dd" /> </div>  </td>   
         </tr> 
         
          <tr>   
            <td><div id="info">Prime time: <fmt:formatDate value="${league.primetime}" pattern="HH:mm" /> </div>  </td>   
         </tr> 
        <c:if test="${!empty pageContext.request.userPrincipal}">
            <c:if test="${flag == false}">
         <tr>
             <td> <div id="teams"><input type="button" value="Create Team" onclick="createTeam('${leaguename}')" id="createButton"/> </div></td>
         </tr>
        </c:if>
        
        <c:if test="${flag == true}">
         <tr>
             <td> <div id="teams"><input type="button" value="Leave League" onclick="leaveLeague('${leaguename}')" id="createButton"/> </div></td>
         </tr>
        </c:if>
       </c:if>
    </table>
    
   <!------------- end league stats ------------->
        
    
      
  <!----------------------- BODY --------------------------------->
      <td width="700px"> 
      

      <!--------------------------TEAMS ------------------------  -->
         
            <div id="teams"> 
            <br>
            <table width="100%"  cellspacing="0" cellpadding="0" >
   <thead>
        <tr>
        <td></td>
            <td width="35%"><div id="theader" >Player</div></td> 
        <td width="65%"> <div id="theader">Team</div></td> 
        
          
        </tr>
  </thead>
  <tbody>
  <c:forEach var="team" items="${leagueTeams}" varStatus="status">
     <c:choose>
        
       
           <c:when test="${team.getUuser().getUsername() == pageContext.request.userPrincipal.getName() }">  
     

              <tr onclick="">
                   <td id="isNew" width="5%"><div id="tdata">${status.index+1}</div></td>
                   <td id="isNew" width="30%"><div id="tdata">${team.getUuser().getUsername()}</div></td>
         
                   <td id="isNew" width="65%"><div id="tdata">${team.name} </div></td>
                   
                <!--    <td id="isNew" width="65%"> <img  src="data:image/jpeg;base64,${team.getByteArrayString()}" /></td> -->
                   
               </tr>
            
            </c:when>
            
            <c:when test="${team.getUuser().getUsername() != pageContext.request.userPrincipal.getName() }">  
     

              <tr onclick="">
                   <td  width="5%"><div id="tdata">${status.index+1}</div></td>
                   <td  width="30%"><div id="tdata">${team.getUuser().getUsername()}</div></td>
         
                   <td width="65%"><div id="tdata">${team.name} </div></td>
                 
               </tr>
            
            </c:when>
          
        </c:choose>
  </c:forEach>
  
  
   </tbody>
 </table>          
             
</div>  
 <input type="button" onclick="startLeague('${leaguename}')"/>
<!--------------------------TEAMS END------------------------->         

      </td>
<!----------------------- BODY END--------------------------------->  
   
  </tr>
 
</table>      
    </div> 
    
   
        
   
                
              <!--footer end -->
</body>


</html>
