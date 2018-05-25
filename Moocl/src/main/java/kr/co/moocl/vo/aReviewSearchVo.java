package kr.co.moocl.vo;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="test_review_update")
public class aReviewSearchVo {
	
	@Id
	private Map<String, Object> _id;
	private String movie_id;
	private String site;
	private String user_id;
	private String review_contents;
	private String reg_date;
	private int user_grade;

	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReview_contents() {
		return review_contents;
	}
	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getUser_grade() {
		return user_grade;
	}
	public void setUser_grade(int user_grade) {
		this.user_grade = user_grade;
	}
	public Map<String, Object> get_id() {
		return _id;
	}
	public void set_id(Map<String, Object> _id) {
		this._id = _id;
	}
	@Override
	public String toString() {
		return "aReviewSearchVo [_id=" + _id + ", movie_id=" + movie_id + ", site=" + site + ", user_id=" + user_id
				+ ", review_contents=" + review_contents + ", reg_date=" + reg_date + ", user_grade=" + user_grade
				+ "]";
	}
	
	
	

}
