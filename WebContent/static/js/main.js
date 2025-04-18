// 페이지 로드시 localStroage에 존재하는 마커들을 지도에 표시
Array.from(markerSet.entries()).forEach((pair) => {
  let value = pair[1];
  makeMarker(
    value.lat,
    value.lng,
    value.title,
    value.contentTypeId,
    value.addr,
    value.tel,
    value.firstimage,
    value.contentid
  );
});

markerClustering._redraw();

// 지역메뉴에서 특정 지역 선택시에 해당 지역으로 이동
document.querySelectorAll(".location").forEach((location) => {
  location.addEventListener("click", () => {
    map.setCenter(
      new naver.maps.LatLng(location.dataset.lat, location.dataset.lng)
    );
    map.setZoom(13);
  });
});

// 관광타입 클릭시 스타일 변경 및 마커 필터링
let convertIdx = [12, 14, 15, 25, 28, 38, 39, 32];
let categories = document.querySelectorAll(".category");

categories.forEach((el, idx) => {
  el.addEventListener("mousedown", (e) => {
    document.querySelectorAll(".category").forEach((category, i) => {
      category.querySelectorAll("span").forEach((span) => {
        span.style.color = "var(--sub-gray)";
      });
      category.querySelector("img").src =
        markerIcon[convertIdx[i]].slice(0, -4) + "-deactivate.svg";
    });

    categories[idx].querySelectorAll("span").forEach((span) => {
      span.style.color = "var(--main-red)";
    });

    categories[idx].querySelector("img").src =
      markerIcon[convertIdx[idx]].slice(0, -4) + "-activate.svg";

    fetch(`http://localhost:8080/enjoytrip/attraction?action=getListByContentTypeId&contentTypeId=${convertIdx[idx]}`).then(data => data.json()).then(data => {reloadPlaceCards(data["data"])});
    reloadMarkers(convertIdx[idx]);
    markerClustering.setMap(null);
  });
});

//place.title, place.locate, place.image, place.contentid
