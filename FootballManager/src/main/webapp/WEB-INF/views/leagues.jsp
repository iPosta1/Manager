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



<!--  -->


        
  <!----------------------- BODY --------------------------------->


<%@include  file="top.jsp" %>

<div class="tabs">

<div id="ttop">

<jsp:useBean id="date" class="java.util.Date"/>
<fmt:formatDate var="currentDate"  
                value="${date}"
                pattern="yyyy-MM-dd"/>

    <input id="tab1" type="radio" name="tabs" checked>
    <label for="tab1" title="All Leagues">All Leagues</label>

    <input id="tab2" type="radio" name="tabs">
    <label for="tab2" title="New Leagues">New Leagues</label>
 
    <input id="tab3" type="radio" name="tabs">
    <label for="tab3" title="Live Leagues">Live Leagues</label>
 

   <section id="content1">
     <!------------------------ ALL LEAGUES--------------------- -->
     
     <table cellspacing="0" cellpadding="0" width="100%" id="leagues_table" >
   <thead >
        <tr >
            <td><div id="theader">League</div></td> 
           <td> <div id="theader">Prime Time</div></td> 
           <td width="100px"><div id="theader">Players</div></td>
           <td><div id="theader">Start date</div></td>
           <td width="90px"><div id="theader">Started</div></td>
          
        </tr>
  </thead>
  <tbody>
     <c:forEach var="aleague" items="${allLeagues}" >
     <c:choose>
     <c:when test="${aleague.started == 'y'}">  
            <tr onclick="intoTheLeague('${aleague.leagueName}','${aleague.started}')">
            
                <td><div id="tdata"><div id="isLive">${aleague.leagueName} </div></div></td>
                <td><div id="tdata"><div id="isLive"><fmt:formatDate value="${aleague.primetime}" pattern="hh:mm" /></div></div></td>
                <td width="100px"><div id="tdata"><div id="isLive">${aleague.getTeams().size()}/${aleague.maxPlayers}</div></div></td>
                  <td><div id="tdata"><div id="isLive"><fmt:formatDate value="${aleague.startDate}" pattern="yyyy-MM-dd" /></div></div></td>
                 <td width="90px"><div id="tdata"><div id="isLive">${aleague.started} </div></div></td>
                 
            </tr>
    </c:when>
    <c:when test="${aleague.started != 'y' && aleague.startDate >= currentDate}">    
            <tr onclick="intoTheLeague('${aleague.leagueName}','${aleague.started}')">
            
                <td ><div id="tdata"><div id="isNew">${aleague.leagueName} </div></div></td>
                <td><div id="tdata"><div id="isNew"><fmt:formatDate value="${aleague.primetime}" pattern="hh:mm" /></div></div></td>
                <td width="100px"><div id="tdata"><div id="isNew">${aleague.getTeams().size()}/${aleague.maxPlayers}</div></div></td>
                  <td><div id="tdata"><div id="isNew"><fmt:formatDate value="${aleague.startDate}" pattern="yyyy-MM-dd" /></div></div></td>
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
     
     <!-------------------------ALL LEAGUES END------------------>
    </section>    
  
 
    <section id="content2">
      <!-- -----------NEW LEAGUES----------------------------- -->
         <table width="100%" id="leagues_table" cellspacing="0" cellpadding="0" >
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
     <c:forEach var="aleague" items="${allLeagues}" >
    
     

     <c:if test="${aleague.started == 'n' && aleague.startDate >= currentDate}">
            <tr onclick="intoTheLeague('${aleague.leagueName}','${aleague.started}')">
            
                <td><div id="tdata">${aleague.leagueName} </div></td>
                <td><div id="tdata"><fmt:formatDate value="${aleague.primetime}" pattern="hh:mm" /></div></td>
                <td width="100px"><div id="tdata">${aleague.getTeams().size()}/${aleague.maxPlayers}</div></td>
                  <td><div id="tdata"><fmt:formatDate value="${aleague.startDate}" pattern="yyyy-MM-dd" /></div></td>
                 <td width="90px"><div id="tdata">${aleague.started} </div></td>
                 
            </tr>
          
           </c:if>
        </c:forEach>

   </tbody>
 </table>
      <!-- ------------NEW LEAGUES END------------------------ -->
    </section> 
    <section id="content3">
     <!----------------------LIVE LEAGUES------------------------ -->
      <table width="100%" id="leagues_table" cellspacing="0" cellpadding="0" >
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
     <c:forEach var="aleague" items="${allLeagues}" >
     <c:if test="${aleague.started == 'y'}">
            <tr onclick="intoTheLeague('${aleague.leagueName}','${aleague.started}')">
            
                <td><div id="tdata">${aleague.leagueName} </div></td>
                <td><div id="tdata"><fmt:formatDate value="${aleague.primetime}" pattern="hh:mm" /></div></td>
                <td width="100px"><div id="tdata">${aleague.getTeams().size()}/${aleague.maxPlayers}</div></td>
                  <td><div id="tdata"><fmt:formatDate value="${aleague.startDate}" pattern="yyyy-MM-dd" /></div></td>
                 <td width="90px"><div id="tdata">${aleague.started} </div></td>
                 
            </tr>
          
           </c:if>
        </c:forEach>

   </tbody>
 </table>
     <!-- ------------------LIVE LEAGUES END--------------------- -->
    </section> 
 
    
</div>

  </div>

     

<!----------------------- BODY END--------------------------------->  
   
    
    
        
   
                
              <!--footer end -->
</body>
</html>
