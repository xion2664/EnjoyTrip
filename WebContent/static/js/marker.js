var htmlMarker1 = {
    content:
      '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(./static/img/cluster/cluster-marker-1.png);background-size:contain;"></div>',
    size: N.Size(40, 40),
    anchor: N.Point(20, 20),
  },
  htmlMarker2 = {
    content:
      '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(./static/img/cluster/cluster-marker-2.png);background-size:contain;"></div>',
    size: N.Size(40, 40),
    anchor: N.Point(20, 20),
  },
  htmlMarker3 = {
    content:
      '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(./static/img/cluster/cluster-marker-3.png);background-size:contain;"></div>',
    size: N.Size(40, 40),
    anchor: N.Point(20, 20),
  },
  htmlMarker4 = {
    content:
      '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(./static/img/cluster/cluster-marker-4.png);background-size:contain;"></div>',
    size: N.Size(40, 40),
    anchor: N.Point(20, 20),
  },
  htmlMarker5 = {
    content:
      '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(./static/img/cluster/cluster-marker-5.png);background-size:contain;"></div>',
    size: N.Size(40, 40),
    anchor: N.Point(20, 20),
  };

let markerIcon = {
  12: "./static/img/icon/관광지.svg",
  14: "./static/img/icon/문화시설.svg",
  15: "./static/img/icon/축제공연행사.svg",
  25: "./static/img/icon/여행코스.svg",
  28: "./static/img/icon/레포츠.svg",
  32: "./static/img/icon/숙박.svg",
  38: "./static/img/icon/쇼핑.svg",
  39: "./static/img/icon/음식점.svg",
};

let currentPos = JSON.parse(sessionStorage.getItem("currentPos")) || {
  lat: 36.3551675,
  lng: 127.2979977,
};

let mapOptions = {
  center: new naver.maps.LatLng(currentPos.lat, currentPos.lng), // 현재 위치 (삼성화재 유성캠퍼스)
  zoom: 13,
  scaleControl: true,
  mapDataControl: true,
  mapTypeControl: false,
  zoomControl: false,
};

const mapSpace = document.querySelector(".map-space");
const map = new naver.maps.Map(mapSpace, mapOptions);

let currentContentTypeId = 0;

let markers = [];
let infoWindows = [];

let markerMap = new Map();
let markerSet =
  new Map(JSON.parse(localStorage.getItem("markerSet"))) || new Map();
let cache = JSON.parse(localStorage.getItem("cache")) || {};

var markerClustering = new MarkerClustering({
  minClusterSize: 1,
  maxZoom: 15,
  map: map,
  markers: markers,
  disableClickZoom: false,
  gridSize: 120,
  icons: [htmlMarker1, htmlMarker2, htmlMarker3, htmlMarker4, htmlMarker5],
  indexGenerator: [10, 100, 200, 500, 1000],
  stylingFunction: function (clusterMarker, count) {
    $(clusterMarker.getElement()).find("div:first-child").text(count);
  },
});

function makeMarker(
  lat,
  lng,
  title,
  contentTypeId,
  addr,
  tel,
  firstimage,
  contentid
) {
	
  if(markerMap.get(contentid)) return;
  let markerOption = {
    position: new naver.maps.LatLng(lat, lng),
    map: map,
    icon: {
      content: `<img src=${
        markerIcon[contentTypeId]
          ? markerIcon[contentTypeId]
          : "./static/img/icon/관광지.svg"
      } width="24" height="24"></img>`,
      size: new naver.maps.Size(24, 24),
      origin: new naver.maps.Point(0, 0),
      anchor: new naver.maps.Point(12, -12),
    },
  };

  let marker = new naver.maps.Marker(markerOption);
  let infoWindow = new naver.maps.InfoWindow({
    content: `<div class="info-window">
    <div class="info-window-title">${title}<div/>
    <div class="info-row-container">
      <div class="info-img-container">
        <img src="${
          firstimage ? firstimage : "./static/img/icon/no-Image.svg"
        }" width="80px" height="60px"/>
      </div>
      <div class="info-container">
        <div>주소 : ${addr ? addr : "정보가 없습니다"}</div>
        <div>전화번호 : ${tel ? tel : "정보가 없습니다"}</div>
      <div>
    <div/>
    <div class="info-marker">마커를 클릭하면 상세페이지로 이동합니다</div>
   </div>`,
  });

  markerMap.set(contentid, marker);
  markers.push(marker);
  infoWindows.push(infoWindow);

  if (currentContentTypeId && contentTypeId != currentContentTypeId)
    hideMarker(marker);

  naver.maps.Event.addListener(marker, "mouseover", () => {
    if (infoWindow.getMap()) infoWindow.close();
    else infoWindow.open(map, marker);
  });

  naver.maps.Event.addListener(marker, "mouseout", () => {
    if (infoWindow.getMap()) infoWindow.close();
  });

  naver.maps.Event.addListener(marker, "click", () => {
    location.href = `/enjoytrip/attraction?action=detail&contentId=${contentid ? contentid : 0}`;
  });
}

function reloadMarkers(contentTypeId) {
  currentContentTypeId = contentTypeId;

  Array.from(markerSet.entries()).forEach((pair) => {
    let key = pair[0];
    let value = pair[1];

    if (value.contentTypeId == contentTypeId) showMarker(markerMap.get(key));
    else hideMarker(markerMap.get(key));
  });
}

function showMarker(marker) {
  if (marker.getMap()) return;
  marker.setMap(map);
}

function hideMarker(marker) {
  if (!marker.getMap()) return;
  marker.setMap(null);
}
// 관광지 - 12
// 문화시설 - 14
// 축제공연행사 - 15
// 여행코스 - 25
// 레포츠 - 28
// 숙박 - 32
// 쇼핑 - 38
// 음식점 - 39

// 지역별 좌표
// 서울 특별시 : 37.56658580121631, 126.9777104192369
// 대전 광역시 : 36.35035288777408, 127.38459043689537
// 울산 광역시 : 35.5384, 129.3114
// 광주 광역시 : 35.1595, 126.8526
// 대구 광역시 : 35.8714, 128.6014
// 부산 광역시 : 35.1796, 129.0756
// 인천 광역시 : 37.4550, 126.7031
