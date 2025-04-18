<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/include/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="static/css/detail.css" />
    <link rel="stylesheet" type="text/css" href="static/css/review.css" />
    <title>enjoytrip!</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>

    <main>
      <section class="mainContent">
        <div class="mainInfo">
          <div class="title">
            <h1>${detail.title}</h1>
            <hr />
          </div>
          <div class="phone">
            <img src="static/img/icon/call.svg" alt="" class="icon" />
            ${detail.telname}
          </div>
          <div class="page">
            <a href="#">
              <img src="static/img/icon/site.svg" alt="" class="icon" />
              ${detail.homepage}
            </a>
          </div>
          <div class="address">
            <img src="static/img/icon/address.svg" alt="" class="icon" />
            ${detail.addr1}
          </div>
          <div class="info">${detail.overview}</div>
        </div>
      </section>
      <section class="imgs">
        <img src="${detail.firstImage}" alt="" id="imgs" />
      </section>
      <section class="subContent">
        <div class="subInfo">
          <hr />
        </div>
      </section>

      <%@ include file="/include/review.jsp" %>
    </main>

    <%@ include file="/include/footer.jsp" %>
  </body>
</html>
