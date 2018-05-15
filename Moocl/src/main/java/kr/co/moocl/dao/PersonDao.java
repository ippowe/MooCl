package kr.co.moocl.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import kr.co.moocl.vo.PeopleVo;

@Component
public class PersonDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Object> getPersonInfoByPplList(List<Object> temp_personId) {

		
		List<Object> peopleInfoList = new ArrayList<>();
		
		for(int i=0; i<temp_personId.size(); i++) {
			Criteria criteria = new Criteria("_id");
			criteria.is(temp_personId.get(i));
			Query query = new Query(criteria);	
			
			PeopleVo tempPplList = mongoTemplate.findOne(query, PeopleVo.class, "people");
			peopleInfoList.add(tempPplList);
		}

		return peopleInfoList;
		
	}
}
