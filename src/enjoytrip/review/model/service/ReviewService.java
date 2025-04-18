package enjoytrip.review.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enjoytrip.review.model.Review;

public interface ReviewService {

	void writeReview(Review review) throws SQLException;

	List<Review> listReview(int contentId) throws SQLException;

	void modifyReview(Review review) throws SQLException;

	void deleteReview(int reviewId) throws SQLException;
}
