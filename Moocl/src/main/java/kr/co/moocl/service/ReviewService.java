package kr.co.moocl.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.ReviewDao;
import kr.co.moocl.vo.ReviewVo;
import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

@Configuration
@EnableAsync
@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;

	public List<ReviewVo> getReviewList(String movieId, int page, int score) {

		if (score == -1) {

			List<ReviewVo> reviewList = reviewDao.findByMovieIdSortDate(movieId, page);

			return reviewList;
		} else {

			List<Integer> scores = new ArrayList<>();
			scores.add(score * 2 - 1);
			scores.add(score * 2);

			List<ReviewVo> reviewList = reviewDao.findByIdPageScore(movieId, page, scores);
			return reviewList;
		}

	}

	public int getReviewCount(String movieId) {
		long temp_count = reviewDao.getReviewCount(movieId);
		int reviewCount = (int) temp_count;

		return reviewCount;
	}

	public List<ReviewVo> testService() {
		return reviewDao.testDao();
	}

	@Async
	public void saveReview(String commonId, String movieId, String site, String review, String userId, int userGrade) {

		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
		String regDate = date.format(today).toString();

		Map<String, Object> _id = new HashMap<String, Object>();
//DBRef 생성 및 추가
		Map<String, Object> movieDBRef = new HashMap<String, Object>();
		movieDBRef.put("$id", commonId);
		movieDBRef.put("$ref", "movie_info");

		_id.put("movie_ref", movieDBRef);
		_id.put("user_id", userId);

		reviewDao.saveReview(_id, movieId, site, userId, review, regDate, userGrade);
	}

	@Async
	public List<String[]> applyKomoran(String commonId, String userId,String review) {

		Komoran komoran = new Komoran("src/main/resources/komoran/models-full");
		List<List<Pair<String, String>>> result = komoran.analyze(review);
		List<String[]> pos_analyze = new LinkedList<>();

		for (List<Pair<String, String>> eojeolResult : result) {
			for (Pair<String, String> wordMorph : eojeolResult) {
				String[] pos = new String [2];
				pos[0] = wordMorph.getFirst();
				pos[1] = wordMorph.getSecond();
				pos_analyze.add(pos);
			}
		}
//		for(String[] a : pos_analyze) {
//			System.out.println(a[0]);
//			System.out.println(a[1]);
//		}
		reviewDao.updatePosReview(commonId,userId,pos_analyze);
		//chk 선언
		ChkService chkService = new ChkService();
		//파라미터 선언(초기화)
		List<String[]> conList = new LinkedList<>();
//		List<List<String[]>> finalList = new LinkedList<>();
		List<String> finalList = new LinkedList<>();
		
		int i = 0 ;
		while (i < pos_analyze.size()) {			
			// 조합 결과값 선언
			conList = new LinkedList<>();
			conList.add(pos_analyze.get(i));
			chkService.chk(pos_analyze, i, conList, finalList);
			i++;
		}
		chkService.pickup(pos_analyze, finalList);
//		for(List<String []> a : finalList) {
//			for(String [] b :a) {
//				System.out.print(" "+b[0]+" "+b[1]);
//			}
//			chkService.word_marking_function(a);
//			System.out.println();
//		}
		for(String a : finalList) {
			System.out.println(a);
		}
		
		return pos_analyze;
	}

}
