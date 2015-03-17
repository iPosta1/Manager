<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Prime time rivalry</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<script src="resources/script.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

</head>
<body>

  <!--left pan start -->
          <div id="leftPan">
             <a href="${pageContext.request.contextPath}/admin/users"> <div id="adminPanHead">
                USERS
              </div> </a>
              <a href="${pageContext.request.contextPath}/admin/leagues"">
                 <div id="adminPanHead"> 
                     Leagues
                   </div> </a>
            <a href="/players">
                 <div id="adminPanHead"> 
                 Players
              </div> </a>

          </div>
            <!--left pan end -->

</body>
</html>