package enjoytrip.attraction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enjoytrip.attraction.model.AttractionDetail;
import enjoytrip.attraction.model.AttractionDto;
import enjoytrip.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao {
	private static AttractionDao attractionDao = new AttractionDaoImpl();
	private DBUtil db;
	
	private AttractionDaoImpl() {
		db = DBUtil.getInstance();
	}
	
	public static AttractionDao getInstance() {
		return attractionDao;
	}
	
	public AttractionDetail detail(int contentId) throws SQLException {
		try(
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					  "select "
					  + "  info.content_id"
					  + ", info.title"
					  + ", info.addr1" 
					  + ", info.addr2"
					  + ", info.tel"
					  + ", des.telname"
					  + ", info.first_image"
					  + ", info.first_image2"
					  + ", des.homepage"
					  + ", des.overview"
					  + " from attraction_info as info, attraction_description as des "
					  + " where info.content_id = ?"
					  + " and info.content_id = des.content_id");
		) {
			stmt.setInt(1, contentId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				AttractionDetail detail = new AttractionDetail();
				detail.setContentId(rs.getInt("content_id"));
				detail.setTitle(rs.getString("title"));
				detail.setAddr1(rs.getString("addr1"));
				detail.setAddr2(rs.getString("addr2"));
				detail.setTel(rs.getString("tel"));
				detail.setTelname(rs.getString("telname"));
				detail.setFirstImage(rs.getString("first_image"));
				detail.setFirstImage2(rs.getString("first_image2"));
				detail.setHomepage(rs.getString("homepage"));
				detail.setOverview(rs.getString("overview"));
				
				return detail;
			}
		}
		
		return null;
	}

	public List<AttractionDto> listByContentTypeId(int contentTypeId) throws SQLException {
		try(
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
						  "select content_id, content_type_id, title, addr1, addr2, "
						  + "zipcode, tel, first_image, first_image2, readcount, "
						  + "sido_code, gugun_code, latitude, longitude, mlevel "
						+ "from attraction_info "
						+ "where content_type_id = ? "
						+ "limit 50");
			) {
				stmt.setInt(1, contentTypeId);
				ResultSet rs = stmt.executeQuery();
				
				List<AttractionDto> attrList = new ArrayList<>();
				while(rs.next()) {
					AttractionDto attr = new AttractionDto();
					attr.setContentId(rs.getInt("content_id"));
					attr.setContentTypeId(rs.getInt("content_type_id"));
					attr.setTitle(rs.getString("title"));
					attr.setAddr1(rs.getString("addr1"));
					attr.setAddr2(rs.getString("addr2"));
					attr.setZipcode(rs.getString("zipcode"));
					attr.setTel(rs.getString("tel"));
					attr.setFirstImage(rs.getString("first_image"));
					attr.setFirstImage2(rs.getString("first_image2"));
					attr.setReadcount(rs.getInt("readcount"));
					attr.setSidoCode(rs.getInt("sido_code"));
					attr.setGugunCode(rs.getInt("gugun_code"));
					attr.setLatitude(rs.getFloat("latitude"));
					attr.setLongitude(rs.getFloat("longitude"));
					attr.setMlevel(rs.getString("mlevel"));
					
					attrList.add(attr);
				}
				
				return attrList;
			}
	}

	public List<AttractionDto> listByLatLng(float latitude, float longitude) throws SQLException {
		try(
				Connection con = db.getConnection();
				PreparedStatement stmt = con.prepareStatement(
						  "select content_id, content_type_id, title, addr1, addr2, "
						  + "zipcode, tel, first_image, first_image2, readcount, "
						  + "sido_code, gugun_code, latitude, longitude, mlevel "
						+ "from attraction_info "
						+ "where latitude < ? and latitude > ? and longitude < ? and longitude > ? "
						+ "limit 100");
			) {
				int idx = 1;
				stmt.setFloat(idx++, latitude + 0.1f);
				stmt.setFloat(idx++, latitude - 0.1f);
				stmt.setFloat(idx++, longitude + 0.1f);
				stmt.setFloat(idx++, longitude - 0.1f);
				ResultSet rs = stmt.executeQuery();
				
				List<AttractionDto> attrList = new ArrayList<>();
				while(rs.next()) {
					AttractionDto attr = new AttractionDto();
					attr.setContentId(rs.getInt("content_id"));
					attr.setContentTypeId(rs.getInt("content_type_id"));
					attr.setTitle(rs.getString("title"));
					attr.setAddr1(rs.getString("addr1"));
					attr.setAddr2(rs.getString("addr2"));
					attr.setZipcode(rs.getString("zipcode"));
					attr.setTel(rs.getString("tel"));
					attr.setFirstImage(rs.getString("first_image"));
					attr.setFirstImage2(rs.getString("first_image2"));
					attr.setReadcount(rs.getInt("readcount"));
					attr.setSidoCode(rs.getInt("sido_code"));
					attr.setGugunCode(rs.getInt("gugun_code"));
					attr.setLatitude(rs.getFloat("latitude"));
					attr.setLongitude(rs.getFloat("longitude"));
					attr.setMlevel(rs.getString("mlevel"));
					
					attrList.add(attr);
				}
				
				return attrList;
			}
	}

}
