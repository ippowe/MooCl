package kr.co.moocl.vo;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="movie_info")
public class InteMovieVo {
	
	@Id
	private String _id;
	private List<Map<String, Object>> movie_id;
	private String inte_title;
	private String movie_title;
	private String inte_director;
	private String director;
	private String poster;
	private Object running_time;
	private List<Map<String, Object>> review_cnt;
	private List<Map<String, Object>> score;
	private List<Map<String, Object>> gender_ratio;
	private List<Map<String, Object>> age;
	private String nation;
	private String open_date;
	private String genre;
//	private String movie_rate;영화 관람등급 추후 수정 필요	
	@DBRef
	private List<Object> person;  //DB에서 링크로 연결돼서 못가져 옴
	
	
	public String get_id() {
		return _id;
	}
	public List<Map<String, Object>> getMovie_id() {
		return movie_id;
	}
	public String getInte_title() {
		return inte_title;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public String getInte_director() {
		return inte_director;
	}
	public String getDirector() {
		return director;
	}
	public String getPoster() {
		return poster;
	}
	public Object getRunning_time() {
		return running_time;
	}
	public List<Map<String, Object>> getReview_cnt() {
		return review_cnt;
	}
	public List<Map<String, Object>> getScore() {
		return score;
	}
	public List<Map<String, Object>> getGender_ratio() {
		return gender_ratio;
	}
	public List<Map<String, Object>> getAge() {
		return age;
	}
	public String getNation() {
		return nation;
	}
	public String getOpen_date() {
		return open_date;
	}
	public String getGenre() {
		return genre;
	}
	public List<Object> getPerson() {
		return person;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public void setMovie_id(List<Map<String, Object>> movie_id) {
		this.movie_id = movie_id;
	}
	public void setInte_title(String inte_title) {
		this.inte_title = inte_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public void setInte_director(String inte_director) {
		this.inte_director = inte_director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public void setRunning_time(Object running_time) {
		this.running_time = running_time;
	}
	public void setReview_cnt(List<Map<String, Object>> review_cnt) {
		this.review_cnt = review_cnt;
	}
	public void setScore(List<Map<String, Object>> score) {
		this.score = score;
	}
	public void setGender_ratio(List<Map<String, Object>> gender_ratio) {
		this.gender_ratio = gender_ratio;
	}
	public void setAge(List<Map<String, Object>> age) {
		this.age = age;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setPerson(List<Object> person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "InteMovieVo [_id=" + _id + ", movie_id=" + movie_id + ", inte_title=" + inte_title + ", movie_title="
				+ movie_title + ", inte_director=" + inte_director + ", director=" + director + ", poster=" + poster
				+ ", running_time=" + running_time + ", review_cnt=" + review_cnt + ", score=" + score
				+ ", gender_ratio=" + gender_ratio + ", age=" + age + ", nation=" + nation + ", open_date=" + open_date
				+ ", genre=" + genre + ", person=" + person + "]";
	}
	public InteMovieVo(String _id, List<Map<String, Object>> movie_id, String inte_title, String movie_title,
			String inte_director, String director, String poster, Object running_time,
			List<Map<String, Object>> review_cnt, List<Map<String, Object>> score,
			List<Map<String, Object>> gender_ratio, List<Map<String, Object>> age, String nation, String open_date,
			String genre, List<Object> person) {
		this._id = _id;
		this.movie_id = movie_id;
		this.inte_title = inte_title;
		this.movie_title = movie_title;
		this.inte_director = inte_director;
		this.director = director;
		this.poster = poster;
		this.running_time = running_time;
		this.review_cnt = review_cnt;
		this.score = score;
		this.gender_ratio = gender_ratio;
		this.age = age;
		this.nation = nation;
		this.open_date = open_date;
		this.genre = genre;
		this.person = person;
	}
	public InteMovieVo() {
		super();
	}
	
	
}

