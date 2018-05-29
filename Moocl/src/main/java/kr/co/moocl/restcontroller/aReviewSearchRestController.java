package kr.co.moocl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.aReviewSearchService;
import kr.co.moocl.vo.ReviewVo;



@RestController
@RequestMapping("/api")
public class aReviewSearchRestController {
	
	@Autowired
	aReviewSearchService areviewsearchservice;
	
	@RequestMapping(value="/reviewsearch", method = RequestMethod.POST)
	public List<ReviewVo> reviewSearch(@RequestBody Map<String, Object> email){
		
		String userId = (String) email.get("email");

		
		List<ReviewVo> reviewSearchList = areviewsearchservice.reviewSearchResult(userId);
		
		return reviewSearchList;
	}
	
	@RequestMapping(value="/delreview", method = RequestMethod.POST)
	public int delReview(@RequestBody Map<String, Object> reviewObject) {
				
		List<Map<String, Object>> reviewList = (List<Map<String, Object>>) reviewObject.get("reivewList");
		return areviewsearchservice.delReview(reviewList);
				
	}
	

}
