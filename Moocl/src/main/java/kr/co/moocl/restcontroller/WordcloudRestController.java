package kr.co.moocl.restcontroller;

import java.util.List;

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
		List<Document> result = (List<Document>) wordCloudService.getWordList(movieId);
		return result;
	}
	
}
