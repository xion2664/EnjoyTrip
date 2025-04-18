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
      <div class="mypage-container">
	      <section class="user-title">
	        <h2>Enjoy, ${userInfo.userId} !</h2>
	      </section>
	      <section class="user-content">
	        <div class="user-info">
		        <div class="user-info-name">ID</div>
		        <div class="user-info-value"> ${userInfo.userId}</div>
	        </div>
	        <div class="user-info">
		        <div class="user-info-name">사용자명</div>
		        <div class="user-info-value"> ${userInfo.userName}</div>
	        </div>
	        <div class="user-info">
	        	<div class="user-info-name">E-mail</div>
		        <div class="user-info-value">${userInfo.emailId}@${userInfo.emailDomain}</div>
	        </div>
	        <div class="user-info">
	        	<div class="user-info-name">가입일</div>
	        	<div class="user-info-value">${userInfo.joinDate}</div>
	        </div>
	      </section>
	      <section class="event-btn-section">
	        <a class="event-btn-modify" href="${root}/user?action=mvmodify">수정</a>
	        <a class="event-btn-delete" href="${root}/user?action=delete&userId=${userInfo.userId}">삭제</a>
	      </section>
      </div>
    </main>

    <%@ include file="/include/footer.jsp" %>
  </body>
</html>
