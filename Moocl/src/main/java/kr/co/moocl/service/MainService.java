package kr.co.moocl.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.MovieDao;
import kr.co.moocl.vo.InteMovieVo;

@Service
public class MainService {

	@Autowired
	MovieDao movieDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieDao.class);
	
	public InteMovieVo modifyForDetail(String movieId) {
		
		logger.info("modify: " + movieId);
		
		InteMovieVo rawMovieData = movieDao.getMovieInfoById(movieId);
		
		logger.info("rawData: " +rawMovieData);

		return rawMovieData;
	}

}
