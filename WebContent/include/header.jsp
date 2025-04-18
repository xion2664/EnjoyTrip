<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${pageContext.request.contextPath}"/>
<style>
	.menu-space {
		display: flex;
		align-items: center;
	}
	
	.menu-icon {
		line-height : 0;
		margin : 0 10px;
	}
	
	.login {
		font-size: 20px;
	    color: white;
	    line-height: 24px;
	}
</style>
<header>
  <nav>
    <div class="logo-space">
      <a href="${root}/index.jsp"> <img src="static/img/icon/logo.svg" id="header-logo" alt="로고" /> </a>
    </div>
    <div class="menu-space">
      <a href="#" class="menu-icon">
        <img src="static/img/icon/search_white.svg" alt="검색" width="30" />
      </a>
      <a href="save.html" class="menu-icon">
        <img src="static/img/icon/heart_white.svg" alt="찜" width="33" />
      </a>
      <c:choose>
      	<c:when test="${empty userInfo}">
	      <a href="${root}/user?action=mvlogin" class="menu-icon login">Login</a>
	    </c:when>
	    <c:otherwise>
	      <div class="menu-icon" id="dropdown">
	        <img src="static/img/icon/user_white.svg" alt="내정보" width="30" />
	        <div class="dropdown-content">
	          <a href="${root}/user?action=mypage">My page</a>
	          <a href="${root}/user?action=logout">Log out</a>
	        </div>
	      </div>
	    </c:otherwise>
	  </c:choose>
    </div>
  </nav>
</header>
