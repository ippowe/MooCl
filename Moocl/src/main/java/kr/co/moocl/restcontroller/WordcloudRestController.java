package kr.co.moocl.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.WordCloudService;

@RestController
@RequestMapping("/api")
public class WordcloudRestController {
	@Autowired
	WordCloudService wordCloudService; 
	@RequestMapping("/getWord")
	public List<Document> getWordList(@RequestParam("movieId") String movieId){
		System.out.println(movieId);
		List<Document> result = (List<Document>) wordCloudService.getWordList(movieId);
		return result;
	}

	@RequestMapping("/getWordByPerson")
	public List<Document> getWordByPerson(
			@RequestParam("personId") String personId){
		int id = Integer.parseInt(personId);
		System.out.println(id);
		List<Document> result = (List<Document>) wordCloudService.getWordByPerson(id);
		return result;
	}
	
	
	@RequestMapping("/getRecommendMovie")
	public Map<String,List<Map<String, Object>>> getRecommendMovie(
			@RequestParam("movieId") String movieId,
			@RequestParam("clickWord") String clickWord){
		System.out.println(movieId);
		System.out.println(clickWord);
		List<Map<String, Object>> goodMovieList = (List<Map<String, Object>>) wordCloudService.getGoodMovie(movieId,clickWord);
		List<Map<String, Object>> badMovieList = (List<Map<String, Object>>) wordCloudService.getBadMovie(movieId,clickWord);
		Map<String,List<Map<String, Object>>> resultMap = new HashMap<String,List<Map<String, Object>>>();
		resultMap.put("good", goodMovieList);
		resultMap.put("bad", badMovieList);
		
		return resultMap;
	}
	
	@RequestMapping("/getRecommendByPerson")
	public Map<String,List<Document>> getRecommendByPerson(
			@RequestParam("personId") String personId,
			@RequestParam("clickWord") String clickWord){
		System.out.println(personId);
		System.out.println(clickWord);
		List<Document> goodMovieList = (List<Document>) wordCloudService.getGoodMovieByPerson(personId,clickWord);
		List<Document> badMovieList = (List<Document>) wordCloudService.getBadMovieByPerson(personId,clickWord);
		Map<String,List<Document>> resultMap = new HashMap<String,List<Document>>();
		resultMap.put("good", goodMovieList);
		resultMap.put("bad", badMovieList);
		
		return resultMap;
	}	
	
	
	
}
