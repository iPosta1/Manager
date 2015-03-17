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
</head>
<body>

<table cellspacing="0" cellpadding="0" width="100%" height="100%" >
    <tr height="100%">
        <td width="25%">
           <div class="gameRosterTabs"> 
            
                 <input id="gtab1" type="radio" name="tabs" checked>
                 <label for="gtab1" title="Offense">Offense</label>
        
                  <input id="gtab2" type="radio" name="tabs">
                  <label for="gtab2" title="Defense">Defense</label>
         
                  <input id="gtab3" type="radio" name="tabs">
                  <label for="gtab3" title="Special Team">Special Team</label>
         
                    <section id="gcontent1">
                        
                       <table cellspacing="0" cellpadding="0" width="100%"  >
                                <tr>
                                   <td>
                                        <table cellspacing="0" cellpadding="0" width="100%" id="gameQBs" >
                                            <thead>
                                                <tr>
                                                    <td>QB</td>
                                                    <td>OVR</td>
                                                    <td>Balance</td>
                                                    
                                                </tr>
                                             </thead>
                                             <tbody>
                                                <tr>
                                                     <td>
                                                     Aaron Rodgers
                                                    </td>
                                                     <td>
                                                      98
                                                    </td>
                                                     <td>
                                                      Good
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                   </td>
                               </tr>
                               
                               <tr>
                                   <td>
                                        <table cellspacing="0" cellpadding="0" width="100%" id="gameWRs" >
                                            <thead>
                                                <tr>
                                                    <td>WR</td>
                                                    <td>OVR</td>
                                                    <td>Balance</td>
                                                    
                                                </tr>
                                             </thead>
                                             <tbody>
                                                <tr>
                                                     <td>
                                                     Dez Bryant
                                                    </td>
                                                     <td>
                                                      97
                                                    </td>
                                                     <td>
                                                      Good
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                   </td>
                               </tr>
                         </table>
                     </section>
           </div>
   
        </td>
          <td width="50%">
	            <table cellspacing="0" cellpadding="0"  height="100%" width="100%">
	                <tr height="5%">
		                <td width="100%">
		                   <div id="time_style">TImE</div>
		                </td>
	                </tr>
	                 
	                <tr height="5%">
		                <td>
		                    <table cellspacing="0" cellpadding="0" >
		                        <tr>
		                             <td>team1</td>
		                             <td>0</td>
		                             <td>0</td>
		                             <td>team2</td>
		                        </tr>
		                    </table>
		                </td>
	                </tr>
	                
	                <tr height="40%">
		                <td>
		                <div id="field"> GAME Fgggejlgggg11111</div>
		                </td>
	                </tr>
	                
	                <tr height="40%">
		                <td>
		                COMBINATION
		                </td>
	                </tr>
	                
	                <tr height="10%">
		                <td>
		                   BUTTONS
		                </td>
	                </tr>
	            </table>

   
   
        </td>
        
        
        <td width="25%">
           <div id="draftroster">
             QQQQQDSAD
           </div>
   
        </td>
  
  

       <!--       
        <td width="50%">
        <div id="gameroster">  
            <table cellspacing="0" cellpadding="0"  height="100%" width="50%">
                <tr height="5%">
                <td width="100%">
                   <div id="time_style">TImE</div>
                </td>
                </tr>
                 
                <tr height="5%">
                <td>
                    <table cellspacing="0" cellpadding="0" >
                        <tr>
                             <td>team1</td>
                             <td>0</td>
                             <td>0</td>
                             <td>team2</td>
                        </tr>
                    </table>
                </td>
                </tr>
                
                <tr height="40%">
                <td>
                <div id="field"> GAME FIELD1111ytujtyujrhejltjjj1hgggggggggggggggggggggejltjjj1hgggggggggggggggggggggejltjjj1hgggggggggggggggggggggejltjjj1hgggggggggggggggggggggejltjjj1hgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg11111</div>
                </td>
                </tr>
                
                <tr height="40%">
                <td>
                COMBINATION
                </td>
                </tr>
                
                <tr height="10%">
                <td>
                   BUTTONS
                </td>
                </tr>
            </table>
            </div>
        </td>
        
        <td width="25%">
        <div id="gameroster">  
            <table cellspacing="0" cellpadding="0"  height="100%" id="gameInfo" >
                <tr><td>Brady throws to Nelson for 17 yards</td></tr>
            </table>
            </div>
        </td>
         --> 
    </tr>
</table>

</body>
</html>