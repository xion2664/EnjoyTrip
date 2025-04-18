package enjoytrip.review.model.dao;

import java.sql.SQLException;
import java.util.List;

import enjoytrip.review.model.Review;

public interface ReviewDAO {

	void writeReview(Review review) throws SQLException;

	List<Review> listReview(int contentId) throws SQLException;

	void modifyReview(Review review) throws SQLException;

	void deleteReview(int reviewId) throws SQLException;
}
