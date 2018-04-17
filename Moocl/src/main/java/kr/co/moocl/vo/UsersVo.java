package kr.co.moocl.vo;

public class UsersVo {

	private int userNo;
	private String email;
	private String password;
	private String gender;
	private int age;
	private String nickName;
	
	public UsersVo(int userNo, String email, String password, String gender, int age, String nickName) {
		this.userNo = userNo;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.nickName = nickName;
	}

	public UsersVo() {
		super();
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "UsersVo [userNo=" + userNo + ", email=" + email + ", password=" + password + ", gender=" + gender
				+ ", age=" + age + ", nickName=" + nickName + "]";
	}
	
}
