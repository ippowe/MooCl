package kr.co.moocl.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import kr.co.moocl.vo.ReviewVo;



@Component
public class MovieDao {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;
	
		
	public List<InteMovieVo> getMovieInfoList(String keyword) {
			
		Query query = new Query().with(new Sort(Sort.Direction.DESC, "open_date"));
		
		query.limit(10);  //10개씩 불러오고 스크롤 내리면 더불러오게 나중에 코딩
		
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
	
	public List<InteMovieVo> getRelatedMoviesByPersonId(int personId){
		
		Criteria criteria = new Criteria("person");
		Criteria embededCriteria = new Criteria("person_id");
		embededCriteria.is(personId);
		criteria.elemMatch(embededCriteria);
		
		Query query = new Query().with(new Sort(Sort.Direction.DESC, "open_date"));
		query.addCriteria(criteria);
		
		List<InteMovieVo> relatedMovieList = mongoTemplate.find(query, InteMovieVo.class, "movie_info");
		return relatedMovieList;
	}
	
	public List<Document> getRelatedMoviesByMovieId(String stringMovieId) {
	    StringBuilder text = new StringBuilder();
	    BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
			        new File("C:\\Users\\1\\Desktop\\Bit_Project\\MooCl\\Moocl\\frontend\\src\\store\\filmo_script.js")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	        while (true) {
	            String line= "";
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (line == null)
	                break;
	            text.append(line).append("\n");
	        }
	    } finally {
	        try { br.close(); } catch (Exception ignore) {}
	    }
	    ExecutableMongoScript filmoScript = new ExecutableMongoScript(text.toString());
	    List<Document> movieList= (List<Document>) mongoOperation.scriptOps().execute(filmoScript, stringMovieId); 
        return movieList;
	}

	public List<InteMovieVo> getMovieInfoList(List<String> movieIds) {
		Criteria criteria = new Criteria("_id");
		criteria.in(movieIds);
		
		Query query = new Query(criteria);
		
		List<InteMovieVo> movieList = mongoTemplate.find(query, InteMovieVo.class, "movie_info");
		return movieList;
	}

	public List<Document> getWordListByMovieId(String movieId) {
	    StringBuilder text = new StringBuilder();
	    BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
			        new File("C:\\Users\\1\\git\\MooCl\\Moocl\\src\\main\\resources\\static\\static\\js\\getWordListByMovieId.js")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	        while (true) {
	            String line= "";
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (line == null)
	                break;
	            text.append(line).append("\n");
	        }
	    } finally {
	        try { br.close(); } catch (Exception ignore) {}
	    }
	    ExecutableMongoScript wordcloudScript = new ExecutableMongoScript(text.toString());
	    List<Document> movieList= (List<Document>) mongoOperation.scriptOps().execute(wordcloudScript, movieId); 
        return movieList;
	}
	
	public void updateCloudWord(String movieId, Object result, String date) {

		Query query = new Query( new Criteria("_id").is(movieId));
		Update update = new Update();
		update.set("wordcloudList", result);
		update.set("wordcloudDate", date);
		
		mongoTemplate.updateFirst(query, update,"movie_info");
		System.out.println(mongoTemplate.find(query, InteMovieVo.class, "movie_info"));
	}
	
}
