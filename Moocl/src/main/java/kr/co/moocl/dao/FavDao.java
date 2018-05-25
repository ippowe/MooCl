package kr.co.moocl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.moocl.vo.FavVo;

@Component
public class FavDao {
	
	
	@Autowired
	SqlSession sqlSession;

	public List<String> getFavMovielist(int userId) {
		
		List<String> favMovielist = sqlSession.selectList("fav.allFavouriteMovies", userId);

		return favMovielist;
	}
	
	public int insertFmovie(int userId, String movieId) {
		FavVo favvo = new FavVo();
		
		favvo.setUserNo(userId);
		favvo.setMovieId(movieId);
				
		int addFMovie = sqlSession.insert("fav.addFavouriteMovie", favvo);
		
		return addFMovie;
	}

	public int deleteFavM(int userId, String movieId) {

		Map<Object, Object> favmap = new HashMap<Object, Object>();
		
		favmap.put("userId", userId);
		favmap.put("movieId", movieId);
		
		int deleteFavMovie = sqlSession.delete("fav.deleteFavouriteMovie", favmap);
	
		return deleteFavMovie;
	}

	public List<String> showfavpeoplelist(int userId) {
		
		List<String> favPeoplelist = sqlSession.selectList("fav.showFavouritePeople", userId);
		
		return favPeoplelist;
	}

	public int addfavperson(int userId, String personId) {
		
		FavVo fvo = new FavVo();
		
		fvo.setUserNo(userId);
		fvo.setPersonId(personId);
				
		int addfperson = sqlSession.insert("fav.addFavouritePerson", fvo);
		
		return addfperson;
	}

	public int delFavPerson(int userId, String personId) {

		Map<String, Object> personmap = new HashMap<String, Object>();
		
		personmap.put("userId", userId);
		personmap.put("personId", personId);
		
		int delFavPerson = sqlSession.delete("fav.deleteFavouritePerson", personmap);
	
		return delFavPerson;
	}


}
