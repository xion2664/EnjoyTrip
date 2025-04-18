package enjoytrip.user.model.service;

import java.sql.SQLException;

import enjoytrip.user.model.UserDto;

public interface UserService {
	boolean idCheck(String userId) throws SQLException;
	UserDto login(String userId, String userPass) throws SQLException;
	boolean regist(UserDto user) throws SQLException;
	UserDto modify(UserDto user) throws SQLException;
	boolean Delete(String userId) throws SQLException;
}
