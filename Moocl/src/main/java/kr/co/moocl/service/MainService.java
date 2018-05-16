package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.MovieDao;
import kr.co.moocl.dao.PersonDao;
import kr.co.moocl.vo.InteMovieVo;
import kr.co.moocl.vo.PeopleVo;

@Service
public class MainService {

	@Autowired
	MovieDao movieDao;
	@Autowired
	PersonDao personDao;
	
	public InteMovieVo modifyForDetail(String movieId) {
		
		InteMovieVo rawMovieData = movieDao.getMovieInfoById(movieId);
		
		List<Object> temp_personId = new ArrayList<>(); 
		for(int i=0; i<rawMovieData.getPerson().size(); i++ ) {
			temp_personId.add(rawMovieData.getPerson().get(i).getPerson_id());
		}
		
		List<Object> pplListHasImg = personDao.getPersonInfoByPplList(temp_personId);
		
		for(int j=0; j<pplListHasImg.size(); j++) {
			String tempImg = ((PeopleVo) pplListHasImg.get(j)).getPeople_img();
			rawMovieData.getPerson().get(j).setPeople_img(tempImg);
		}

		return rawMovieData;
	}
	
	public List<Document> testService() {

		return null;
	}
	

}
