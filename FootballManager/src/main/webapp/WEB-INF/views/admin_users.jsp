<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Prime time rivalry</title>
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/resources/script.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<script src="https://www.google.com/recaptcha/api.js" async defer></script>



</head>
<body>



  <table width="1000px" height="100%" align="center">
     <tr>
       <td width="250px">
        
   <%@include  file="admin_leftpan.jsp" %>
          
       </td>
        
  <!----------------------- BODY --------------------------------->
  <td width="750px" vertical-align="top">
     
<div id="iblock">

   <table width="95%" id="itable" vertical-align="top">
      <tr height="40px">
         <td width="20px"> <input type="text" id="search_name" size="20px" value="${search_name}"   onkeyup="if(/[^a-aZ-Z\s]/.test(this.value))this.value=this.value.replace(/[^a-zA-Z\s]+/g,'')"/> </td>
         <td align="left">  <div id="search_btn"><input type="button" value="SEARCH" onclick="searchUser()" class="sumbit_style" /></div> </td>
      </tr>
     
   </table>
<table width="100%" id="ttable" >
   <thead>

        <tr>
            <td><div id="theader" > username </div></td> 
        <td>  <div id="theader">User group </div></td> 
           <td>   <div id="theader">email </div></td>
          
        </tr>
   
  </thead>
  <tbody>
        <c:forEach var="user" items="${usersList}" >
        
            <tr onclick="show('${user.username}','${user.password}','${user.userGroup}','${user.email}')">
            
                <td><div id="tdata">${user.username} </div></td>
                <td><div id="tdata">${user.userGroup}</div></td>
                <td><div id="tdata">${user.email}</div></td>
     
            </tr>
           
           
        </c:forEach>

   </tbody>
 </table>

<div id="input_window">
<div id="iwheader"> <label id="i_username"> </label> </div>
     <table width="100%">
     
        <tr height=30px>
           <td width="40%" align="center"> Username </td>
           <td width="60%" align="center">  <input type="text" id="i_name"/> </td>
        </tr>
        
        <tr height=30px> 
          <td width="40%" align="center"> Password </td>
           <td width="60%" align="center">  <input type="text" id="i_password"/> </td>
        </tr>
        
        <tr height=30px>
           <td width="40%" align="center"> Group </td>
           <td width="60%" align="center">  <input type="text" id="i_group"/> </td>
        </tr>
        
        <tr height=30px>  
           <td width="40%" align="center"> Email </td>
           <td width="60%" align="center">  <input type="text" id="i_email"/> </td>
        </tr>
        
      
     
  </table>
  
  <div id="sumbit_btn"> 
  
    <table id="buttns" width="100%" height="40px" align="center">
               <tr>
                  <td width="60%"> <input type="button" onclick="deleteUser()" class="sumbit_style" value="delete"/> </td>
                  <td>  <input type="button" value="save"  onclick="editUser()" class="sumbit_style" /></td>
               </tr>
            </table>
  
  </div>
  
  
  
  
  <div class="close" title="Close" onclick="i_close()"></div>
</div>

</div>


   </td>      


<!----------------------- BODY END--------------------------------->  
   
  </tr>
</table>      
    
    
    
        <!--body end -->
        
        
   
                
              <!--footer end -->
</body>
</html>
