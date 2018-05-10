package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.MovieDao;
import kr.co.moocl.vo.InteMovieVo;
import kr.co.moocl.vo.PeopleVo;

@Service
public class MainService {

	@Autowired
	MovieDao movieDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieDao.class);
	
	public InteMovieVo modifyForDetail(String movieId) {
		
		logger.info("modify: " + movieId);
		
		InteMovieVo rawMovieData = movieDao.getMovieInfoById(movieId);
		AggregationResults<PeopleVo> result = movieDao.getPersonInfo(movieId);
		List<PeopleVo> tempPeople = result.getMappedResults();
		
		List<Object> tempPersonList = new ArrayList<>();
		for(int i=0; i<tempPeople.size(); i++) {
			tempPersonList.add(tempPeople.get(i).getPerson());
		}
		
		rawMovieData.setPerson(tempPersonList);
	
		logger.info("rawData: " +rawMovieData);

		return rawMovieData;
	}
	

}
