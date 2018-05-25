package kr.co.moocl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;

import kr.co.moocl.dao.aReviewSearchDao;
import kr.co.moocl.vo.ReviewVo;
import kr.co.moocl.vo.aReviewSearchVo;

@Service
public class aReviewSearchService {

	@Autowired
	aReviewSearchDao areviewsearchdao;
	
	public List<aReviewSearchVo> reviewSearchResult(String userid) {
		
		List<aReviewSearchVo> reviewSearchList = areviewsearchdao.findReviewListByReviewerId(userid);
		
		return reviewSearchList ;
	}

	public void delReview(String userid, String movieid) {
				
		areviewsearchdao.delReview(userid, movieid);
				
	}

}
