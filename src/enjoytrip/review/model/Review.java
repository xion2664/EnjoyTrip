package enjoytrip.review.model;

public class Review {
	private int reviewId;
	private int contentId;
	private String userId;
	private String title;
	private String content;
	private int rate;
	private String publishDate;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int reviewId, int contentId, String userId, String title, String content, int rate,
			String publishDate) {
		super();
		this.reviewId = reviewId;
		this.contentId = contentId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.rate = rate;
		this.publishDate = publishDate;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "ReviewDTO [reviewId=" + reviewId + ", contentId=" + contentId + ", userId=" + userId + ", title="
				+ title + ", content=" + content + ", rate=" + rate + ", publishDate=" + publishDate + "]";
	}
}
