package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.ReviewDao;
import kr.co.moocl.vo.ReviewVo;

@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	public List<ReviewVo> getReviewList(String movieId, int page, int score) {
				
		if(score == -1) {
			
			List<ReviewVo> reviewList = reviewDao.findByMovieIdSortDate(movieId, page);
			
			return reviewList;
		} else {
			
			List<Integer> scores = new ArrayList<>();
			scores.add(score*2 - 1);
			scores.add(score*2);
			
			List<ReviewVo> reviewList = reviewDao.findByIdPageScore(movieId, page, scores);
			return reviewList;
		}
	}
	
	public Map<String, Integer> getReviewCount(String movieId) {
		
		Map<String, Integer> reviewCounts = new HashMap<>();
		
		int i = 0;
		String score = "";
		int totalCount = 0;
		while( i < 11) {
			long temp_count = reviewDao.getReviewCount(movieId, i);
			score = String.valueOf(i);
			totalCount += temp_count;
			reviewCounts.put(score, (int) temp_count);
			i++;
		}
		
		reviewCounts.put("total", totalCount);
		

		return reviewCounts;
	}
	

	public List<ReviewVo> testService() {
		return reviewDao.testDao();
	}
}
