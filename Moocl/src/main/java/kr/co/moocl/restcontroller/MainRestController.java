package kr.co.moocl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.MainService;
import kr.co.moocl.vo.InteMovieVo;

@RestController
@RequestMapping("/api")
public class MainRestController {
	
		@Autowired
	MainService mainService;
	
	@RequestMapping(value="/detailinfo", method=RequestMethod.GET)
	public InteMovieVo detailInfo(@RequestParam String movieId){
		
		InteMovieVo movieData = mainService.modifyForDetail(movieId);

		return movieData;
	}

	
	@RequestMapping("/test")
	public List<Document> testController() {
		List<Document> ResultList =  mainService.testService();
		return ResultList;
	}
	
}
