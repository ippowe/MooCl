package kr.co.moocl.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;

import kr.co.moocl.service.aReviewSearchService;
import kr.co.moocl.vo.aReviewSearchVo;



@RestController
@RequestMapping("/api")
public class aReviewSearchRestController {
	
	@Autowired
	aReviewSearchService areviewsearchservice;
	
	@RequestMapping(value="/reviewsearch", method = RequestMethod.GET)
	public List<aReviewSearchVo> reviewSearch(@RequestParam("userid") String userid){
		
		List<aReviewSearchVo> reviewSearchList = areviewsearchservice.reviewSearchResult(userid);
		
		return reviewSearchList;
	}
	
	@RequestMapping(value="/delreview", method = RequestMethod.GET)
	public void delReview(@RequestParam("userid") String userid, @RequestParam("movieid") String movieid) {
		
		areviewsearchservice.delReview(userid, movieid);
				
	}
	

}
