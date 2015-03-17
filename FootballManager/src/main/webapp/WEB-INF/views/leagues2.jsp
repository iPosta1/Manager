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
       <td width="240px">
        

          
       </td>
        
  <!----------------------- BODY --------------------------------->
  <td width="750px" vertical-align="top">
     




<div class="tabs">
    <input id="tab1" type="radio" name="tabs" checked>
    <label for="tab1" title="Your Leagues">Your Leagues</label>
 
    <input id="tab2" type="radio" name="tabs">
    <label for="tab2" title="New Leagues">New Leagues</label>
 
    <input id="tab3" type="radio" name="tabs">
    <label for="tab3" title="Live Leagues">Live Leagues</label>
 
    <input id="tab4" type="radio" name="tabs">
    <label for="tab4" title="All Leagues">All Leagues</label>
    
       <input id="tab5" type="radio" name="tabs">
    <label for="tab5" title="Create a League">Create a League</label>
 
    <section id="content1">
 <!----------------- YOUR LEAGUES ----------------------------->  
<jsp:useBean id="date" class="java.util.Date"/>
<fmt:formatDate var="currentDate"  
                value="${date}"
                pattern="yyyy-MM-dd"/>
   <!------- LIVE -------->
 <p>LIVE</p>    

 <c:if test="${not empty pageContext.request.userPrincipal}">

 
<table width="100%" id="stable" >
   <thead>
        <tr>
            <td><div id="theader" >League</div></td> 
        <td> <div id="theader">Prime Time</div></td> 
           <td width="100px"><div id="theader">Players</div></td>
           <td><div id="theader">Week</div></td>
          
        </tr>
  </thead>
  <tbody>
     <c:forEach var="league" items="${userNewLeagues}" >
         <c:if test="${league.started == 'y'}">
            <tr onclick="intoTheLeague('${league.leagueName}','${league.started}')">
            
                <td><div id="tdata">${league.leagueName} </div></td>
                <td><div id="tdata"><fmt:formatDate value="${league.primetime}" pattern="hh:mm" /></div></td>
                <td width="100px"><div id="tdata">${league.getTeams().size()}/${league.maxPlayers}</div></td>
                  <td><div id="tdata"><fmt:formatDate value="${league.startDate}" pattern="yyyy-MM-dd" /></div></td>
     
            </tr>
           </c:if>
           
        </c:forEach>

   </tbody>
 </table>

</c:if>
  <!------- LIVE END-------->
  <br>
  <!------- Not Started -------->
 <p>NEW</p>    
     
 <c:if test="${not empty pageContext.request.userPrincipal}">
<table width="100%" id="stable" >
   <thead>
        <tr>
            <td><div id="theader" >League</div></td> 
        <td> <div id="theader">Prime Time</div></td> 
           <td width="100px"><div id="theader">Players</div></td>
           <td><div id="theader">Start Date</div></td>
          
        </tr>
  </thead>
  <tbody>
        <c:forEach var="league" items="${userNewLeagues}" >

     <c:if test="${league.started == 'n' && league.startDate >= currentDate}">
     
            <tr onclick="intoTheLeague('${league.leagueName}','${league.started}')">
            
                <td><div id="tdata">${league.leagueName} </div></td>
                <td><div id="tdata"><fmt:formatDate value="${league.primetime}" pattern="hh:mm" /></div></td>
                <td width="100px"><div id="tdata">${league.getTeams().size()}/${league.maxPlayers}</div></td>
                  <td><div id="tdata"><fmt:formatDate value="${league.startDate}" pattern="yyyy-MM-dd" /></div></td>
     
            </tr>
           </c:if>
           
        </c:forEach>

   </tbody>
 </table>
</c:if>

  <!------- Not Started END-------->
    <br>
    <!------- Old  -------->
 <p>OLD</p>    
    
 <c:if test="${not empty pageContext.request.userPrincipal}">
<table width="57%" id="stable" >
   <thead>
        <tr>
            <td><div id="theader" >League</div></td> 
           <td><div id="theader" >Standing</div></td> 
          
        </tr>
  </thead>
  <tbody>
       <c:forEach var="league" items="${userNewLeagues}" >
       

     <c:if test="${league.started == 'n' && league.startDate < currentDate}">
       
            <tr onclick="intoTheLeague('${league.leagueName}','${league.started}')">
            
                <td><div id="tdata">${league.leagueName} </div></td>
                <td><div id="tdata">${league.leagueName} </div></td>
              
     
            </tr>
           </c:if>
        </c:forEach>

   </tbody>
 </table>

</c:if>
  <!------- OLD END-------->
<!----------------- YOUR LEAGUES END-----------------------------> 
    </section>  
    <section id="content2">
      <!-- -----------NEW LEAGUES----------------------------- -->
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
    <section id="content4">
     <!------------------------ ALL LEAGUES--------------------- -->
     
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
     <c:forEach var="aleague" items="${allLeagues}" >
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
     
     <!-------------------------ALL LEAGUES END------------------>
    </section>    
    
     <section id="content5">
     <!---------------- CREATE A LEAGUE--------------------------- -->
     <div id="createLeague">
  
         <form:form method="post" action="addLeague" commandName="league" id="createLeagueForm" >
            <table width="100%">
              <tr height="50px"> 
                  <td width="30%"> League name
                       <form:input path="leagueName" id="leaguename" onkeyup="if(/[^a-aZ-Z\s]/.test(this.value))this.value=this.value.replace(/[^a-zA-Z\s]+/g,'')"/> 
                  </td>
                  
                   <td width="70%"> <label id="createleague_error">Incorrect name</label> </td>
                  
              </tr>
              
              <tr height="200px"> 
              
               <td width="50%"> Start date 
                      <form:input type="text" path="startDate" id="datetimepicker"/>                
                  </td>
              
                  <td width="50%"> Prime time <br>
                      <form:input type="text" path="primetime" id="datetimepicker1"/>
                       
                  </td>

              </tr>
              
            </table>
          
               <div id="sumbit_btn_l">
               <table width="30px" height="40px" align="center">
                 <tr>
                   <td>
                       <input type="button" onclick="validateLeagueForm();" value="SUBMIT" class="sumbit_style" />
                       
                        </td>
                       
                 </tr>
               </table>
             
              </div>
         </form:form>
   
    </div>
      <!---------------- CREATE A LEAGUE END--------------------------- -->
    </section>    
</div>



   </td>      

<!----------------------- BODY END--------------------------------->  
   
  </tr>
</table>      
    
    
    
        <br class="spacer" />
        </div>
        <!--body end -->
        
        
   
                
              <!--footer end -->
</body>


<script src="${pageContext.request.contextPath}/resources/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery.datetimepicker.js"></script>
<script>
$('#datetimepicker').datetimepicker({
    lang:'en',
    timepicker:false,
    format:'Y-m-d',
    formatDate:'Y-m-d',
    inline:true,
    value:0,
    minDate:'-1970/01/02' // yesterday is minimum date

});



$('#datetimepicker1').datetimepicker({
    datepicker:false,
    format:'H:i',
    inline:true,
    step:5,
    value:'20:00'
});
</script>
</html>
