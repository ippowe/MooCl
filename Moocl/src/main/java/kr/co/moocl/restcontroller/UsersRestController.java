package kr.co.moocl.restcontroller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moocl.service.UsersService;

@RequestMapping("/api")
@RestController
public class UsersRestController {

	private static final Logger logger = LoggerFactory.getLogger(UsersRestController.class);
	
	@Autowired
	UsersService usersService;
	
	@RequestMapping("/login")
	public Map<String, Object> login(@RequestBody Map<String, String> userVo){
		
		Map<String, Object> result = usersService.userLogin(userVo);
		
		return result;
	}
	
	@RequestMapping("/checkEmail")
	public Boolean checkEmail(@RequestBody Map<String, String> userEmail) {
		
		Boolean result = usersService.checkEmail(userEmail);
		//이메일 중복 X : true / 이메일 중복 : false
		
		return result;
	}
	
	@RequestMapping("/join")
	public Boolean joinUser(@RequestBody Map<String, Object> userVo) {

		Boolean result = usersService.joinUser(userVo);

		return result;
	}
}
