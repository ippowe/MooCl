package kr.co.moocl.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.dao.MovieDao;
import kr.co.moocl.service.MainService;
import kr.co.moocl.vo.InteMovieVo;

@RestController
@RequestMapping("/api")
public class MainRestController {

	@Autowired
	MainService mainService;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieDao.class);
	
	@RequestMapping(value="/detailinfo", method=RequestMethod.GET)
	public InteMovieVo detailInfo(@RequestParam String movieId){
		logger.info("detailInfo: " + movieId);
		
		InteMovieVo modifiedInfo = mainService.modifyForDetail(movieId);
		
		return modifiedInfo;
	}
	
}
