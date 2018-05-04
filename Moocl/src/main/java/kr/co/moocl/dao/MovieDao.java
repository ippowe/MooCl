package kr.co.moocl.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import kr.co.moocl.vo.InteMovieVo;


@Component
public class MovieDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieDao.class);
		
	public List<InteMovieVo> getMovieInfoList(String keyword) {
			
		Query query = new Query().with(new Sort(Sort.Direction.DESC, "movie_id"));
		
		query.limit(12);  //12개씩 불러오고 스크롤 내리면 더불러오게 나중에 코딩
		
		query.addCriteria(Criteria.where("inte_title").regex(keyword));
		logger.info(query.toString());		
		List<InteMovieVo> queryResult = mongoTemplate.find(query, InteMovieVo.class, "movie_info"); 
	
			
		logger.info("queryResylt: " + queryResult);
		
		return queryResult;
	}

	public InteMovieVo getMovieInfoById(String movieId) {
		
		Criteria criteria = new Criteria("_id");
		criteria.is(movieId);
		
		Query query = new Query(criteria);
		
		InteMovieVo rawMovieData = mongoTemplate.findOne(query, InteMovieVo.class, "movie_info");
		
		return rawMovieData;
	}
}
