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


 <script>

 $(window).unload(function(){ 
	 var leaguename = '${leaguename}';
	 leaveDraft(leaguename);
   });

    window.onbeforeunload = function (event) {
    	 var leaguename = '${leaguename}';
    	 leaveDraft(leaguename);
    }
	  
 
 $(document).ready(
         function() {
        	 var leaguename = '${leaguename}';
       
        	 loadTables(leaguename);

        	 setInterval(function() {
        	        
                  checkUpdateState(leaguename);
                  updateTimer(leaguename);
                  }, 1000);
        	 
        	// $("#draft_p_table tr").click(function(){
        	//	     });
            // setInterval(function() {
        
          //  getPlayers(leaguename);
           //  }, 30000);

        
             
         });



        </script>
</head>
<body>

<table cellspacing="0" cellpadding="0" width="100%" height="100%"  >
     
    <tr > 
   
        <td  width="20%" >
      
            <div id="testblock">
       <table cellspacing="0" cellpadding="0" width="100%" height="100%" id="draftTeams" >
            <tr><td></td></tr>
        </table>
        </div> 
         <div id="your_next_pick">
         <table cellspacing="0" cellpadding="0" width="100%" height="100%" id="yourNext" >
         </table>
            </div>
                
        </td>

        <td width="80%">
            <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" >
                <tr height="60%">
                 
                  <td>
                  <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" >
                    <tr>
                       <td width="50%">
                           <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" id="draftInfo" >
                            <tr height="50%">
                                <td>
                                   <table cellspacing="0" cellpadding="0" align="center" id="draftPicks" >
                                      <tr><td></td></tr>
                                   </table>
                                </td> 
                            </tr>
                              <tr height="50%">
                                <td id="draft_p_info">
                                <label id="p_id"> </label>
                                <label id="p_lastname"> </label>
                                <label id="p_firstname"> </label>
                                <label id="p_lastname"> </label>
                                <label id="p_photo"> </label>
                                <label id="p_birthdate"> </label>
                                <label id="p_position"> </label>
                                <input type="button" onclick="draftPlayer('${pageContext.request.userPrincipal.name}','${leaguename}')" value="Draft Player"/>

                                </td> 
                            </tr>
                       </table>
                       </td>
                       
                        <td width="50%">
                            <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" id="draftInfo" >
                            <tr>
                                <td>
                                 <div class="draftRosterTabs">
                                 
                                     <input id="dtab1" type="radio" name="tabs" checked>
                                     <label for="dtab1" title="Offense">Offense</label>

                                     <input id="dtab2" type="radio" name="tabs">
                                     <label for="dtab2" title="Defense">Defense</label>
 
                                     <input id="dtab3" type="radio" name="tabs">
                                     <label for="dtab3" title="Special Team">Special Team</label>
                                     
                                      <section id="dcontent1">
			                                         <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" id="draftRoster" >
			                                            <tr height="50%">
			                                                <td width="25%"> 
			                                                     <table cellspacing="0" cellpadding="0" align="center" id="draftRosterQB" >
			                                                      <thead>
			                                                         <tr><td>QB</td></tr>
			                                                      </thead>
			                                                      <tbody>
			                                                         <tr><td> </td></tr>
			                                                       </tbody>
			                                                     </table>
			                                                </td>
			                                                
			                                                 <td width="25%">
			                                                <table cellspacing="0" cellpadding="0" align="center" id="draftRosterWR" >
                                                                  <thead>
                                                                     <tr><td>WR</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
			                                                </td>
			                                                
			                                                 <td width="25%">
			                                                 <table cellspacing="0" cellpadding="0" align="center" id="draftRosterRB" >
                                                                  <thead>
                                                                     <tr><td>RB</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
			                                                </td>
			                                               
			                                                 <td width="25%">
			                                                <table cellspacing="0" cellpadding="0" align="center" id="draftRosterTE" >
                                                                  <thead>
                                                                     <tr><td>TE</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
			                                                </td>
			                                              </tr>  
			                                              
			                                              
			                                              <tr height="50%">
			                                                 <td width="25%">
			                                                <table cellspacing="0" cellpadding="0" align="center" id="draftRosterFB" >
                                                                  <thead>
                                                                     <tr><td>FB</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
			                                                </td>
			                                                
			                                                 <td width="25%">
			                                                <table cellspacing="0" cellpadding="0" align="center" id="draftRosterC" >
                                                                  <thead>
                                                                     <tr><td>C</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
			                                                </td>
			                                                
			                                                 <td width="25%">
			                                                 <table cellspacing="0" cellpadding="0" align="center" id="draftRosterOT" >
                                                                  <thead>
                                                                     <tr><td>OT</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
			                                                </td>
			                                                
			                                                 <td width="25%">
			                                               <table cellspacing="0" cellpadding="0" align="center" id="draftRosterOG" >
                                                                  <thead>
                                                                     <tr><td>OG</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
			                                                </td>
			                                                
			                                            
			                                            </tr>
			                                         </table>
                                         </section>
                                         
                                         <section id="dcontent2">
                                                     <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" id="draftRoster" >
                                                        <tr height="50%">
                                                        
                                                           <td width="25%">
                                                            <table cellspacing="0" cellpadding="0" align="center" id="draftRosterDE" >
                                                                  <thead>
                                                                     <tr><td>DE</td></tr>
                                                                  </thead>
                                                                  <tbbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbbody>
                                                                 </table>
                                                            </td>
                                                        
                                                        
                                                         
                                                            
                                                             <td width="25%">
                                                            <table cellspacing="0" cellpadding="0" align="center" id="draftRosterCB" >
                                                                  <thead>
                                                                     <tr><td>CB</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                                            </td>
                                                            
                                                             <td width="25%">
                                                            <table cellspacing="0" cellpadding="0" align="center" id="draftRosterSS" >
                                                                  <thead>
                                                                     <tr><td>SS</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                                            </td>
                                                           
                                                             <td width="25%">
                                                             <table cellspacing="0" cellpadding="0" align="center" id="draftRosterFS" >
                                                                  <thead>
                                                                     <tr><td>FS</td></tr>
                                                                  </thead>
                                                                  <tbbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbbody>
                                                                 </table>
                                                            </td>
                                                          </tr>  
                                                          
                                                          
                                                          <tr height="50%">
                                                          
                                                             <td width="25%">
                                                              <table cellspacing="0" cellpadding="0" align="center" id="draftRosterOLB" >
                                                                  <thead>
                                                                     <tr><td>OLB</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                                            </td>
                                                            
                                                             <td width="25%">
                                                              <table cellspacing="0" cellpadding="0" align="center" id="draftRosterMLB" >
                                                                  <thead>
                                                                     <tr><td>MLB</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                                            </td>
                                                            
                                                             <td width="25%">
                                                            <table cellspacing="0" cellpadding="0" align="center" id="draftRosterDT" >
                                                                  <thead>
                                                                     <tr><td>DT</td></tr>
                                                                  </thead>
                                                                  <tbbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbbody>
                                                                 </table>
                                                            </td>
                                                            
                                                             <td width="25%">
                                                            
                                                            </td>

                                                            
                                                        
                                                        </tr>
                                                     </table>
                                         </section>
                                         
                                          <section id="dcontent3">
                                                     <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" id="draftRoster" >
                                                        <tr height="50%">
                                                            <td width="25%">
                                                              <table cellspacing="0" cellpadding="0" align="center" id="draftRosterK" >
                                                                  <thead>
                                                                     <tr><td>Kicker</td></tr>
                                                                  </thead>
                                                                  <tbbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbbody>
                                                                 </table>
                                                            </td>
                                                            
                                                             <td width="25%">
                                                             <table cellspacing="0" cellpadding="0" align="center" id="draftRosterP" >
                                                                  <thead>
                                                                     <tr><td>Punter</td></tr>
                                                                  </thead>
                                                                  <tbbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbbody>
                                                                 </table>
                                                            </td>
                                                            
                                                             <td width="25%">
                                                             
                                                            </td>
                                                           
                                                             <td width="25%">
                                                            
                                                            </td>
                                                          </tr>  
                                                          
                                                          
                                                          <tr height="50%">
                                                             <td width="25%">
                                                            
                                                            </td>
                                                            
                                                             <td width="25%">
                                                          
                                                            </td>
                                                            
                                                             <td width="25%">
                                                            
                                                            </td>
                                                            
                                                             <td width="25%">
                                                            
                                                            </td>
                                                            
                                                        
                                                        </tr>
                                                     </table>
                                         </section>
                                     </div>
                                
                                </td> 
                            </tr>
                             
                       </table>
                        </td>
                    </tr>
                 
                    </table>
                
                
                
                
                <tr height="40%">
                   <td>
                    
                
                  <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" id="draft_p_table" >
                      
                           <thead>

                      <tr>
                         <!--  <td width="3%"><div id="dhead" > № </div></td>  -->
                          <td width="15%"><div id="dhead" > player </div></td> 
                          <td width="5%">   <div id="dhead">age </div></td>
                          <td width="5%">   <div id="dhead">height </div></td>
                          <td width="5%">   <div id="dhead">weight </div></td>
                          <td width="5%">   <div id="dhead">position </div></td>
                          <td width="5%">   <div id="dhead">overall </div></td>
                          <td width="5%">   <div id="dhead">speed </div></td>
                          <td width="5%">   <div id="dhead">agility </div></td>
                          <td width="5%">   <div id="dhead">awareness </div></td>
                          <td width="5%">   <div id="dhead">catching </div></td>
                          <td width="5%">   <div id="dhead">carrying </div></td>
                          <td width="5%">   <div id="dhead">tackling </div></td>
                          <td width="5%">   <div id="dhead">break tackle </div></td>
                          <td  width="5%">   <div id="dhead">jumping </div></td>
                          <td width="5%">   <div id="dhead">throw power </div></td>
                          <td width="5%">   <div id="dhead">throw accuracy </div></td>
                          <td width="5%">   <div id="dhead">kick power </div></td>
                          <td width="5%">   <div id="dhead">kick accuracy </div></td>
                          <td width="5%">   <div id="dhead">strength </div></td>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                      
                   </td>
                </tr>
                
            </table>
            
            
            
            
        </td>
    
    </tr>
</table>

</body>
</html>