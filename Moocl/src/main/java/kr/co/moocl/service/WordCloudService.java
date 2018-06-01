package kr.co.moocl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.MovieDao;
import kr.co.moocl.dao.PersonDao;
import kr.co.moocl.vo.InteMovieVo;
import kr.co.moocl.vo.PeopleVo;

@Service
public class WordCloudService {
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	PersonDao personDao;

	public Object getWordList(String movieId) {
		Object result = null;
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
		String d = date.format(today).toString();
		
		if (!isLatestWordcolud(movieId)) {
			System.out.println("업데이트필요");
			result = movieDao.getWordListByMovieId(movieId);
			movieDao.updateCloudWord(movieId,result,d);
		} else {
			System.out.println("바로가져오기");
			result = movieDao.getMovieInfoById(movieId).getWordcloudList();
		}
		return result;
	}
	
	public Object getWordByPerson(int personId) {
		Object result = null;
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
		String d = date.format(today).toString();
		
		if (!isLatestWordcoludByPerson(personId)) {
			System.out.println("업데이트필요");
			result = personDao.getWordListByPersonId(personId);
			personDao.updateCloudWord(personId,result,d);
		} else {
			System.out.println("바로가져오기");
			result = personDao.getPersonById(personId).getWordcloudList();
		}
		return result;
	}


	public List<Document> getGoodMovie(String movieId, String clickWord) {
		return movieDao.getMovieByStd(movieId,clickWord,"gte",8);
	}

	public List<Document> getBadMovie(String movieId, String clickWord) {
		return movieDao.getMovieByStd(movieId,clickWord,"lte",5);
	}
		
	
	
	// 최신 클라우드인지 확인하는 메소드 (3일전까지는 최신으로 판단)
	// false : wordcloudDate null || today - updateDate > 4
	// true : wordcloudDate today - updateDate < 4
	public boolean isLatestWordcolud(String movieId) {
		boolean result = false;
		InteMovieVo vo = movieDao.getMovieInfoById(movieId);
//		String update = vo.getWordcloudDate();

		//test 용 날짜 변환
		String update = "2018.05.13";
		
		if (update != null ) {
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
			int diff = 0 ;
			
			try {
				Date compareDate = date.parse(update);
				long intervalTime = today.getTime() - compareDate.getTime();
				Calendar cal = Calendar.getInstance();
				diff = (int)intervalTime/(24*60*60*1000);
				diff = Math.abs(diff);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			System.out.println(diff);
			if (diff < 4) {
				result = true;
			}
			System.out.println(result);
		}
		return result;
	}
	
	public boolean isLatestWordcoludByPerson(int personId) {
		boolean result = false;
		PeopleVo vo = personDao.getPersonById(personId);
		String update = vo.getWordcloudDate();

		//test 용 날짜 변환
//		String update = "2018.05.13";
		
		if (update != null ) {
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
			int diff = 0 ;
			
			try {
				Date compareDate = date.parse(update);
				long intervalTime = today.getTime() - compareDate.getTime();
				Calendar cal = Calendar.getInstance();
				diff = (int)intervalTime/(24*60*60*1000);
				diff = Math.abs(diff);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			System.out.println(diff);
			if (diff < 4) {
				result = true;
			}
			System.out.println(result);
		}
		return result;
	}
}
