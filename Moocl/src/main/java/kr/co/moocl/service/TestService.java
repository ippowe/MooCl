package kr.co.moocl.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.TestDao;
import kr.co.moocl.vo.FilmoVo;
import kr.co.moocl.vo.MongoVo;
import kr.co.moocl.vo.MovieVo;

@Service
public class TestService {

	@Autowired
	TestDao testDao;
	
	public MovieVo testMongoDB() {
		return testDao.testMongoDB();
	}
	
	public void testFilmoVo(){
		
		for(int i=1; i<101; i++) {
			
		}
		testDao.testMovieVo();
	}
	
}
