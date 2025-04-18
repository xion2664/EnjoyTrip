package enjoytrip.user.model.service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import enjoytrip.user.model.UserDto;
import enjoytrip.user.model.dao.UserDao;
import enjoytrip.user.model.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private static UserService userService = new UserServiceImpl();
	private UserDao userDao;

	private UserServiceImpl() {
		userDao = UserDaoImpl.getInstance();
	}

	public static UserService getInstance() {
		return userService;
	}

	public boolean idCheck(String userId) throws SQLException {
		return userDao.idCheck(userId);
	}

	public UserDto login(String userId, String userPass) throws SQLException {
		return userDao.login(userId, userPass);
	}

	public boolean regist(UserDto user) throws SQLException {

		String encryptedPass = BCrypt.hashpw(user.getUserPass(), BCrypt.gensalt());
		user.setUserPass(encryptedPass);

		return userDao.regist(user);
	}

	public UserDto modify(UserDto user) throws SQLException {
		return userDao.modify(user);
	}

	public boolean Delete(String userId) throws SQLException {
		return userDao.Delete(userId);
	}

}
