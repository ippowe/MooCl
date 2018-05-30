package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<PeopleVo> tempPersonList = rawMovieData.getPerson();
		
		if(tempPersonList == null) {
			
			temp_personId = null;
		} else { 
			
			for(int i=0; i<tempPersonList.size(); i++ ) {
				temp_personId.add(tempPersonList.get(i).getPerson_id());
			}
			
			List<PeopleVo> pplListHasImg = personDao.getPersonInfoByPplList(temp_personId);
			
			for(int j=0; j<pplListHasImg.size(); j++) {
				String tempImg = "";
				if(pplListHasImg.get(j) == null) {
					tempImg = "hasNoImg";
				} else {
					tempImg =  pplListHasImg.get(j).getPeople_img();	
				}
				rawMovieData.getPerson().get(j).setPeople_img(tempImg);
			}
		}
		return rawMovieData;
	}
	
	public List<Document> testService() {

		return null;
	}

	public List<Map<String, Object>> getMovieList(List<String> movieIds) {
		
		List<InteMovieVo> movieList = new ArrayList<>();
		List<Map<String, Object>> searchResult = new ArrayList<>();
		
		movieList = movieDao.getMovieInfoList(movieIds);
		
		for(int i=0; i<movieList.size(); i++) {
			String movieTitle = movieList.get(i).getMovie_title();
			Object posterUrl = movieList.get(i).getPoster();
//			String movieRate = movieList.get(i).getMovie_rate(); 영화 등급 가져오기
			String movieId = movieList.get(i).get_id();
			String inteTitle = movieList.get(i).getInte_title(); // 사이트별 통합을 위해서 띄어쓰기, 특수문자 지운 영화제목
			List<Map<String, Object>> score = movieList.get(i).getScore();
			
			Map<String, Object> simpleMovieInfo = new HashMap<>();
			simpleMovieInfo.put("movieTitle", movieTitle);
			simpleMovieInfo.put("posterUrl", posterUrl);
//			simpleMovieInfo.put("movieRate", movieRate);
			simpleMovieInfo.put("movieId", movieId);
			simpleMovieInfo.put("inteTitle", inteTitle);
			simpleMovieInfo.put("score", score);
			
			searchResult.add(simpleMovieInfo);
			
		}
				
		return searchResult;
	}
	

}
