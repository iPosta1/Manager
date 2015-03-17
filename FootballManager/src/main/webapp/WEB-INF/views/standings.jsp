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

<table cellspacing="0" cellpadding="0" width="1000px" height="40px" align="center" >
     <tr>
       <td width="100%">
        
        <div id="topPanelStandings"></div>
          <input type="button" onclick="openDraft('${leaguename}')" value="draft"/>
          <input type="button" onclick="startDraft('${leaguename}')" value="start draft"/>
        </td>
       </tr>
   </table>

 <div id="tfon">
  
   
    <table cellspacing="0" cellpadding="0" width="1000px" height="100%" align="center" id="leagues_table">
     <tr>
         <td width="650px">
         <!---------------------- TEAMS LIST --------------------------->
            <div id="teams"> ${leaguename}
            <table width="100%" cellspacing="0" cellpadding="0" >
            
            <!-- AFC EAST TEAMS -->
            <thead>
           <tr><td><div id="conf_name_afc">AFC</div></td></tr></thead>
               <thead>
                    <tr>
                           <td width="30%"> <div id="standings_theader">AFC East Team</div></td>
                           <td width="15%"> <div id="standings_theader">W-L-T</div></td>
                            <td width="15%"> <div id="standings_theader">Total Off. yds</div></td>
                             <td width="15%"> <div id="standings_theader">Total Def. yds</div></td>
                             <td width="10%"> <div id="standings_theader">TD</div></td>
                             <td width="10%"> <div id="standings_theader">Streak</div></td>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="standing" items="${standings}" varStatus="status" >

                    <c:if test="${status.index >=0 && status.index<4}"> 
                    
                   
                     <tr onclick="">
                     
                           <td width="30%"><div id="s_tdata">${standing.getSID().team.getName()} </div></td>
                           <td width="15%"><div id="s_tdata">${standing.wins}-${standing.loses}-${standing.ties}</div></td>
                            <td width="15%"><div id="s_tdata">0</div></td>
                             <td width="15%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
            
                      </tr>
                      
                     
                   </c:if>
                    
             </c:forEach>

               </tbody>
               <tr><td><br></td></tr>
     
           
               <!-- AFC North TEAMS -->
               <thead>
                
                    <tr>
                           <td width="30%"> <div id="standings_theader">AFC North Team</div></td>
                           <td width="15%"> <div id="standings_theader">W-L-T</div></td>
                            <td width="15%"> <div id="standings_theader">Total Off. yds</div></td>
                             <td width="15%"> <div id="standings_theader">Total Def. yds</div></td>
                             <td width="10%"> <div id="standings_theader">TD</div></td>
                             <td width="10%"> <div id="standings_theader">Streak</div></td>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="standing" items="${standings}" varStatus="status" >

                    <c:if test="${status.index >=4 && status.index<8}"> 
                    
                   
                     <tr onclick="">
                              <td width="30%"><div id="s_tdata">${standing.getSID().team.getName()} </div></td>
                           <td width="15%"><div id="s_tdata">${standing.wins}-${standing.loses}-${standing.ties}</div></td>
                            <td width="15%"><div id="s_tdata">0</div></td>
                             <td width="15%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
            
                      </tr>
                   </c:if>
             </c:forEach>

               </tbody>
                <tr><td><br></td></tr>
                <!-- AFC South TEAMS -->
               <thead>
                
                    <tr>
                           <td width="30%"> <div id="standings_theader">AFC South Team</div></td>
                           <td width="15%"> <div id="standings_theader">W-L-T</div></td>
                            <td width="15%"> <div id="standings_theader">Total Off. yds</div></td>
                             <td width="15%"> <div id="standings_theader">Total Def. yds</div></td>
                             <td width="10%"> <div id="standings_theader">TD</div></td>
                             <td width="10%"> <div id="standings_theader">Streak</div></td>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="standing" items="${standings}" varStatus="status" >

                    <c:if test="${status.index >=8 && status.index<12}"> 
                    
                   
                     <tr onclick="">
                             <td width="30%"><div id="s_tdata">${standing.getSID().team.getName()} </div></td>
                           <td width="15%"><div id="s_tdata">${standing.wins}-${standing.loses}-${standing.ties}</div></td>
                            <td width="15%"><div id="s_tdata">0</div></td>
                             <td width="15%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
            
                      </tr>
                   </c:if>
             </c:forEach>

               </tbody>
                <tr><td><br></td></tr>
                
                    <!-- AFC West TEAMS -->
               <thead>
                
                    <tr>
                           <td width="30%"> <div id="standings_theader">AFC West Team</div></td>
                           <td width="15%"> <div id="standings_theader">W-L-T</div></td>
                            <td width="15%"> <div id="standings_theader">Total Off. yds</div></td>
                             <td width="15%"> <div id="standings_theader">Total Def. yds</div></td>
                             <td width="10%"> <div id="standings_theader">TD</div></td>
                             <td width="10%"> <div id="standings_theader">Streak</div></td>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="standing" items="${standings}" varStatus="status" >

                    <c:if test="${status.index >=12 && status.index<16}"> 
                    
                   
                     <tr onclick="">
                              <td width="30%"><div id="s_tdata">${standing.getSID().team.getName()} </div></td>
                           <td width="15%"><div id="s_tdata">${standing.wins}-${standing.loses}-${standing.ties}</div></td>
                            <td width="15%"><div id="s_tdata">0</div></td>
                             <td width="15%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
            
                      </tr>
                   </c:if>
             </c:forEach>

               </tbody>
                <tr><td><br></td></tr>
<!-- ---------------------NFC----------------------- -->

      <!-- NFC EAST TEAMS -->
            <thead>
           <tr><td><div id="conf_name_nfc">AFC</div></td></tr></thead>
               <thead>
                    <tr>
                           <td width="30%"> <div id="standings_theader">NFC East Team</div></td>
                           <td width="15%"> <div id="standings_theader">W-L-T</div></td>
                            <td width="15%"> <div id="standings_theader">Total Off. yds</div></td>
                             <td width="15%"> <div id="standings_theader">Total Def. yds</div></td>
                             <td width="10%"> <div id="standings_theader">TD</div></td>
                             <td width="10%"> <div id="standings_theader">Streak</div></td>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="standing" items="${standings}" varStatus="status" >

                    <c:if test="${status.index >=16 && status.index<20}"> 
                    
                   
                     <tr onclick="">
                              <td width="30%"><div id="s_tdata">${standing.getSID().team.getName()} </div></td>
                           <td width="15%"><div id="s_tdata">${standing.wins}-${standing.loses}-${standing.ties}</div></td>
                            <td width="15%"><div id="s_tdata">0</div></td>
                             <td width="15%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
            
                      </tr>
                   </c:if>
             </c:forEach>

               </tbody>
                <tr><td><br></td></tr>
               
               <!-- NFC North TEAMS -->
               <thead>
                
                    <tr>
                            <td width="30%"> <div id="standings_theader">NFC North Team</div></td>
                           <td width="15%"> <div id="standings_theader">W-L-T</div></td>
                            <td width="15%"> <div id="standings_theader">Total Off. yds</div></td>
                             <td width="15%"> <div id="standings_theader">Total Def. yds</div></td>
                             <td width="10%"> <div id="standings_theader">TD</div></td>
                             <td width="10%"> <div id="standings_theader">Streak</div></td>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="standing" items="${standings}" varStatus="status" >

                    <c:if test="${status.index >=20 && status.index<24}"> 
                    
                   
                     <tr onclick="">
                              <td width="30%"><div id="s_tdata">${standing.getSID().team.getName()} </div></td>
                           <td width="15%"><div id="s_tdata">${standing.wins}-${standing.loses}-${standing.ties}</div></td>
                            <td width="15%"><div id="s_tdata">0</div></td>
                             <td width="15%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
            
                      </tr>
                   </c:if>
             </c:forEach>

               </tbody>
                <tr><td><br></td></tr>
               
                <!-- NFC South TEAMS -->
               <thead>
                
                    <tr>
                          <td width="30%"> <div id="standings_theader">NFC South Team</div></td>
                           <td width="15%"> <div id="standings_theader">W-L-T</div></td>
                            <td width="15%"> <div id="standings_theader">Total Off. yds</div></td>
                             <td width="15%"> <div id="standings_theader">Total Def. yds</div></td>
                             <td width="10%"> <div id="standings_theader">TD</div></td>
                             <td width="10%"> <div id="standings_theader">Streak</div></td>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="standing" items="${standings}" varStatus="status" >

                    <c:if test="${status.index >=24 && status.index<28}"> 
                    
                   
                     <tr onclick="">
                           <td width="30%"><div id="s_tdata">${standing.getSID().team.getName()} </div></td>
                           <td width="15%"><div id="s_tdata">${standing.wins}-${standing.loses}-${standing.ties}</div></td>
                            <td width="15%"><div id="s_tdata">0</div></td>
                             <td width="15%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
            
                      </tr>
                   </c:if>
             </c:forEach>

               </tbody>
                <tr><td><br></td></tr>
               
                    <!-- NFC West TEAMS -->
               <thead>
                
                    <tr>
                     <td width="30%"> <div id="standings_theader">NFC West Team</div></td>
                           <td width="15%"> <div id="standings_theader">W-L-T</div></td>
                            <td width="15%"> <div id="standings_theader">Total Off. yds</div></td>
                             <td width="15%"> <div id="standings_theader">Total Def. yds</div></td>
                             <td width="10%"> <div id="standings_theader">TD</div></td>
                             <td width="10%"> <div id="standings_theader">Streak</div></td>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="standing" items="${standings}" varStatus="status" >

                    <c:if test="${status.index >=28 && status.index<32}"> 
                    
                   
                     <tr onclick="">
                             <td width="30%"><div id="s_tdata">${standing.getSID().team.getName()} </div></td>
                           <td width="15%"><div id="s_tdata">${standing.wins}-${standing.loses}-${standing.ties}</div></td>
                            <td width="15%"><div id="s_tdata">0</div></td>
                             <td width="15%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                            <td width="10%"><div id="s_tdata">0</div></td>
                      </tr>
                   </c:if>
             </c:forEach>

               </tbody>
                 <tr><td><br></td></tr>
 </table>          
             
</div>  
          <!------------------ TEAMS LIST END---------- -->
        </td>
        
         <td width="350px">
           <div id="leftStBlock">
             <!-- ------------------------------ -->
           <div class="weektabs">
    <input id="weektab1" type="radio" name="weektabs" checked>
    <label for="weektab1" title="Your Leagues">Week 1</label>
 
    <input id="weektab2" type="radio" name="weektabs">
    <label for="weektab2" title="New Leagues">Week 2</label>
 
    <input id="weektab3" type="radio" name="weektabs">
    <label for="weektab3" title="Live Leagues">Live Leagues</label>
 
    <input id="weektab4" type="radio" name="weektabs">
    <label for="weektab4" title="All Leagues">All Leagues</label>
    
       <input id="weektab5" type="radio" name="weektabs">
    <label for="weektab5" title="Create a League">Create a League</label>
    
     <section id="weekcontent1">
    <!------------------ WEEK 1 -------------->
                      
            <table cellspacing="0" cellpadding="0" width="100%" height="100%"  >
     <tbody>
     <c:forEach var="game" items="${games}" >
     <c:if test="${game.getWeek().weekName == 'week 1' }">
       
        <c:choose>
        
        <c:when test="${game.getTeam1().getUuser().getUsername() == pageContext.request.userPrincipal.getName() || game.getTeam2().getUuser().getUsername() == pageContext.request.userPrincipal.getName()}">  
   
               <tr id="your" onclick="">
                <td width="80%"><div id="g_tdata">${game.getTeam1().getName()} </div></td>
                <td width="20%"><div id="g_tdata">${game.team1Points}</div></td>
             </tr>
             <tr id="your" onclick="">
                <td width="80%"><div id="g_tdata">${game.getTeam2().getName()} </div></td>
                <td width="20%"><div id="g_tdata">${game.team2Points}</div></td>
              </tr>
              <tr><td> <div id="games_footer"></div> </td></tr>
        </c:when>
           
        
        
        <c:when test="${game.getTeam1().getName() != pageContext.request.userPrincipal.getName()}">  
            <tr onclick="">
                <td width="80%"><div id="g_tdata">${game.getTeam1().getName()} </div></td>
                <td width="20%"><div id="g_tdata">${game.team1Points}</div></td>
             </tr>
             <tr onclick="">
                <td width="80%"><div id="g_tdata">${game.getTeam2().getName()} </div></td>
                <td width="20%"><div id="g_tdata">${game.team2Points}</div></td>
              </tr>
              <tr><td> <div id="games_footer"></div> </td></tr>
        </c:when>
        
        </c:choose>
               
         
                 
           </c:if>
        </c:forEach>

   </tbody>
 </table>       
    <!-- ----------------WEEK 1 END--------- -->
     </section>
      <section class="weektabs" id="weekcontent2">
      <!------------------ WEEK 2 -------------->
                      
            <table cellspacing="0" cellpadding="0" width="100%" height="100%"  >
     <tbody>
     <c:forEach var="game" items="${games}" >
     <c:if test="${game.getWeek().weekName == 'week 2' }">
       
        <c:choose>
        
        <c:when test="${game.getTeam1().getUuser().getUsername() == pageContext.request.userPrincipal.getName() || game.getTeam2().getUuser().getUsername() == pageContext.request.userPrincipal.getName()}">  
   
               <tr id="your" onclick="">
                <td width="80%"><div id="g_tdata">${game.getTeam1().getName()} </div></td>
                <td width="20%"><div id="g_tdata">${game.team1Points}</div></td>
             </tr>
             <tr id="your" onclick="">
                <td width="80%"><div id="g_tdata">${game.getTeam2().getName()} </div></td>
                <td width="20%"><div id="g_tdata">${game.team2Points}</div></td>
              </tr>
              <tr><td> <div id="games_footer"></div> </td></tr>
        </c:when>
           
        
        
        <c:when test="${game.getTeam1().getName() != pageContext.request.userPrincipal.getName()}">  
            <tr onclick="">
                <td width="80%"><div id="g_tdata">${game.getTeam1().getName()} </div></td>
                <td width="20%"><div id="g_tdata">${game.team1Points}</div></td>
             </tr>
             <tr onclick="">
                <td width="80%"><div id="g_tdata">${game.getTeam2().getName()} </div></td>
                <td width="20%"><div id="g_tdata">${game.team2Points}</div></td>
              </tr>
              <tr><td> <div id="games_footer"></div> </td></tr>
        </c:when>
        
        </c:choose>
               
         
                 
           </c:if>
        </c:forEach>

   </tbody>
 </table>       
    <!-- ----------------WEEK 2 END--------- -->
     </section>
      <section class="weektabs" id="weekcontent3">
      <br>
      QWEcvcxvcx
      <input id="input_buttons" type="button"  onclick="toDraft('${leaguename}')" />
   
     </section>
      <section class="weektabs" id="weekcontent4">
      fggfgf
     </section>
      <section class="weektabs" id="weekcontent5">
     </section>
     </div>
     <!-- ------------------------------ -->
           </div>
          
        </td>
       </tr>
   </table>
         

     
      <!--------------------- top panel------ -->
      <!-- 
<div id="topLeaguePanel">
<table cellspacing="0" cellpadding="0" width="100%" height="20px" align="center">

  <tr>
     <td class="tpd" width="100px"> 
         <a class="tp" href="${pageContext.request.contextPath}/leagues">League</a>
     </td> 
     <td class="tpd" width="100px"> 
          <a class="tp" href="#">My team</a>
     </td>
     <td class="tpd" width="100px"> 
          <a class="tp" href="#">Schedule</a>
     </td>
     <td class="tpd" width="100px"> 
          <a class="tp" href="#">Players</a>
     </td>
     <td class="tpd" width="100px"> 
          <a class="tp" href="#">Team's stats</a>
     </td>
     <td class="tpd" width="100px"> 
          <a class="tp" href="#">Player's stats</a>
     </td>
 
  </tr>     
</table>
</div>
 -->
  <!--------------------- top panel end------ -->
     

 </div>

</body>


</html>
