package kr.co.moocl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import kr.co.moocl.vo.MongoVo;
import kr.co.moocl.vo.MovieVo;

@Component
public class TestDao {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public MovieVo testMongoDB() {
		Criteria criteria = new Criteria("movie_title");  
		criteria.is("플란다스의 개");
		
		Query query = new Query(criteria); 		 // qeury 내용   name = sue
		
		return mongoTemplate.findOne(query, MovieVo.class);
	}
	
	public void testMovieVo() {
		
		List<MovieVo> movieList = mongoTemplate.findAll(MovieVo.class);		
		System.out.println(movieList);
	}

}
