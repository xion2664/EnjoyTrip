<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/include/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="static/css/mypage.css" />
    <title>enjoytrip!</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>

    <main>
      <section class="user-title">
        <h2>Enjoy, ${userInfo.userId} !</h2>
      </section>
      <section class="user-content">
        <form action="${root}/user" id="user-edit" method="post">
          <input type="hidden" name="action" value="modify"/>
          <input type="hidden" name="userId" value="${userInfo.userId}"/>
          <input type="text" name="userName" placeholder="UserName" />
          <input type="password" name="userPass" placeholder="UserPW" />
          <input type="password" name="newPW" />
          <input type="password" name="newPW2" />
          <input type="text" name="emailId" placeholder="UserEmailID" />
          <input type="text" name="emailDomain" placeholder="UserEmailDomain" />
          <button>완료</button>
        </form>
      </section>
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
    <script
      src="https://code.jquery.com/jquery-1.12.4.min.js"
      integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
      crossorigin="anonymous"
    ></script>
    <script
      type="text/javascript"
      src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=cqmvh7feg7"
    ></script>
    <script src="./static/js/card.js"></script>
    <script src="./static/js/markerClustering.js"></script>
    <script src="./static/js/marker.js"></script>
    <script src="./static/js/main.js"></script>
    <script src="./static/js/api.js"></script>
  </body>
</html>
