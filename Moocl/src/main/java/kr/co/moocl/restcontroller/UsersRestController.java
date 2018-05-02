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
		
		logger.info("로그인 RestController 진입 userVo : " + userVo);
		
		Map<String, Object> result = usersService.userLogin(userVo);
		
		logger.info("로그인 Service에서 결과 가져옴 : " + result);
		
		return result;
	}
	
	@RequestMapping("/checkEmail")
	public Boolean checkEmail(@RequestBody Map<String, String> userEmail) {
		logger.info("checkEmail 진입 userEmail : " + userEmail);
		
		Boolean result = usersService.checkEmail(userEmail);
		
		logger.info("로그인 Service에서 결과 가져옴 : " + result);
		//이메일 중복 X : true / 이메일 중복 : false
		
		return result;
	}
	
	@RequestMapping("/join")
	public Boolean joinUser(@RequestBody Map<String, Object> userVo) {
		logger.info("joinUser 진입 userVo : " + userVo);
		
		Boolean result = usersService.joinUser(userVo);
		
		logger.info("회원가입 성공 여부: " + result);
		
		return result;
	}
}
