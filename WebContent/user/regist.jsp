<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/include/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="static/css/register.css" />
    <title>enjoytrip!</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>
    <main>
      <form action="${root}/user" method="POST">
      	<input type="hidden" name="action" value="regist">
        <input id="nickname" name="userName" placeholder="Nickname" />
        <input id="login" name="userId" placeholder="ID" />
        <input type="password" name="userPass" id="password" placeholder="Password" />
        <input type="password" id="password-check" placeholder="Password Check" />
        <input type="hidden" name="emailId" value="ssafy"/>
        <input type="hidden" name="emailDomain" value="ssafy.com"/>
        <button id="register-btn">Regist</button>
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
    <script src="./static/js/register.js"></script>
  </body>
</html>
