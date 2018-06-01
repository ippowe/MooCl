package kr.co.moocl.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.stereotype.Component;

import kr.co.moocl.vo.InteMovieVo;
import kr.co.moocl.vo.PeopleVo;

@Component
public class PersonDao {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;
	@Autowired
	private ResourceLoader resourceLoader;
	
	public List<PeopleVo> getPersonInfoByPplList(List<Object> temp_personId) {

		
		List<PeopleVo> peopleInfoList = new ArrayList<>();
		
		for(int i=0; i<temp_personId.size(); i++) {
			Criteria criteria = new Criteria("_id");
			criteria.is(temp_personId.get(i));
			Query query = new Query(criteria);	
			
			PeopleVo tempPplList = mongoTemplate.findOne(query, PeopleVo.class, "people");
			peopleInfoList.add(tempPplList);
		}

		return peopleInfoList;
		
	}

	public List<PeopleVo> getPersonListByIdList(List<Integer> personIds) {
		Criteria criteria = new Criteria("_id");
		criteria.in(personIds);
		
		Query query = new Query(criteria);
		List<PeopleVo> personInfoList = mongoTemplate.find(query, PeopleVo.class, "people");
				
		return personInfoList;
	}
	
	public PeopleVo getPersonById(int personId) {
		Query query = new Query(Criteria.where("_id").is(personId));
		PeopleVo result = mongoTemplate.findOne(query, PeopleVo.class, "people");
		System.out.println(result);
		return result;
	}

	public Object getWordListByPersonId(int personId) {
		ExecutableMongoScript wordcloudScript = scriptMaker("JavaScript/getWordListByPersonId.js");
		List<Document> movieList = 
				(List<Document>) mongoOperation.scriptOps().execute(wordcloudScript, personId);
		return movieList;
	}
	
	private ExecutableMongoScript scriptMaker(String path) {
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

	public void updateCloudWord(int personId, Object result, String date) {

		Query query = new Query(new Criteria("_id").is(personId));
		Update update = new Update();
		update.set("wordcloudList", result);
		update.set("wordcloudDate", date);

		mongoTemplate.updateFirst(query, update, "people");
		System.out.println(mongoTemplate.find(query, PeopleVo.class, "people"));
	}

}
