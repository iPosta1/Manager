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


  <table width="1000px" height="100%" align="center">
     <tr>
        
  <!----------------------- BODY --------------------------------->
  <td width="1000px" vertical-align="top">
     




   <!------------------------ ALL LEAGUES--------------------- -->
     <div id="iblock">
     <table width="100%" id="btable" >
   <thead>
        <tr>
            <td><div id="theader" >League</div></td> 
        <td> <div id="theader">Prime Time</div></td> 
           <td width="100px"><div id="theader">Players</div></td>
           <td><div id="theader">Start date</div></td>
           <td width="90px"><div id="theader">Started</div></td>
          
        </tr>
  </thead>
  <tbody>
     <c:forEach var="aleague" items="${allLeaguesAdmin}" >
     <c:choose>
     <c:when test="${aleague.started == 'y'}">  
            <tr onclick="intoTheLeague('${aleague.leagueName}','${aleague.started}')">
            
                <td><div id="tdata">${aleague.leagueName} </div></td>
                <td><div id="tdata"><fmt:formatDate value="${aleague.primetime}" pattern="hh:mm" /></div></td>
                <td width="100px"><div id="tdata">${aleague.getTeams().size()}/${aleague.maxPlayers}</div></td>
                  <td><div id="tdata"><fmt:formatDate value="${aleague.startDate}" pattern="yyyy-MM-dd" /></div></td>
                 <td width="90px"><div id="tdata"><div id="isLive">${aleague.started} </div></div></td>
                 
            </tr>
    </c:when>
    <c:when test="${aleague.started != 'y' && aleague.startDate >= currentDate}">    
            <tr onclick="intoTheLeague('${aleague.leagueName}','${aleague.started}')">
            
                <td ><div id="tdata">${aleague.leagueName} </div></td>
                <td><div id="tdata"><fmt:formatDate value="${aleague.primetime}" pattern="hh:mm" /></div></td>
                <td width="100px"><div id="tdata">${aleague.getTeams().size()}/${aleague.maxPlayers}</div></td>
                  <td><div id="tdata"><fmt:formatDate value="${aleague.startDate}" pattern="yyyy-MM-dd" /></div></td>
                 <td width="90px"><div id="tdata"><div id="isNew">${aleague.started}</div> </div></td>
                 
            </tr>
            </c:when>
            
           <c:when test="${aleague.started != 'y' && aleague.startDate < currentDate}">    
            <tr onclick="intoTheLeague('${aleague.leagueName}','${aleague.started}')">
            
                <td ><div id="tdata">${aleague.leagueName} </div></td>
                <td><div id="tdata"><fmt:formatDate value="${aleague.primetime}" pattern="hh:mm" /></div></td>
                <td width="100px"><div id="tdata">${aleague.getTeams().size()}/${aleague.maxPlayers}</div></td>
                  <td><div id="tdata"><fmt:formatDate value="${aleague.startDate}" pattern="yyyy-MM-dd" /></div></td>
                 <td width="90px"><div id="tdata"><div id="isOld">${aleague.started}</div> </div></td>
                 
            </tr>
            </c:when>
          </c:choose>
          
        </c:forEach>

   </tbody>
 </table>
     </div>
     <!-------------------------ALL LEAGUES END------------------>

   </td>      

<!----------------------- BODY END--------------------------------->  
   
  </tr>
</table>      
    
    
    
        <br class="spacer" />
        </div>
        <!--body end -->
        
        
   
                
              <!--footer end -->
</body>


</html>
