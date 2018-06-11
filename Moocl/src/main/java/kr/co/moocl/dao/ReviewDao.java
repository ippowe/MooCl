package kr.co.moocl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

		List<ReviewVo> reviewList = mongoTemplate.find(query, ReviewVo.class, "test_review");
		
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
		
		List<ReviewVo> reviewList = mongoTemplate.find(query, ReviewVo.class, "test_review");
		
		return reviewList;
	}
	
	public long getReviewCount(String movieId, int scores) {
// 		db.review.find({ "_id.movie_ref.$id" : movieId, "user_grade" : score, review_contents : {$ne : ""}}, {"_id" : 0}).count()
		
		Criteria criteria = new Criteria("_id.movie_ref.$id");
		criteria.is(movieId);
		criteria.and("user_grade").is(scores);
		criteria.and("review_contents").ne("");
	
		
		Query query = new Query(criteria);
		
		long reviewCount = mongoTemplate.count(query, ReviewVo.class, "test_review");
		
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
		query.skip((pageNo-1) * 10);
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "reg_date"));
		query.fields().exclude("_id");
		
		List<ReviewVo> reviewList = mongoTemplate.find(query, ReviewVo.class, "test_review");
		
		return reviewList;
	}
	// save test method
	//[ReviewVo [_id=null, movie_id=49503, site=daum, user_id=Qmh0VmM몽골소년, review_contents=80/0.5, reg_date=2018.04.23, user_grade=8], ReviewVo [_id=null, movie_id=49503, site=daum, user_id=NnZnOWULSM, review_contents=볼만해, reg_date=2018.04.06, user_grade=6], ReviewVo [_id=null, movie_id=49503, site=daum, user_id=OW5SeDc임지욱, review_contents=., reg_date=2018.03.24, user_grade=7], ReviewVo [_id=null, movie_id=49503, site=daum, user_id=UHc5eQ=제테크, review_contents=와우!!!!!!이런 아이언맨인데 한쿡이름 철남자, reg_date=2018.02.18, user_grade=8], ReviewVo [_id=null, movie_id=49503, site=daum, user_id=QzlxYlM니나노, review_contents=완벽한 명작!!!! 아이언맨의 기적!!!, reg_date=2018.01.13, user_grade=10]]
	public void saveReview(Map<String, Object> _id, String movieId, String site, String userId, String reviewContents, String regDate,int userGrade) {

		ReviewVo saveVo = new ReviewVo(_id,movieId,site,userId,reviewContents,regDate ,userGrade);
		mongoTemplate.save(saveVo, "test_review");
	}
	
	public void updatePosReview(String commonId, String userId, List<String[]> pos_analyze , List<String> gurumi_word ) {

		Query query = new Query();
		query.addCriteria( new Criteria().andOperator(
				Criteria.where("_id.user_id").is(userId),
				Criteria.where("_id.movie_ref.$id").is(commonId)
		));
//		System.out.println(mongoTemplate.find(query, TestReviewVo.class, "test_review"));
		Update update = new Update();
		update.set("pos_analyze", pos_analyze);
		update.set("gurumi_word", gurumi_word);
		
		mongoTemplate.updateFirst(query, update,"test_review");
		System.out.println(mongoTemplate.find(query, ReviewVo.class, "test_review"));
	}
}
