package kr.co.moocl.vo;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="review")
public class ReviewVo {

	@Id
	private Map<String, Object> _id;
	private String movie_id;
	private String site;
	private String user_id;
	private String review_contents;
	private String reg_date;
	private int user_grade;
	private List<List<String>> pos_analyze;
	
	public ReviewVo(Map<String, Object> _id, String movie_id, String site, String user_id, String review_contents,
			String reg_date, int user_grade) {
		this._id = _id;
		this.movie_id = movie_id;
		this.site = site;
		this.user_id = user_id;
		this.review_contents = review_contents;
		this.reg_date = reg_date;
		this.user_grade = user_grade;
	}
	
	public ReviewVo() {
	}

	public Map<String, Object> get_id() {
		return _id;
	}

	public String getMovie_id() {
		return movie_id;
	}

	public String getSite() {
		return site;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getReview_contents() {
		return review_contents;
	}

	public String getReg_date() {
		return reg_date;
	}

	public int getUser_grade() {
		return user_grade;
	}

	public void set_id(Map<String, Object> _id) {
		this._id = _id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public void setUser_grade(int user_grade) {
		this.user_grade = user_grade;
	}
	
	public List<List<String>> getPos_analyze() {
		return pos_analyze;
	}

	public void setPos_analyze(List<List<String>> pos_analyze) {
		this.pos_analyze = pos_analyze;
	}

	@Override
	public String toString() {
		return "ReviewVo [_id=" + _id + ", movie_id=" + movie_id + ", site=" + site + ", user_id=" + user_id
				+ ", review_contents=" + review_contents + ", reg_date=" + reg_date + ", user_grade=" + user_grade
				+ ", pos_analyze=" + pos_analyze + "]";
	}

	
}
