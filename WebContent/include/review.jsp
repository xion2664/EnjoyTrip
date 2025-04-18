<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<script src="static/js/review.js"></script>
<script>
  getReviewList(${contentId});
</script>
<section class="reviewContent">
  <form
    action="${root}/review?action=write"
    class="review-insert"
    method="post"
  >
    <input type="hidden" name="contentId" value="${contentId}" />
    <input type="hidden" name="userId" value="${userInfo.userId}" />
    <input type="hidden" name="rate" value="3" />
    <input
      class="inputText"
      type="text"
      name="title"
      placeholder="리뷰 제목"
      required
    />
    <textarea
      class="inputText"
      name="content"
      placeholder="리뷰 내용"
      rows="7"
      required
    ></textarea>
    <div class="btnSpace">
      <!-- <div class="starSpace">
        <a href="#" id="s1">
          <img src="${root}/static/img/icon/star.svg" alt="" />
        </a>
        <a href="#" id="s2">
          <img src="${root}/static/img/icon/star.svg" alt="" />
        </a>
        <a href="#" id="s3">
          <img src="${root}/static/img/icon/star.svg" alt="" />
        </a>
        <a href="#" id="s4">
          <img src="${root}/static/img/icon/star.svg" alt="" />
        </a>
        <a href="#" id="s5">
          <img src="${root}/static/img/icon/star.svg" alt="" />
        </a>
      </div> -->
      <button id="registBtn">등록</button>
    </div>
    <hr />
  </form>

  <div class="reviewList">
    <div class="review">
      <div class="reviewTop">
        <div class="titleContent">
          <div class="title"><h4>${review.title}</h4></div>
          <div class="info">
            <span class="date">${review.publishDate}</span>
            <span class="name"><b>${review.userId}</b></span>
          </div>
        </div>
        <div class="subContent">
          <div class="btns">
            <a href="#">
              <img src="${root}/static/img/icon/edit.svg" alt="" id="edit" />
            </a>
            <a href="#">
              <img
                src="${root}/static/img/icon/delete.svg"
                alt=""
                id="delete"
              />
            </a>
          </div>
          <div class="rate">${review.rate}</div>
        </div>
      </div>
      <div class="reviewBtm">평점 : ${review.content}점</div>
      <hr />
    </div>
  </div>
</section>
