package kr.co.moocl.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.stereotype.Component;

import kr.co.moocl.vo.InteMovieVo;

@Component
public class MovieDao {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;
	@Autowired
	private ResourceLoader resourceLoader;

	public List<InteMovieVo> getMovieInfoList(String keyword) {

		Query query = new Query().with(new Sort(Sort.Direction.DESC, "open_date"));

		query.limit(10); // 10개씩 불러오고 스크롤 내리면 더불러오게 나중에 코딩

		query.addCriteria(Criteria.where("inte_title").regex(keyword));

		List<InteMovieVo> queryResult = mongoTemplate.find(query, InteMovieVo.class, "movie_info");

		return queryResult;
	}

	public InteMovieVo getMovieInfoById(String movieId) {

		Criteria criteria = new Criteria("_id");
		criteria.is(movieId);

		Query query = new Query(criteria);

		InteMovieVo rawMovieData = mongoTemplate.findOne(query, InteMovieVo.class, "movie_info");

		return rawMovieData;
	}

	public List<InteMovieVo> getRelatedMoviesByPersonId(int personId) {

		Criteria criteria = new Criteria("person");
		Criteria embededCriteria = new Criteria("person_id");
		embededCriteria.is(personId);
		criteria.elemMatch(embededCriteria);

		Query query = new Query().with(new Sort(Sort.Direction.DESC, "open_date"));
		query.addCriteria(criteria);

		List<InteMovieVo> relatedMovieList = mongoTemplate.find(query, InteMovieVo.class, "movie_info");
		return relatedMovieList;
	}

	public List<InteMovieVo> getMovieInfoList(List<String> movieIds) {
		Criteria criteria = new Criteria("_id");
		criteria.in(movieIds);

		Query query = new Query(criteria);

		List<InteMovieVo> movieList = mongoTemplate.find(query, InteMovieVo.class, "movie_info");
		return movieList;
	}

	public List<Document> getWordListByMovieId(String movieId) {
		ExecutableMongoScript wordcloudScript = scriptMaker("JavaScript/getWordListByMovieId.js");
		List<Document> movieList = (List<Document>) mongoOperation.scriptOps().execute(wordcloudScript, movieId);
		return movieList;
	}

	public void updateCloudWord(String movieId, Object result, String date) {

		Query query = new Query(new Criteria("_id").is(movieId));
		Update update = new Update();
		update.set("wordcloudList", result);
		update.set("wordcloudDate", date);

		mongoTemplate.updateFirst(query, update, "movie_info");
	}


	public List<Map<String, Object>> getMovieByStd(String movieId, String clickWord, String condition, int grade) {
		ExecutableMongoScript wordcloudScript = scriptMaker("JavaScript/getMovieByStd.js");
		
		List<Map<String, Object>> movieList = 
				(List<Map<String, Object>>) mongoOperation.scriptOps().execute(wordcloudScript, movieId,clickWord,condition,grade);
		
		for(Map<String, Object> movies : movieList) {
			String movie = (String) movies.get("movie");
			InteMovieVo movieInfo = getMovieInfoById(movie);
			movies.replace("movie", movieInfo);
		}
		if(movieList.size() != 0) {
			movieList.remove(0);
			return movieList;
		} else {
			return movieList;
		}
		

	}

	public List<Map<String, Object>> getMovieByPerson(String personId, String clickWord, String condition, int grade) {
		ExecutableMongoScript wordcloudScript = scriptMaker("JavaScript/getMovieByPersonWord.js");
		
		List<Map<String, Object>> movieList = 
				(List<Map<String, Object>>) mongoOperation.scriptOps().execute(wordcloudScript, personId,clickWord,condition,grade);
				
		for(Map<String, Object> movies : movieList) {
			String movie = (String) movies.get("movie");
			InteMovieVo movieInfo = getMovieInfoById(movie);
			movies.replace("movie", movieInfo);
		}
		if(movieList.size() != 0) {
			movieList.remove(0);
			return movieList;
		} else {
			return movieList;
		}
		
	}
	
	private ExecutableMongoScript scriptMaker (String path) {
		String resourcePath = "classpath:";
		resourcePath += path;
		StringBuilder text = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(
					resourceLoader.getResource(resourcePath).getURI().getPath())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			while (true) {
				String line = "";
				try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (line == null)
					break;
				text.append(line).append("\n");
			}
		} finally {
			try {
				br.close();
			} catch (Exception ignore) {
			}
		}
		ExecutableMongoScript script = new ExecutableMongoScript(text.toString());
		return script;
	}

}
