package enjoytrip.attraction.model.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enjoytrip.attraction.model.AttractionDetail;
import enjoytrip.attraction.model.AttractionDto;
import enjoytrip.attraction.model.service.AttractionService;
import enjoytrip.attraction.model.service.AttractionServiceImpl;
import enjoytrip.util.ToJSON;

@WebServlet("/attraction")
public class AttractionController extends HttpServlet {
	private AttractionService attractionService;
	
	public void init() throws ServletException {
		attractionService = AttractionServiceImpl.getInstance();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		try {
			if("detail".equals(action)) {
				detail(req, resp);
			} else if("getListByContentTypeId".equals(action)) {
				getListByContentTypeId(req, resp);
			} else if("getListByLatLng".equals(action)) {
				getListByLatLng(req, resp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void getListByLatLng(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		float lat = Float.parseFloat(req.getParameter("lat"));
		float lng = Float.parseFloat(req.getParameter("lng"));
		
		List<AttractionDto> list = attractionService.listByLatLng(lat, lng);
		
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(ToJSON.attractionDtoListToJSON(list));
	}

	private void getListByContentTypeId(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int contentTypeId = Integer.parseInt(req.getParameter("contentTypeId"));
		
		List<AttractionDto> list = attractionService.listByContentTypeId(contentTypeId);
		
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(ToJSON.attractionDtoListToJSON(list));
	}

	private void detail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int contentId = Integer.parseInt(req.getParameter("contentId"));
		AttractionDetail detail = attractionService.detail(contentId);
		
		req.setAttribute("contentId", contentId);
		req.setAttribute("detail", detail);
		req.getRequestDispatcher("/attraction/detail.jsp").forward(req, resp);
	}
}
