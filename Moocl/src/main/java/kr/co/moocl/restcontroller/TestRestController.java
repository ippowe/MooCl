package kr.co.moocl.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.TestService;
import kr.co.moocl.vo.MovieVo;

@RestController
public class TestRestController {

	@Autowired
	TestService testService;
	
	@RequestMapping("/test/mongo")
	public MovieVo testMongoDB() {
		return testService.testMongoDB();
	}
	
	@RequestMapping("/test/filmo")
	public void filmoTest(){
		testService.testFilmoVo();
	}
}
