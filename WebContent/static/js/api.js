let API_KEY =
  "j%2Bvccgt54dyu3d%2F3%2FCFGMtzOBCB0Mp1DWjE0Ch0FZUbmfYFCfpkWSbVdqpMED1mF%2FUdLFHRWRC9VkY%2FIa%2F9r2A%3D%3D";
let radius = 3000;
let numOfRows = 100;
let pageNo = 1;

function getUrlNearbyInfo(API_KEY, radius, numOfRows, pageNo, lat, lng) {
  return `http://apis.data.go.kr/B551011/KorService1/locationBasedList1?ServiceKey=${API_KEY}&contentTypeId=&mapX=${lng}&mapY=${lat}&radius=${radius}&listYN=Y&MobileOS=ETC&MobileApp=AppTest&arrange=A&numOfRows=${numOfRows}&pageNo=${pageNo}`;
}

async function getNearbyInfo(searchLat, searchLng) {
  searchLat = Number.parseFloat(+searchLat).toFixed(4);
  searchLng = Number.parseFloat(+searchLng).toFixed(4);
//  if (cache[searchLat] && cache[searchLat].indexOf(searchLng) > -1) return;

  if (!cache[searchLat]) cache[searchLat] = [];

//  let request = await fetch(
//    getUrlNearbyInfo(API_KEY, radius, numOfRows, pageNo, searchLat, searchLng)
//  );
  let request = await fetch(`http://localhost:8080/enjoytrip/attraction?action=getListByLatLng&lat=${searchLat}&lng=${searchLng}`);
  let response = await request.json();

  response["data"].forEach((item) => {
    markerSet.set(item.contentid, { "lat" : item.lat, "lng" : item.lng, "contentTypeId" : item.contenttypeid, "title" : item.title, "addr" : item.addr1 || item.addr2, "tel": item.tel, "firstimage" :  item.firstimage1 || item.firstimage2, "contentid" : item.contentid });
    makeMarker(item.lat, item.lng, item.title, item.contenttypeid, item.addr1 || item.addr2, item.tel, item.firstimage1 || item.firstimage2, item.contentid);
  });

  cache[searchLat].push(searchLng);

  markerClustering._redraw();

  localStorage.setItem("markerSet", JSON.stringify(Array.from(markerSet.entries())));
  localStorage.setItem("cache", JSON.stringify(cache));
}

document.querySelectorAll(".location").forEach((location) => {
  location.addEventListener("click", () => {
    getNearbyInfo(location.dataset.lat, location.dataset.lng);
  });
});

getNearbyInfo(currentPos.lat, currentPos.lng);

let prevTimeOut = undefined;

naver.maps.Event.addListener(map, "bounds_changed", (bounds) => {
  let center = map.getCenter();
  currentPos = { lat: center._lat, lng: center._lng };
  sessionStorage.setItem("currentPos", JSON.stringify(currentPos));

  if (prevTimeOut) clearTimeout(prevTimeOut);
  prevTimeOut = setTimeout(() => {
    getNearbyInfo(center._lat, center._lng);
  }, 300);
});
