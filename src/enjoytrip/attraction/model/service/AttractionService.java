package enjoytrip.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import enjoytrip.attraction.model.AttractionDetail;
import enjoytrip.attraction.model.AttractionDto;

public interface AttractionService {
	AttractionDetail detail(int contentId) throws SQLException; 
	List<AttractionDto> listByContentTypeId(int contentTypeId) throws SQLException;
	List<AttractionDto> listByLatLng(float latitude, float longitude) throws SQLException;
}
