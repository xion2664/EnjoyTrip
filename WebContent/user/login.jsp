<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="static/img/icon/favicon.svg" />
    <%@ include file="/include/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="static/css/login.css" />
    <title>enjoytrip!</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>

    <main>
      <form action="${root}/user" method="POST">
      	<input type="hidden" name="action" value="login"/>
        <a href="${root}/user?action=mvregist" id="register">need register?</a>
        <input id="login" name="userId" placeholder="ID" />
        <input type="password" name="userPass" id="password" placeholder="Password" />
        <button id="login-btn">login</button>
      </form>
    </main>

    <footer>
      <img src="static/img/icon/logo.svg" alt="로고" id="footerLogo" />
      <div class="sns-space">
        <a href="#" class="sns-icon">
          <img src="static/img/icon/instagram_white.svg" alt="인스타그램" />
        </a>
        <a href="#" class="sns-icon">
          <img src="static/img/icon/twitter_white.svg" alt="트위터" />
        </a>
        <a href="#" class="sns-icon">
          <img src="static/img/icon/facebook_white.svg" alt="페이스북" />
        </a>
      </div>
      <p>SSAFY &copy; 2023 황정민 박시연</p>
    </footer>

    <script src="./static/js/login.js"></script>
  </body>
</html>