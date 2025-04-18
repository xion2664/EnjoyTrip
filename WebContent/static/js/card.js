let cardData = [];
let dataIdx = 0;

function reloadPlaceCards(data) {
  let placeSpace = document.querySelector(".place-space");
  placeSpace.innerHTML = "";

  cardData = data;
  dataIdx = 10;

  data.forEach((place) => {
    if (!place.firstimage && !place.firstimage2) return;
    placeSpace.appendChild(makePlaceCard(place.title, place.addr1 || place.addr2, place.firstimage || place.firstimage2, place.contentid));
  });
}

function makePlaceCard(title, locate, image, contentid) {
  let card = document.createElement("a");
  card.className = "place";
  card.href = `/enjoytrip/attraction?action=detail&contentId=${contentid}`;

  card.innerHTML = `
  <div class="placeImg">
    <div class="heart">  
      <img src="static/img/icon/heart_white.svg" alt="" />
    </div>
    <div class="cover">
      <div class="title-cover">
        <div class="title">${title}</div>
        <div>────</div>
        <div class="locate">${title}</div>
      </div>
    </div>
    <img src=${image ? image : "static/img/festival/부산국제락페스티벌.jpeg"} alt="" />
  </div>
  `;

  return card;
}
