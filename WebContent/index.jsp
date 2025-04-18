<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/include/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="static/css/index.css" />
    <link rel="stylesheet" type="text/css" href="static/css/info.css" />
    <title>enjoytrip!</title>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>

    <main>
      <section class="ad-space"></section>
      <!-- <section class="category-space"></section> -->
      <section>	
        <div class="map-content">
          <div class="selectLocation-space">
            <div class="location" data-lat="37.5665" data-lng="126.9777">서울특별시</div>
            <div class="location" data-lat="37.4550" data-lng="126.7031">인천광역시</div>
            <div class="location" data-lat="36.3503" data-lng="127.3845">대전광역시</div>
            <div class="location" data-lat="35.1595" data-lng="126.8526">광주광역시</div>
            <div class="location" data-lat="35.8714" data-lng="128.6014">대구광역시</div>
            <div class="location" data-lat="35.5384" data-lng="129.3114">울산광역시</div>
            <div class="location" data-lat="35.1796" data-lng="129.0756">부산광역시</div>
          </div>
          <div class="map-space"></div>
        </div>
        <div class="place-content">
          <div class="selectCategory-space">
            <a href="#" class="category">
              <img src="static/img/icon/관광지-deactivate.svg" alt="" />
              <span>-</span>
              <span>관광지</span>
            </a>
            <a href="#" class="category">
              <img src="static/img/icon/문화시설-deactivate.svg" alt="" />
              <span>-</span>
              <span>문화시설</span>
            </a>
            <a href="#" class="category">
              <img src="static/img/icon/축제공연행사-deactivate.svg" alt="" />
              <span>-</span>
              <span>축제·공연·행사</span>
            </a>
            <a href="#" class="category">
              <img src="static/img/icon/여행코스-deactivate.svg" alt="" />
              <span>-</span>
              <span>여행코스</span>
            </a>
            <a href="#" class="category">
              <img src="static/img/icon/레포츠-deactivate.svg" alt="" />
              <span>-</span>
              <span>레포츠</span>
            </a>
            <a href="#" class="category">
              <img src="static/img/icon/쇼핑-deactivate.svg" alt="" />
              <span>-</span>
              <span>쇼핑</span>
            </a>
            <a href="#" class="category">
              <img src="static/img/icon/음식점-deactivate.svg" alt="" />
              <span>-</span>
              <span>음식점</span>
            </a>
            <a href="#" class="category">
              <img src="static/img/icon/숙박-deactivate.svg" alt="" />
              <span>-</span>
              <span>숙박</span>
            </a>
          </div>
          <div class="place-space">
          </div>
        </div>
      </section>
    </main>

	<%@ include file="/include/footer.jsp" %>
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
