package kr.co.moocl.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.FavService;

@RestController
@RequestMapping("/api")
public class MyPageFavController {
	
	@Autowired
	FavService favService;

	@RequestMapping(value="/favlist", method=RequestMethod.GET)
	public Map<String, List<String>> showFavMovie(@RequestParam("userId") String userNo) {
				
		int userId = Integer.parseInt(userNo);
		
		Map<String, List<String>> FavList = new HashMap<>();
		
		List<String> FavMovieList = favService.allFavMovie(userId);
		List<String> favpeopleList = favService.allFavpeople(userId);
		
		FavList.put("FavMovieList", FavMovieList);
		FavList.put("favpeopleList", favpeopleList);
			
		return FavList;
		
	}
	
	@RequestMapping(value="/favdata", method=RequestMethod.GET)
	public Map<String, Object> myPageData(@RequestParam("userId") String userNo){
		
		Map<String, Object> myPageData = favService.getMypageData(userNo);
				
		return myPageData;
	}
	
	@RequestMapping(value="/addfavmovie", method=RequestMethod.POST)
	public int addFavMovie(@RequestBody Map<String, String> favMovieVo) {

		int userId = Integer.parseInt(favMovieVo.get("userId"));
		String movieId = favMovieVo.get("movieId");
		
		int addFavMovie = favService.addFavMovie(userId, movieId);

		return addFavMovie;
	}
	
	@RequestMapping(value= "/delfavmovie", method=RequestMethod.POST)
	public int deleteFavMovie(@RequestBody Map<String, String> favMovieVo) {

		
		int userId = Integer.parseInt(favMovieVo.get("userId"));
		String movieId = favMovieVo.get("movieId");
		
		int delFavMovie = favService.deleteFavMovie(userId, movieId);

		return delFavMovie;
	}
	
	@RequestMapping(value="/addfavpeople", method=RequestMethod.POST)
	public int addFavPeople(@RequestBody Map<String, String> favPersonVo) {
		
		int userId = Integer.parseInt(favPersonVo.get("userId"));
		String personId = favPersonVo.get("personId");
		
		int add_favPeople = favService.insertfavPeople(userId, personId);
		
		return add_favPeople ;
	}
	
	@RequestMapping(value= "/delfavpeople", method=RequestMethod.POST)
	public int deleteFavPeople(@RequestBody Map<String, String> favPersonVo) {
		
		int userId = Integer.parseInt(favPersonVo.get("userId"));
		String personId = favPersonVo.get("personId");
		
		int del_Favperson = favService.deleteFavPeople(userId, personId);
	
		return del_Favperson;
	}
	
}
	

