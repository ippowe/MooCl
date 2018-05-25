package kr.co.moocl.vo;

public class FavVo {

	private int UserNo;	//사용자
	private String MovieId;
	private String PersonId;	//감독, 배우
	
	public FavVo() {}
	
	public FavVo(int userNo, String movieId, String personId) {
		super();
		UserNo = userNo;
		MovieId = movieId;
		PersonId = personId;
	}

	public int getUserNo() {
		return UserNo;
	}

	public void setUserNo(int userNo) {
		UserNo = userNo;
	}

	public String getMovieId() {
		return MovieId;
	}

	public void setMovieId(String movieId) {
		MovieId = movieId;
	}

	public String getPersonId() {
		return PersonId;
	}

	public void setPersonId(String personId) {
		PersonId = personId;
	}

	@Override
	public String toString() {
		return "FavVo [UserNo=" + UserNo + ", MovieId=" + MovieId + ", PersonId=" + PersonId + "]";
	}
	
	
	
	
	
	
}
