package enjoytrip.review.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enjoytrip.review.model.Review;
import enjoytrip.review.model.dao.ReviewDAO;
import enjoytrip.review.model.dao.ReviewDAOImpl;

public class ReviewServiceImpl implements ReviewService {

	private static ReviewService reviewService = new ReviewServiceImpl();
	private ReviewDAO reviewDao;

	private ReviewServiceImpl() {
		reviewDao = ReviewDAOImpl.getReviewDAO();
	}

	public static ReviewService getReviewService() {
		return reviewService;
	}

	@Override
	public void writeReview(Review review) throws SQLException {
		reviewDao.writeReview(review);
	}

	@Override
	public List<Review> listReview(int contentId) throws SQLException {
		return reviewDao.listReview(contentId);
	}

	@Override
	public void modifyReview(Review review) throws SQLException {
		reviewDao.modifyReview(review);
	}

	@Override
	public void deleteReview(int reviewId) throws SQLException {
		reviewDao.deleteReview(reviewId);
	}
}
