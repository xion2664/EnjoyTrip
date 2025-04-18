package enjoytrip.user.model.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enjoytrip.user.model.UserDto;
import enjoytrip.user.model.service.UserService;
import enjoytrip.user.model.service.UserServiceImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private UserService userService;
	
	public void init() throws ServletException {
		userService = UserServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		try {
			if("mvlogin".equals(action)) {
				mvlogin(req, resp);
			} else if("login".equals(action)) {
				login(req, resp);
			} else if("idcheck".equals(action)) {
				idcheck(req, resp);
			} else if("mypage".equals(action)) {
				mypage(req, resp);
			} else if("mvmodify".equals(action)) { 
				mvmodify(req, resp);
			} else if("modify".equals(action)) {
				modify(req, resp);
			} else if("delete".equals(action)) {
				delete(req, resp);
			} else if("mvregist".equals(action)) {
				mvregist(req, resp);
			} else if("regist".equals(action)) {
				regist(req, resp);
			} else if("logout".equals(action)) {
				logout(req, resp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getSession().removeAttribute("userInfo");
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}

	private void regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		UserDto user = new UserDto();
		
		user.setUserId(req.getParameter("userId"));
		user.setUserPass(req.getParameter("userPass"));
		user.setUserName(req.getParameter("userName"));
		user.setEmailDomain(req.getParameter("emailDomain"));
		user.setEmailId(req.getParameter("emailId"));
		
		String path =  req.getContextPath() + "/user?action=mvregist";
		if(userService.regist(user)) {
			path = req.getContextPath() + "/user?action=mvlogin";
		}
		
		resp.sendRedirect(path);
	}

	private void mvregist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/user/regist.jsp").forward(req, resp);		
	}

	private void mypage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/user/mypage.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userId = req.getParameter("userId");
		boolean result = userService.Delete(userId);
		
		String path = req.getContextPath() + "/user?action=mypage";
		
		if(result) {
			req.getSession().removeAttribute("userInfo");
			path = req.getContextPath() + "/index.jsp";
		}
		
		resp.sendRedirect(path);
	}

	private void mvmodify(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/user/modify.jsp").forward(req, resp);
	}

	private void modify(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		UserDto user = new UserDto();
	
		user.setUserId(req.getParameter("userId"));
		user.setUserPass(req.getParameter("userPass"));
		user.setUserName(req.getParameter("userName"));
		user.setEmailDomain(req.getParameter("emailDomain"));
		user.setEmailId(req.getParameter("emailId"));
		
		UserDto modifiedUser = userService.modify(user);
	
		String path = req.getContextPath() + "/user?action=mvmodify";
		if(modifiedUser != null) {
			req.getSession().setAttribute("userInfo", modifiedUser);
			path = req.getContextPath() + "/user?action=mypage";
		}
		
		resp.sendRedirect(path);
	}

	private void idcheck(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String checkId = req.getParameter("checkId");
		boolean result = userService.idCheck(checkId);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print("{ \"isDuplicated\" : " + (result ? "\"false\"" : "\"true\"") + " }");
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userId = req.getParameter("userId");
		String userPass = req.getParameter("userPass");
		
		UserDto user = userService.login(userId, userPass);
		
		String path = req.getContextPath() + "/user?action=mvlogin";
		if(user != null) {
			req.getSession().setAttribute("userInfo", user);
			path = req.getContextPath() + "/index.jsp";
		}
		
		resp.sendRedirect(path);
	}

	private void mvlogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/user/login.jsp").forward(req, resp);;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}
}
