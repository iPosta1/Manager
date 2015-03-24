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
            //       });
            // setInterval(function() {
        
          //  getPlayers(leaguename);
           //  }, 30000);

            
             
         });



        </script>
</head>
<body>
<!-- ----------- -->
  
	<div id="page_loader">
	   <div id="page_name_text">FANTASY DRAFT </div>  
	   <div class="wheel" id="page_load_spinner"></div>
	   <div class="wheel2" id="page_load_spinner2"></div>
    <div id="page_load_text">LOADING</div>
	</div>
<!-- -------------- -->
 <div id="testblock">
       <table cellspacing="0" cellpadding="0" width="100%" height="100%" id="draftTeams" >
            <tr><td></td></tr>
        </table>
        </div> 
        
        
         <div id="your_next_pick">
         <table cellspacing="0" cellpadding="0" width="100%" height="100%" id="yourNext" >
         </table>
            </div>
            
             <div id="bl_header"> Draft History</div>
            <div id="bl_picks">
              <!-- ------PICKS------------- -->
                             <table cellspacing="0" cellpadding="0" width="100%" height="100%" id="draftPicks" >
                                        <tbody>
                                         <tr>
                                         <td></td>
                                         </tr>
                                         </tbody>
                                </table>
                            <!-- ----------Picks end------- -->
            </div>
            
              <div id="bl_info">
              
               <label id="p_id"> </label>
              <table  width="100%" height="100%" >
	              <tr height="160px">
		               <td width="120px">
		                      <table  width="100%" height="100%" >
		                          <tr height="140px">
		                              <td>
		                              <div id="player_photo">
		                              <!-- PHOTO -->
		                              <label id="p_photo"> </label>
		                              </div>
		                              <div id="p_name">
		                               <label id="p_firstname"> </label>
                                       <label id="p_lastname"> </label>
                                       </div>
                                      
		                              </td>
		                          </tr>
		                          
		                           <tr>
                                      <td>
                                      <div id="draft_button" onclick="draftPlayer('${pageContext.request.userPrincipal.name}','${leaguename}')" value="Draft Player">Draft Player</div>
                                      
                                      </td>
                                  </tr>
		                      </table>
		               </td>
		               <td>
		               <div id="p_main_info">
		                  <table  width="100%" height="100%">
		                  <tr>
		                   <td width="45%">Position</td>
		                   <td id ="p_info_td" width="5%"><div id="p_info_cell"><label id="p_position"> </label></div></td>
		                   <td  width="45%">Carrying</td>
		                   <td id ="p_carrying_td" width="5%"><div id="p_info_cell"><label id="p_carrying"> </label></div></td>
		                  </tr>
		                  
		                  <tr>
                           <td width="45%">Overall</td>
                           <td width="5%" id="p_ovr_td"><div id="p_info_cell"><label id="p_ovr"> </label></div></td>
                           <td  width="45%">Tackling</td>
                           <td id ="p_tackling_td" width="5%"><div id="p_info_cell"><label id="p_tackling"> </label></div></td>
                          </tr>
                          
                          <tr>
                           <td>Age</td>
                           <td id ="p_info_td"><div id="p_info_cell"><label id="p_birthdate"> </label></div></td>
                           <td>Break Tackle</td>
                           <td id ="p_breaktackle_td"><div id="p_info_cell"><label id="p_breaktackle"> </label></div></td>
                          </tr>
                          
                          <tr>
                           <td>Height</td>
                           <td id ="p_info_td"><div id="p_info_cell"><label id="p_height"> </label></div></td>
                           <td>Jumping</td>
                           <td id ="p_jumping_td"><div id="p_info_cell"><label id="p_jumping"> </label></div></td>
                          </tr>
                          
                          <tr>
                           <td>Weight</td>
                           <td id ="p_info_td"><div id="p_info_cell"><label id="p_weight"> </label></div></td>
                           <td>Throw Power</td>
                           <td id ="p_throwpower_td"><div id="p_info_cell"><label id="p_throwpower"> </label></div></td>
                          </tr>
                          
                          <tr>
                           <td>Speed</td>
                           <td id ="p_speed_td"><div id="p_info_cell"><label id="p_speed"> </label></div></td>
                           <td >Throw Accuracy</td>
                           <td id ="p_throwaccuracy_td"><div id="p_info_cell"><label id="p_throwaccuracy"> </label></div></td>
                          </tr>
                          
                          <tr>
                           <td>Agility</td>
                           <td id ="p_agility_td"><div id="p_info_cell"><label id="p_agility"> </label></div></td>
                           <td>Kick Power</td>
                           <td id ="p_kickpower_td"> <div id="p_info_cell"><label id="p_kickpower"> </label></div></td>
                          </tr>
                          
                          <tr>
                           <td>Awareness</td>
                           <td id ="p_awareness_td"><div id="p_info_cell"><label id="p_awareness"> </label></div></td>
                           <td>Kick Accuracy</td>
                           <td id ="p_kickaccuracy_td"><div id="p_info_cell"><label id="p_kickaccuracy"> </label></div></td>
                          </tr>
                          
                          <tr>
                           <td>Catching</td>
                           <td id ="p_catching_td"><div id="p_info_cell"><label id="p_catching"> </label></div></td>
                           <td>Strength</td>
                           <td id ="p_strength_td"><div id="p_info_cell"><label id="p_strength"> </label></div></td>
                          </tr>
                          
                       
		                  </table>
		               </div>
		               
		               </td>
		            </tr>
		        </table>
		    </div>
		             
		               
		                
                     
                           
	             
	             
          
              
                       
                                
                                <div id="roster_loading">
                                <div id="load_text">LOADING</div>
                                 <div class="wheel2" id="load_spinner"></div>     
                                 <div class="wheel" id="load_spinner2"></div>                
                                </div>
                                
                                <div id="bl_roster">
                                 <!-- -----------ROSTER ---------- -->
                           <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center"  >
                            <tr>
                                <td>
                                    <div id="rosterName"></div>
                                </td>
                            </tr>
                            <tr height="95%">
                                <td>
                                    <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" id="draftRoster" >
                                      <tr height="25%">
                                        <td width="12%">
                                                                 <table  align="center" id="draftRosterQB" >
                                                                  <thead>
                                                                     <tr><td>QB (0/2)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                      
                                         <td width="12%">
                                           <table  align="center" id="draftRosterWR" >
                                                                  <thead>
                                                                     <tr><td>WR (0/4)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                         <td width="12%">
                                           <table  align="center" id="draftRosterRB" >
                                                                  <thead>
                                                                     <tr><td>RB (0/2)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                         <td width="12%">
                                        <table  align="center" id="draftRosterTE" >
                                                                  <thead>
                                                                     <tr><td>TE (0/2)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                         <td width="12%">
                                        <table align="center" id="draftRosterFB" >
                                                                  <thead>
                                                                     <tr><td>FB (0/1)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                     
                                      
                                     
                                      
                                         </tr>
                                         <tr height="25%">
                                            <td width="12%">
                                        <table  align="center" id="draftRosterC" >
                                                                  <thead>
                                                                     <tr><td>C (0/2)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                         
                                         <td width="12%">
                                        <table  align="center" id="draftRosterOT" >
                                                                  <thead>
                                                                     <tr><td>OT (0/4)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                         <td width="12%">
                                        <table  align="center" id="draftRosterOG" >
                                                                  <thead>
                                                                     <tr><td>OG (0/4)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                         <td width="12%">
                                        <table align="center" id="draftRosterDE" >
                                                                  <thead>
                                                                     <tr><td>DE (0/4)</td></tr>
                                                                  </thead>
                                                                  <tbbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbbody>
                                                                 </table>
                                        </td>
                                         <td width="12%">
                                         <table align="center" id="draftRosterCB" >
                                                                  <thead>
                                                                     <tr><td>CB (0/4)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                        
                                         </tr>
                                      
                                      <tr height="25%">
                                      
                                       <td width="12%">
                                        <table  align="center" id="draftRosterSS" >
                                                                  <thead>
                                                                     <tr><td>SS (0/2)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                     
                                         <td width="12%">
                                            <table  align="center" id="draftRosterFS" >
                                                                  <thead>
                                                                     <tr><td>FS (0/2)</td></tr>
                                                                  </thead>
                                                                  <tbbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbbody>
                                                                 </table>
                                        </td>
                                      
                                         <td width="12%">
                                        <table  align="center" id="draftRosterOLB" >
                                                                  <thead>
                                                                     <tr><td>OLB (0/4)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                         <td width="12%">
                                        <table  align="center" id="draftRosterMLB" >
                                                                  <thead>
                                                                     <tr><td>MLB (0/2)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbody>
                                                                 </table>
                                        </td>
                                         <td width="12%">
                                         <table  align="center" id="draftRosterDT" >
                                                                  <thead>
                                                                     <tr><td>DT (0/4)</td></tr>
                                                                  </thead>
                                                                  <tbody>
                                                                     <tr><td> </td></tr>
                                                                   </tbbody>
                                                                 </table>
                                        </td>
                                        
                                         
                                       
                                      </tr>
                                      <tr height="25%">
                                           <td width="12%">
                                            <table  align="center" id="draftRosterK" >
                                                                      <thead>
                                                                         <tr><td>Kicker (0/1)</td></tr>
                                                                      </thead>
                                                                      <tbody>
                                                                         <tr><td> </td></tr>
                                                                       </tbbody>
                                                                     </table>
                                            </td>
                                      
                                          <td width="12%">
                                            <table  align="center" id="draftRosterP" >
                                                                      <thead>
                                                                         <tr><td>Punter (0/1)</td></tr>
                                                                      </thead>
                                                                      <tbody>
                                                                         <tr><td> </td></tr>
                                                                       </tbody>
                                                                     </table>
                                            </td>
                                      
                                       <td width="12%">
                                        
                                        </td>
                                        <td width="12%">
                                        
                                        </td>
                                        <td width="12%">
                                        
                                        </td>
                                      </tr>
                                    </table>
    
                                
                                </td> 
                            </tr>
                             
                       </table>
                           <!-- ----------roster end---------- -->      
                                </div>
                                
                                
                                  <!-- --------------Players---------------- -->
                  
                  <div id="draftpblock">
                <div class="draftPlayersTabs">
                
                       <input id="dptab0" type="radio" name="tabs" checked>
                       <label for="dptab0" title="ALL">ALL</label>
                
                                 
                       <input id="dptab1" type="radio" name="tabs">
                       <label for="dptab1" title="QB">QB</label>

                       <input id="dptab2" type="radio" name="tabs">
                       <label for="dptab2" title="RB">RB</label>
 
                       <input id="dptab3" type="radio" name="tabs">
                       <label for="dptab3" title="DE">DE</label>
                       
                        <input id="dptab4" type="radio" name="tabs">
                       <label for="dptab4" title="WR">WR</label>
                       
                        <input id="dptab5" type="radio" name="tabs">
                       <label for="dptab5" title="CB">CB</label>
                       
                        <input id="dptab6" type="radio" name="tabs">
                       <label for="dptab6" title="OT">OT</label>
                       
                        <input id="dptab7" type="radio" name="tabs">
                       <label for="dptab7" title="MLB">MLB</label>
                       
                        <input id="dptab8" type="radio" name="tabs">
                       <label for="dptab8" title="OLB">OLB</label>
                       
                        <input id="dptab9" type="radio" name="tabs">
                       <label for="dptab9" title="SS">SS</label>
                       
                        <input id="dptab10" type="radio" name="tabs">
                       <label for="dptab10" title="FS">FS</label>
                       
                        <input id="dptab11" type="radio" name="tabs">
                       <label for="dptab11" title="OG">OG</label>
                       
                        <input id="dptab12" type="radio" name="tabs">
                       <label for="dptab12" title="DT">DT</label>
                       
                        <input id="dptab13" type="radio" name="tabs">
                       <label for="dptab13" title="TE">TE</label>
                       
                        <input id="dptab14" type="radio" name="tabs">
                       <label for="dptab14" title="C">C</label>
                       
                        <input id="dptab15" type="radio" name="tabs">
                       <label for="dptab15" title="FB">FB</label>
                       
                        <input id="dptab16" type="radio" name="tabs">
                       <label for="dptab16" title="K">K</label>
                       
                        <input id="dptab17" type="radio" name="tabs">
                       <label for="dptab17" title="P">P</label>
                                     
                       <section id="dpcontent0">
                    
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" id="draft_p_table" >
                      
                           <thead>

                      <tr>
                         <!--  <td width="3%"> № </div></td>  -->
                          <th width="15%">  player</th>        
                          <th width="5%">   position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    throw power </th>
                          <th width="5%">    throw accuracy </th>
                          <th width="5%">    kick power </th>
                          <th width="5%">    kick accuracy </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
           
                <tbody>
            <tr>
   
            </tr>
            
             <tr>
           
            </tr>
            </tbody>
            
                       </table>              
               
                       </section>
                       
                       
                       
                        <section id="dpcontent1">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center"  id="draft_p_table_QB" >
                      
                           <thead>

                      <tr>
                          
                           <th width="15%">   player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%">    throw power </th>
                          <th width="5%">    throw accuracy </th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>                 
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                       <section id="dpcontent2">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_RB" >
                      
                           <thead>

                      <tr>

                          <th width="15%">  player </th> 
                          
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
             
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                         <section id="dpcontent3">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_DE" >
                      
                           <thead>

                      <tr>
                          <th width="15%">   player </th> 
                        
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                       <section id="dpcontent4">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_WR" >
                      
                           <thead>

                      <tr>
                          <th width="15%">   player </th> 
                        
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                       <section id="dpcontent5">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_CB" >
                      
                           <thead>

                      <tr>
                         <th width="15%">  player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                             <section id="dpcontent6">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_OT" >
                      
                           <thead>

                      <tr>
                          <th width="15%">   player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                             <section id="dpcontent7">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_MLB" >
                      
                           <thead>

                      <tr>
                          <th width="15%">   player </th> 
                          
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                             <section id="dpcontent8">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_OLB" >
                      
                           <thead>

                      <tr>
                         <th width="15%">  player </th> 
                        
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                        <section id="dpcontent9">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_SS" >
                      
                           <thead>

                      <tr>
                         <th width="15%">   player </th> 
                          
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                        <section id="dpcontent10">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_FS" >
                      
                           <thead>

                      <tr>
                         <th width="15%">   player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                        <section id="dpcontent11">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_OG" >
                      
                           <thead>

                      <tr>
                         <th width="15%">   player </th> 
                       
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                           <section id="dpcontent12">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_DT" >
                      
                           <thead>

                      <tr>
                         <th width="15%">   player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                           <section id="dpcontent13">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_TE" >
                      
                           <thead>

                      <tr>
                          <th width="15%">   player </th> 
                          
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                           <section id="dpcontent14">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_C" >
                      
                           <thead>

                      <tr>
                          <th width="15%">   player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
       <section id="dpcontent15">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_FB" >
                      
                           <thead>

                      <tr>
                         <th width="15%">   player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
          
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                       <section id="dpcontent16">
                        <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_K" >
                      
                           <thead>

                      <tr>
                      
                      <th width="15%">   player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                           <th width="5%">    kick power </th>
                          <th width="5%">    kick accuracy </th>
                          <th width="5%">    throw power </th>
                          <th width="5%">    throw accuracy </th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
                      
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       
                       <section id="dpcontent17">
                       <table cellspacing="0" cellpadding="0" width="100%" height="100%" align="center" class="draft_p_table" id="draft_p_table_P" >
                      
                           <thead>

                      <tr>
                      
                      <th width="15%">   player </th> 
                         
                          <th width="5%">    position </th>
                          <th width="5%">    overall</th>
                           <th width="5%">    kick power </th>
                          <th width="5%">    kick accuracy </th>
                          <th width="5%">    throw power </th>
                          <th width="5%">    throw accuracy </th>
                          <th width="5%" >   speed</th>
                          <th width="5%">    agility </th>
                          <th width="5%">    awareness </th>
                          <th width="5%">    catching </th>
                          <th width="5%">    carrying </th>
                          <th width="5%">    tackling </th>
                          <th width="5%">    break tackle </th>
                          <th  width="5%">    jumping </th>
                          <th width="5%">    strength </th>
                      
                        </tr>
  
            </thead>
            <tbody>
            <tr>
            
            </tr>
            </tbody>
            
                       </table>
                       </section>
                       

               </div>    
                </div>
                  <!-- -------------players end------------- -->
</body>
</html>