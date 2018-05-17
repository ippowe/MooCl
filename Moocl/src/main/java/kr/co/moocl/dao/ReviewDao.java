package kr.co.moocl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import kr.co.moocl.vo.ReviewVo;

@Component
public class ReviewDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<ReviewVo> findReviewsByMovieId(String movieId){
		Criteria criteria = new Criteria("_id.movie_ref.$id");
		criteria.is(movieId);
		
		Query query = new Query(criteria);
		query.fields().exclude("_id");
		System.out.println(query.toString());
		List<ReviewVo> reviewList = mongoTemplate.find(query, ReviewVo.class, "review");
		
		return reviewList;
	}
	
	public List<ReviewVo> findByMovieIdSortDate(String movieId, int page){
//		db.review.find({ "_id.movie_ref.$id" : movieId, review_contents : {$ne : ""}}
//		, {"_id" : 0}).skip((page-1)*4).limit(5).sort({"reg_date" : -1})
		
		Query query = new Query();
		query.addCriteria( new Criteria().andOperator(
				Criteria.where("review_contents").ne(""),
				Criteria.where("_id.movie_ref.$id").is(movieId)
		));
		
		query.skip((page-1) * 5);
		query.limit(5);
		query.with(new Sort(Sort.Direction.DESC, "reg_date"));
		query.fields().exclude("_id");
		
		List<ReviewVo> reviewList = mongoTemplate.find(query, ReviewVo.class, "review");
		
		return reviewList;
	}
	
	public long getReviewCount(String movieId) {
// 		db.review.find({ "_id.movie_ref.$id" : movieId, review_contents : {$ne : ""}}, {"_id" : 0}).count()

		Criteria criteria = new Criteria("_id.movie_ref.$id");
		criteria.is(movieId);
		criteria.and("review_contents").ne("");
		
		Query query = new Query(criteria);
		
		long reviewCount = mongoTemplate.count(query, ReviewVo.class, "review");
		
		return reviewCount;
	}
		
	public List<ReviewVo> testDao() {
		return null;
	}

	public List<ReviewVo> findByIdPageScore(String movieId, int pageNo, List<Integer> scores) {
//		db.review.find({ "_id.movie_ref.$id" : "movieId", "user_grade" : score, review_contents : {$ne : ""}}
//		, {"_id" : 0}).skip((pageNo-1)*5).limit(5).sort({"reg_date" : -1})		
		
		Criteria criteria = new Criteria("_id.movie_ref.$id");
		criteria.is(movieId);
		criteria.and("user_grade").in(scores);
		criteria.and("review_contents").ne("");
		
		Query query = new Query(criteria);
		query.skip((pageNo-1) * 5);
		query.limit(5);
		query.with(new Sort(Sort.Direction.DESC, "reg_date"));
		query.fields().exclude("_id");
		
		List<ReviewVo> reviewList = mongoTemplate.find(query, ReviewVo.class, "review");
		
		System.out.println(reviewList);
		return reviewList;
	}
}
