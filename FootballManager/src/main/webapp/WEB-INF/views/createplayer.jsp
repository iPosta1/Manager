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
</head>
<body>

<div id="create_player">
<form:form method="post" action="addplayer" commandName="player" id="create_player_form" onsubmit="validatePlayerForm(); return false;" >
            <table width="100%" height="100%">
              <tr height="20px"> 
                  <td width="35%"> firstname </td>
                  <td width="30%"><form:input path="firstname" id="frstname" /> </td>
              </tr>
              
                <tr height="20px"> 
                  <td width="35%"> lastname </td>
                  <td width="30%"><form:input path="lastname" id="lstname" /> </td>
              </tr>
              
                 <tr height="20px"> 
                  <td width="35%"> birthdate yyyy-mm-dd </td>
                  <td width="30%"><form:input path="birthdate" type="text" id="brthdate" /> </td>
              </tr>
              
                 <tr height="20px"> 
                  <td width="35%"> height </td>
                  <td width="30%"><form:input path="height" id="hght" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> weight </td>
                  <td width="30%"><form:input path="weight" id="wght" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> default position </td>
                  <td width="30%"><form:input path="defaultPosition" id="defposition" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> photo </td>
                  <td width="30%"><form:input path="photo" id="phto" /> </td>
              </tr>
              
                 <tr height="20px"> 
                  <td width="35%"> overall </td>
                  <td width="30%"><form:input path="ovr" id="ovrall" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> speed </td>
                  <td width="30%"><form:input path="speed" id="spd" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> agility </td>
                  <td width="30%"><form:input path="agility" id="agil" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> awareness </td>
                  <td width="30%"><form:input path="awareness" id="awar" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> catching </td>
                  <td width="30%"><form:input path="catching" id="ctching" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> carrying </td>
                  <td width="30%"><form:input path="carrying" id="crring" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> tackling </td>
                  <td width="30%"><form:input path="tackling" id="tckling" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> break tackle </td>
                  <td width="30%"><form:input path="breakTackle" id="brktackle" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> jumping </td>
                  <td width="30%"><form:input path="jumping" id="jmp" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> throw power </td>
                  <td width="30%"><form:input path="throwPower" id="thrpower" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> throw accuracy </td>
                  <td width="30%"><form:input path="throwAccuracy" id="thraccuracy" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> kick Power </td>
                  <td width="30%"><form:input path="kickPower" id="kkpower" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> kick accuracy </td>
                  <td width="30%"><form:input path="kickAccuracy" id="kkaccuracy" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> strength </td>
                  <td width="30%"><form:input path="strength" id="str" /> </td>
              </tr>
                 <tr height="20px"> 
                  <td width="35%"> stamina </td>
                  <td width="30%"><form:input path="stamina" id="stamin" /> </td>
              </tr>
            </table>
              
               <div id="sumbit_btn">
               <table width="30px" height="40px" align="center">
                 <tr>
                   <td>
                       <input type="submit" value="SUBMIT" class="sumbit_style" /> </td>
                 </tr>
               </table>
              </div>
         </form:form>
         </div>

</body>
</html>