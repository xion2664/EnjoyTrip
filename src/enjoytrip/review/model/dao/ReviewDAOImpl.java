package enjoytrip.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enjoytrip.review.model.Review;
import enjoytrip.util.DBUtil;

public class ReviewDAOImpl implements ReviewDAO {

	private static ReviewDAO reviewDao;
	private DBUtil db;

	private ReviewDAOImpl() {
		db = DBUtil.getInstance();
	}

	public static ReviewDAO getReviewDAO() {
		if (reviewDao == null)
			reviewDao = new ReviewDAOImpl();
		return reviewDao;
	}

	@Override
	public void writeReview(Review review) throws SQLException {
		try (
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
						"insert into reviews(content_id, user_id, title, content, rate) "
					  + "values(?, ?, ?, ?, ?) "
				);
		) {
			int index = 1;
			stmt.setInt(index++, review.getContentId());
			stmt.setString(index++, review.getUserId());
			stmt.setString(index++, review.getTitle());
			stmt.setString(index++, review.getContent());
			stmt.setInt(index++, review.getRate());
			stmt.executeUpdate();
		}
	}

	@Override
	public List<Review> listReview(int contentId) throws SQLException {
		List<Review> list = new ArrayList<>();
		
		try (
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
						"select content_id, review_id, title, publish_date, user_id, rate, content "
					  + "from reviews "
					  + "where content_id = ? "
					  + "order by review_id desc "
				);
		) {
			stmt.setInt(1, contentId);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Review review = new Review();
				review.setContentId(rs.getInt("content_id"));
				review.setReviewId(rs.getInt("review_id"));
				review.setTitle(rs.getString("title"));
				review.setPublishDate(rs.getString("publish_date"));
				review.setUserId(rs.getString("user_id"));
				review.setRate(rs.getInt("rate"));
				review.setContent(rs.getString("content"));
				list.add(review);
			}
		}

		return list;
	}

	@Override
	public void modifyReview(Review review) throws SQLException {
		try (
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
						"update reviews "
					  + "set title = ?, content = ?, rate = ? "
					  + "where review_id = ? "
				);
		) {
			int index = 1;
			stmt.setString(index++, review.getTitle());
			stmt.setString(index++, review.getContent());
			stmt.setInt(index++, review.getRate());
			stmt.setInt(index++, review.getReviewId());
			stmt.executeUpdate();
		}
	}

	@Override
	public void deleteReview(int reviewId) throws SQLException {
		try (
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
						"delete from reviews "
					  + "where review_id = ? "
				);
		) {
			stmt.setInt(1, reviewId);
			stmt.executeUpdate();
		}
	}
}
