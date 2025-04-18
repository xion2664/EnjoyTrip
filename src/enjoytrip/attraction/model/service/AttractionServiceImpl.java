package enjoytrip.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import enjoytrip.attraction.model.AttractionDetail;
import enjoytrip.attraction.model.AttractionDto;
import enjoytrip.attraction.model.dao.AttractionDao;
import enjoytrip.attraction.model.dao.AttractionDaoImpl;

public class AttractionServiceImpl implements AttractionService {
	private static AttractionService attractionService = new AttractionServiceImpl();
	private AttractionDao attractionDao;
	
	private AttractionServiceImpl() {
		attractionDao = AttractionDaoImpl.getInstance();
	}
	
	public static AttractionService getInstance() {
		return attractionService;
	}
	
	public AttractionDetail detail(int contentId) throws SQLException {
		AttractionDetail detail = attractionDao.detail(contentId);
		if(detail == null) return null;
		
		String telname = detail.getTelname();
		if(telname == null || telname.equals("")) detail.setTelname(detail.getTel());
		
		String addr1 = detail.getAddr1();
		if(addr1 == null || addr1.equals("")) detail.setAddr1(detail.getAddr2());
		
		String firstImage = detail.getFirstImage();
		if(firstImage == null || firstImage.equals("")) detail.setFirstImage(detail.getFirstImage2());
		
		return detail;
	}

	public List<AttractionDto> listByContentTypeId(int contentTypeId) throws SQLException {
		return attractionDao.listByContentTypeId(contentTypeId);
	}

	public List<AttractionDto> listByLatLng(float latitude, float longitude) throws SQLException {
		return attractionDao.listByLatLng(latitude, longitude);
	}

}
