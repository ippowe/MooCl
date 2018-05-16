package kr.co.moocl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.PersonService;
import kr.co.moocl.vo.InteMovieVo;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	PersonService personSerivce;
	
	@RequestMapping(value= "/removie", method=RequestMethod.POST)
	public List<InteMovieVo> relatedMovies(@RequestBody Map<String, Object> personId){
		
		List<InteMovieVo> relatedMovieList = personSerivce.getRelatedMovies(personId);
		
		return relatedMovieList;
	}
}
