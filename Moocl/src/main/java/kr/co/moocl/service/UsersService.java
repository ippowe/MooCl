package kr.co.moocl.service;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moocl.dao.UsersDao;
import kr.co.moocl.vo.UsersVo;

@Service
public class UsersService {

	private static final Logger logger = LoggerFactory.getLogger(UsersService.class);
	
	@Autowired
	UsersDao usersDao;
	
	public Boolean userLogin(Map<String, String> userVo){
		
		logger.info("UsersService.userLogin 진입 UserVo : " + userVo.toString());
		
		String userEmail = userVo.get("email");
		String userPass = userVo.get("password");
		
		UsersVo userInfo = usersDao.getUserInfoByEmail(userEmail);
		
		logger.info("usersDao에서 userInfo 가져옴 : " + userInfo.toString());
		
		if(userPass.equals(userInfo.getPassword()) || userInfo == null) {
			logger.info("로그인 실패");
			return false;
		} else {
			logger.info("로그인 성공");
			return true;
		}
		
	}

	public Boolean checkEmail(Map<String, String> userEmail) {
		
		logger.info("UsersService.checkEmail 진입 UserVo : " + userEmail.toString());
		
		String email = userEmail.get("email");
		
		UsersVo userInfo = usersDao.getUserInfoByEmail(email);
		
		logger.info("usersDao에서 userInfo 가져옴 : " + userInfo);
		
		Boolean result = true; //이메일 중복 X : true / 이메일 중복 : false
		
		if(userInfo != null) {
			result = false;
			logger.info("이메일 중복 O");
		}

		return result;
	}
	
	public Boolean joinUser(Map<String, Object> userVo) {
		logger.info("UsersService.joinUser 진입 UserVo : " + userVo.toString());
		
		//age값이 문자열이기 때문에 정수형으로 바꿔줌
		String tempAge = userVo.get("age").toString();
		int intAge = Integer.parseInt(tempAge);
		userVo.put("age", intAge);
		System.out.println(userVo);
		
		int cnt = usersDao.insertUserInfo(userVo);
		
		logger.info("DB에 입력 성공 갯수 : " + cnt);
		
		if (cnt == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	

}
