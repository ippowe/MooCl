package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.MovieDao;
import kr.co.moocl.dao.PersonDao;
import kr.co.moocl.vo.InteMovieVo;

@Service
public class PersonService {
	
	@Autowired
	PersonDao personDao;
	
	@Autowired
	MovieDao movieDao;
	
	public List<Document> getRelatedMovies(Map<String, Object> movieId){
		
		String stringMovieId = movieId.get("movieId").toString();				

		List<Document> tempMovieList = movieDao.getRelatedMoviesByMovieId(stringMovieId);

		 
				
		return tempMovieList;
	}

}
