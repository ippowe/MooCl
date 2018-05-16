package kr.co.moocl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<InteMovieVo> getRelatedMovies(Map<String, Object> personId){
		
		int stringPersonId = Integer.parseInt(personId.get("personId").toString());				
		
		List<InteMovieVo> relatedMovieList = movieDao.getRelatedMoviesByPersonId(stringPersonId);
				 
				
		return relatedMovieList;
	}

}
