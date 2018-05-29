package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.aReviewSearchDao;
import kr.co.moocl.vo.ReviewVo;

@Service
public class aReviewSearchService {

	@Autowired
	aReviewSearchDao areviewsearchdao;
	
	public List<ReviewVo> reviewSearchResult(String userId) {
		
		List<ReviewVo> reviewSearchList = areviewsearchdao.findReviewListByReviewerId(userId);
		
		return reviewSearchList ;
	}

	public int delReview(List<Map<String, Object>> reviewList) {
		
		String userId = (String) reviewList.get(0).get("user_id");
		List<String> movieIdList = new ArrayList<>();
		
		for(Map<String, Object> review : reviewList) {
			movieIdList.add((String) review.get("movie_id"));
		}
		
		return areviewsearchdao.delReview(userId, movieIdList);
				
	}

}
