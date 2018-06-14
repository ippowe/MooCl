package kr.co.moocl.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import kr.co.moocl.vo.ReviewVo;

@Component
public class aReviewSearchDao {
	
	@Autowired
	MongoTemplate mongoTemplate;

	public List<ReviewVo> findReviewListByReviewerId(String userid) {

		Query query = new Query();
		query.addCriteria( new Criteria().andOperator(
			Criteria.where("_id.user_id").is(userid),	
			Criteria.where("site").is("gurumi")
		));
		
		query.with(new Sort(Sort.Direction.DESC, "reg_date"));
		
		List<ReviewVo> reviewSearchList = mongoTemplate.find(query, ReviewVo.class, "test_review");
		
		return reviewSearchList;
	}

	public int delReview(String userId, List<String> movieIdList) {
		
		Criteria criuser = new Criteria("_id.user_id");
		Criteria crimovie = new Criteria("_id.movie_ref.$id");
		
		criuser.is(userId);
		crimovie.in(movieIdList);
		
		Criteria cricon = new Criteria().andOperator(criuser, crimovie);
		Query q = new Query(cricon);
		
		int count = (int) mongoTemplate.remove(q, ReviewVo.class, "test_review").getDeletedCount();

		return count;
	}
}
