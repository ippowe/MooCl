package kr.co.moocl.vo;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="movie_info")
public class InteMovieVo {
	
	@Id
	private String _id;
	private List<Map<String, String>> movie_id;
	private String inte_title;
	private String movie_title;
	private String director;
	private List<Map<String, Object>> review_cnt;
	private List<Map<String, Object>> gender_ratio;
	private List<Map<String, Object>> age;
	private List<Map<String, Object>> score;
	private String nation;
	private String open_date;
	private String genre;
	private String inte_director;
	private Object running_time;
	private Object poster;
	private List<PeopleVo> person;
	private String watching_rate;
	
	
	public InteMovieVo(String _id, List<Map<String, String>> movie_id, String inte_title, String movie_title,
			String director, List<Map<String, Object>> review_cnt, List<Map<String, Object>> gender_ratio,
			List<Map<String, Object>> age, List<Map<String, Object>> score, String nation, String open_date,
			String genre, String inte_director, Object running_time, Object poster, List<PeopleVo> person,
			String watching_rate) {
		this._id = _id;
		this.movie_id = movie_id;
		this.inte_title = inte_title;
		this.movie_title = movie_title;
		this.director = director;
		this.review_cnt = review_cnt;
		this.gender_ratio = gender_ratio;
		this.age = age;
		this.score = score;
		this.nation = nation;
		this.open_date = open_date;
		this.genre = genre;
		this.inte_director = inte_director;
		this.running_time = running_time;
		this.poster = poster;
		this.person = person;
		this.watching_rate = watching_rate;
	}
	public InteMovieVo() {
	}
	public String get_id() {
		return _id;
	}
	public List<Map<String, String>> getMovie_id() {
		return movie_id;
	}
	public String getInte_title() {
		return inte_title;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public String getDirector() {
		return director;
	}
	public List<Map<String, Object>> getReview_cnt() {
		return review_cnt;
	}
	public List<Map<String, Object>> getGender_ratio() {
		return gender_ratio;
	}
	public List<Map<String, Object>> getAge() {
		return age;
	}
	public List<Map<String, Object>> getScore() {
		return score;
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
	public String getInte_director() {
		return inte_director;
	}
	public Object getRunning_time() {
		return running_time;
	}
	public Object getPoster() {
		return poster;
	}
	public List<PeopleVo> getPerson() {
		return person;
	}
	public String getWatching_rate() {
		return watching_rate;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public void setMovie_id(List<Map<String, String>> movie_id) {
		this.movie_id = movie_id;
	}
	public void setInte_title(String inte_title) {
		this.inte_title = inte_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setReview_cnt(List<Map<String, Object>> review_cnt) {
		this.review_cnt = review_cnt;
	}
	public void setGender_ratio(List<Map<String, Object>> gender_ratio) {
		this.gender_ratio = gender_ratio;
	}
	public void setAge(List<Map<String, Object>> age) {
		this.age = age;
	}
	public void setScore(List<Map<String, Object>> score) {
		this.score = score;
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
	public void setInte_director(String inte_director) {
		this.inte_director = inte_director;
	}
	public void setRunning_time(Object running_time) {
		this.running_time = running_time;
	}
	public void setPoster(Object poster) {
		this.poster = poster;
	}
	public void setPerson(List<PeopleVo> person) {
		this.person = person;
	}
	public void setWatching_rate(String watching_rate) {
		this.watching_rate = watching_rate;
	}
	@Override
	public String toString() {
		return "InteMovieVo [_id=" + _id + ", movie_id=" + movie_id + ", inte_title=" + inte_title + ", movie_title="
				+ movie_title + ", director=" + director + ", review_cnt=" + review_cnt + ", gender_ratio="
				+ gender_ratio + ", age=" + age + ", score=" + score + ", nation=" + nation + ", open_date=" + open_date
				+ ", genre=" + genre + ", inte_director=" + inte_director + ", running_time=" + running_time
				+ ", poster=" + poster + ", person=" + person + ", watching_rate=" + watching_rate + "]";
	}
	
	
}

