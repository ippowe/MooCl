package kr.co.moocl.restcontroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.ReviewService;
import kr.co.moocl.vo.ReviewVo;
import kr.co.shineware.util.common.model.Pair;

@RestController
@RequestMapping("/api")
public class ReviewRestController {
	
	@Autowired
	ReviewService reviewService;

	@RequestMapping("/reviewlist")
	public List<ReviewVo> getReviewList(HttpServletRequest request){
		
		String movieId = request.getParameter("movieId");
		int page = Integer.parseInt(request.getParameter("pageNo"));
		int score = Integer.parseInt(request.getParameter("score"));
		
		List<ReviewVo> reviewList = reviewService.getReviewList(movieId, page, score);
		
		return reviewList;
	}
	
	@RequestMapping("/reviewcount")
	public int CountingReview(@RequestParam("movieId") String movieId) {
		
		int reviewCount = reviewService.getReviewCount(movieId);
		
		return reviewCount;
	} 
	
	@RequestMapping("/reviewtest")
	public List<ReviewVo> testReview(){
		return reviewService.testService();
	}
	
	@RequestMapping("/reviewtest1")
	public List<List<Pair<String,String>>> testReview1(){
		System.out.println("hi");
		return reviewService.applyKomoran("슈퍼액션에서 하고있는데 진짜 재밌어요");
	}
	
	
}
