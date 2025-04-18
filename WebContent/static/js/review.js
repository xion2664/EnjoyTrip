function getReviewList(contentId) {
	fetch(`http://localhost:8080/enjoytrip/review?action=get&contentId=${contentId}`)
	  .then((resp) => resp.json())
	  .then((data) => {
		  console.log(data);
	    let reviewList = document.querySelector(".reviewList");
	    reviewList.innerHTML = "";
	    data["data"].forEach((review) => {
	      reviewList.innerHTML += `<div class="review">
	        <div class="reviewTop">
	          <div class="titleContent">
	            <div class="title">${review.title}</div>
	            <div class="info">
	              <span class="date">${review.publishdate}</span>
	              <span class="name">${review.userid}</span>
	            </div>
	          </div>
	          <div class="subContent">
	            <div class="btns">
	              <a href="#">
	                <img src="static/img/icon/edit.svg" alt="" id="edit" />
	              </a>
	              <a href="#">
	                <img src="static/img/icon/delete.svg" alt="" id="delete" />
	              </a>
	            </div>
	            <div class="rate">${review.rate}</div>
	          </div>
	        </div>
	        <div class="reviewBtm">${review.content}</div>
	      </div>`;
	    });
	  });
}

