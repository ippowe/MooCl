package kr.co.moocl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.DeleteResult;

import kr.co.moocl.vo.ReviewVo;
import kr.co.moocl.vo.aReviewSearchVo;

@Component
public class aReviewSearchDao {
	
	@Autowired
	MongoTemplate mongotemplate;

	public List<aReviewSearchVo> findReviewListByReviewerId(String userid) {
	
//		Query query = new Query();
//		query.addCriteria( new Criteria().andOperator(
//			Criteria.where("_id.user_id").is(reviewerId),
//			Criteria.where("site").is("gurumi")
//		));
		
//		Query query = new Query();
//		query.addCriteria( 
//				Criteria.where("id.user_id").exists(true)
//				.andOperator(
//						Criteria.where("_id.user_id").is(reviewerId),
//						Criteria.where("site").is("gurumi")
//		));
		
		Query query = new Query();
		query.addCriteria( new Criteria().andOperator(
			Criteria.where("user_id").is(userid)			
		));
		
		query.with(new Sort(Sort.Direction.DESC, "reg_date"));
		
		System.out.println(" ::: byId ::: " + query.toString());
		
		List<aReviewSearchVo> reviewSearchList = mongotemplate.find(query, aReviewSearchVo.class, "test_review_update");
		
		return reviewSearchList;
	}

	public void delReview(String userid, String movieid) {
		
		Criteria criuser = new Criteria("_id.user_id");
		Criteria crimovie = new Criteria("_id.movie_id");
		
		criuser.is(userid);
		crimovie.is(movieid);
		
		Criteria cricon = new Criteria().andOperator(criuser, crimovie);
		Query q = new Query(cricon);
		
		mongotemplate.remove(q,aReviewSearchVo.class, "test_review_update");
		
	}

}
