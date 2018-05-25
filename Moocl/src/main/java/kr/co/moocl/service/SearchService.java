package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.MovieDao;
import kr.co.moocl.vo.InteMovieVo;

@Service
public class SearchService {

	@Autowired
	MovieDao movieDao;
	
	public List<Map<String, Object>> inteSearchResult(String keyword){
		
		List<InteMovieVo> daoResult = movieDao.getMovieInfoList(keyword);
		System.out.println(daoResult);
		
		List<Map<String, Object>> searchResult = new ArrayList<>();
		
		
		for(int i=0; i<daoResult.size(); i++) {
			String movieTitle = daoResult.get(i).getMovie_title();
			Object posterUrl = daoResult.get(i).getPoster();
//			String movieRate = daoResult.get(i).getMovie_rate(); 영화 등급 가져오기
			String movieId = daoResult.get(i).get_id();
			String inteTitle = daoResult.get(i).getInte_title(); // 사이트별 통합을 위해서 띄어쓰기, 특수문자 지운 영화제목
			List<Map<String, Object>> score = daoResult.get(i).getScore();
			
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
