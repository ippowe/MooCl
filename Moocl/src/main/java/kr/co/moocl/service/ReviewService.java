package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.List;

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
	
		
	
	public int getReviewCount(String movieId) {
		long temp_count = reviewDao.getReviewCount(movieId);
		int reviewCount = (int) temp_count;

		return reviewCount;
	}
	

	public List<ReviewVo> testService() {
		return reviewDao.testDao();
	}
}
