package kr.co.moocl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.ReviewDao;
import kr.co.moocl.vo.ReviewVo;

@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	public List<ReviewVo> getReviewList(String movieId, int page) {
		List<ReviewVo> reviewList = reviewDao.findByMovieIdSortDate(movieId, page);
		return reviewList;
	}
	
	public long getReviewCount(String movieId) {
		long reviewCount = reviewDao.getReviewCount(movieId);
		return reviewCount;
	}

	public List<ReviewVo> testService() {
		return reviewDao.testDao();
	}
}
