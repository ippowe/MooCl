package kr.co.moocl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	PersonService personSerivce;
	
	@RequestMapping(value= "/removie", method=RequestMethod.POST)
	public List<Document> relatedMovies(@RequestBody Map<String, Object> movieId){
		
		List<Document> relatedMovies = personSerivce.getRelatedMovies(movieId);
		
		return relatedMovies;
	}
}
