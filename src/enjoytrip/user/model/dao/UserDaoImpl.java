package enjoytrip.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import enjoytrip.user.model.UserDto;
import enjoytrip.util.DBUtil;

public class UserDaoImpl implements UserDao {
	private static UserDao userDao = new UserDaoImpl();
	DBUtil db;
	
	private UserDaoImpl() {
		db = DBUtil.getInstance();
	}
	
	public static UserDao getInstance() {
		return userDao;
	}
	
	public boolean idCheck(String userId) throws SQLException {
		try(
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(
				  "select user_id "
				+ "from users "
				+ "where user_id = ?"
			)
		) {
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) return false;
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public UserDto login(String userId, String userPass) throws SQLException {
		try(
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
					  "select user_id, user_pass, user_name, join_date, email_id, email_domain "
					+ "from users "
					+ "where user_id = ? "
				)
			) {
				int idx = 1;
				stmt.setString(idx++, userId);
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){
					UserDto user = new UserDto();
					
					user.setUserId(rs.getString("user_id"));
					user.setUserPass(rs.getString("user_pass"));
					user.setUserName(rs.getString("user_name"));
					user.setJoinDate(rs.getString("join_date"));
					user.setEmailId(rs.getString("email_id"));
					user.setEmailDomain(rs.getString("email_domain"));
					
					if(!BCrypt.checkpw(userPass, user.getUserPass()))
						return null;
					
					return user;
				}
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		
		return null;
	}

	public boolean regist(UserDto user) throws SQLException {
		try(
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
					  "insert into users(user_id, user_name, user_pass, email_id, email_domain) "
					+ "values (?, ?, ?, ?, ?) "
				)
			) {
				int idx = 1;
				stmt.setString(idx++, user.getUserId());
				stmt.setString(idx++, user.getUserName());
				stmt.setString(idx++, user.getUserPass());
				stmt.setString(idx++, user.getEmailId());
				stmt.setString(idx++, user.getEmailDomain());
				
				int result = stmt.executeUpdate();
				if(result == 1) return true;
				else return false;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}

	public UserDto modify(UserDto user) throws SQLException {
		try(
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
					  "update users "
					+ "set user_pass = ?, user_name = ?, email_id = ?, email_domain = ? "
					+ "where user_id = ?"
				)
			) {
				int idx = 1;
				stmt.setString(idx++, user.getUserPass());
				stmt.setString(idx++, user.getUserName());
				stmt.setString(idx++, user.getEmailId());
				stmt.setString(idx++, user.getEmailDomain());
				
				stmt.setString(idx++, user.getUserId());
				int result = stmt.executeUpdate();
				if(result == 1) return user;
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		
		return null;
	}

	public boolean Delete(String userId) throws SQLException {
		try(
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
					  "delete from users "
					+ "where user_id = ?"
				)
			) {
				stmt.setString(1, userId);
				int result = stmt.executeUpdate();
				
				if(result == 1) return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		
		return false;
	}
}
