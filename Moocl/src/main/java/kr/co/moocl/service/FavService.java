package kr.co.moocl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.FavDao;
import kr.co.moocl.dao.MovieDao;
import kr.co.moocl.dao.PersonDao;
import kr.co.moocl.vo.PeopleVo;

@Service
public class FavService {
	
	@Autowired
	FavDao favDao;
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	MainService mainService;
	
	@Autowired
	PersonDao personDao;

	public List<String> allFavMovie(int userId) {
		List<String> getfavMoivie = favDao.getFavMovielist(userId);

		return getfavMoivie;
	}
	
	
	public int addFavMovie(int userId, String movieId) {
		int insertFavMovie = favDao.insertFmovie(userId, movieId);
		
		return insertFavMovie;
	}


	public int deleteFavMovie(int userId, String movieId) {
		
		int removeFavMovie = favDao.deleteFavM(userId, movieId);
		return removeFavMovie;
	}


	public List<String> allFavpeople(int userId) {
		List<String> showFpeoplelist = favDao.showfavpeoplelist(userId);

		return showFpeoplelist;
	}


	public int insertfavPeople(int userId, String personId) {	
		int insert_favPerson = favDao.addfavperson(userId, personId);

		return insert_favPerson;
	}


	public int deleteFavPeople(int userId, String personId) {	
		int delete_fperson = favDao.delFavPerson(userId, personId);

		return delete_fperson;
	}


	public Map<String, Object> getMypageData(String userNo) {
		Map<String, Object> myPageData = new HashMap<>();
		
		int userId = Integer.parseInt(userNo);
		
		List<String> movieIds = favDao.getFavMovielist(userId);
		List<String> temp_personIds = favDao.showfavpeoplelist(userId);
		List<Integer> personIds = new ArrayList<>();
		for(String personId : temp_personIds){
			personIds.add(Integer.parseInt(personId));
		}
		
		
		List<PeopleVo> personInfoList = personDao.getPersonListByIdList(personIds);
		for(PeopleVo person : personInfoList) {
			person.setPerson_id(person.get_id());
		}
		List<Map<String, Object>> movieInfoList = mainService.getMovieList(movieIds);
		
		myPageData.put("personInfoList", personInfoList);
		myPageData.put("movieInfoList", movieInfoList);
		
		return myPageData;
	}

}
