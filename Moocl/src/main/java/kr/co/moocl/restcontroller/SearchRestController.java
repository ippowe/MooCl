package kr.co.moocl.restcontroller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.SearchService;


@RestController
@RequestMapping("/api")
public class SearchRestController {

	@Autowired
	SearchService searchService;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchRestController.class);
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public List<Map<String, Object>> searchInteData(@RequestParam String keyword){
		logger.info("searchController keyword:" + keyword);
		
		List<Map<String, Object>> searchResultList = searchService.inteSearchResult(keyword);
				
		logger.info("searching result : " + searchResultList);
		return searchResultList;
	}

	
}
