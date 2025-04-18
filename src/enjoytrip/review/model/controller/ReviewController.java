package enjoytrip.review.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enjoytrip.review.model.Review;
import enjoytrip.review.model.service.ReviewService;
import enjoytrip.review.model.service.ReviewServiceImpl;
import enjoytrip.util.ToJSON;

@WebServlet("/review")
public class ReviewController extends HttpServlet {
	private ReviewService reviewService;
	
	public void init() throws ServletException {
		 reviewService = ReviewServiceImpl.getReviewService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		try {
			if("get".equals(action)) {
				getListByContentId(req, resp);
			} else if("write".equals(action)) {
				write(req, resp);
			} else if("delete".equals(action)) {
				delete(req, resp);
			} else if("modify".equals(action)) {
				modify(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int reviewId = Integer.parseInt(req.getParameter("reviewId"));
		int contentId = Integer.parseInt(req.getParameter("contentId"));
		reviewService.deleteReview(reviewId);
		resp.sendRedirect(req.getContextPath() + "/attraction?action=detail&contentId=" + contentId);
	}

	private void write(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Review review = new Review();
		
		review.setUserId(req.getParameter("userId"));
		review.setContentId(Integer.parseInt(req.getParameter("contentId")));
		review.setContent(req.getParameter("content"));
		review.setTitle(req.getParameter("title"));
		review.setRate(Integer.parseInt(req.getParameter("rate")));
		
		if(review.getUserId() == null || "".equals(review.getUserId().trim())) return;
		reviewService.writeReview(review);
		
		resp.sendRedirect(req.getContextPath() + "/attraction?action=detail&contentId=" + review.getContentId());
	}

	private void getListByContentId(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int contentId = Integer.parseInt(req.getParameter("contentId"));
		List<Review> reviewList = reviewService.listReview(contentId);
		
		resp.setContentType("application/json;charset=utf-8"); 
		resp.getWriter().print(ToJSON.reviewDtoListToJSON(reviewList));
	}
	
	private void modify(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Review review = new Review();
		
		review.setReviewId(Integer.parseInt(req.getParameter("reviewId")));
		review.setContentId(Integer.parseInt(req.getParameter("contentId")));
		review.setContent(req.getParameter("content"));
		review.setTitle(req.getParameter("title"));
		review.setRate(Integer.parseInt(req.getParameter("rate")));
		
		reviewService.modifyReview(review);
		
		resp.sendRedirect(req.getContextPath() + "/attraction?action=detail&contentId=" + review.getContentId());
	}
}
