package kr.co.moocl.vo;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class FilmoVo {

	@Id
	private Object _id;
	private Object movie_id;
	private Object movie_title;
	private Object poster;
	private Object director;
	private Object review_cnt;
	private Object score;
	private Object person;
	public Object get_id() {
		return _id;
	}
	public void set_id(Object _id) {
		this._id = _id;
	}
	public Object getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Object movie_id) {
		this.movie_id = movie_id;
	}
	public Object getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(Object movie_title) {
		this.movie_title = movie_title;
	}
	public Object getPoster() {
		return poster;
	}
	public void setPoster(Object poster) {
		this.poster = poster;
	}
	public Object getDirector() {
		return director;
	}
	public void setDirector(Object director) {
		this.director = director;
	}
	public Object getReview_cnt() {
		return review_cnt;
	}
	public void setReview_cnt(Object review_cnt) {
		this.review_cnt = review_cnt;
	}
	public Object getScore() {
		return score;
	}
	public void setScore(Object score) {
		this.score = score;
	}
	public Object getPerson() {
		return person;
	}
	public void setPerson(Object person) {
		this.person = person;
	}
	public FilmoVo(Object _id, Object movie_id, Object movie_title, Object poster, Object director, Object review_cnt,
			Object score, Object person) {
		this._id = _id;
		this.movie_id = movie_id;
		this.movie_title = movie_title;
		this.poster = poster;
		this.director = director;
		this.review_cnt = review_cnt;
		this.score = score;
		this.person = person;
	}
	public FilmoVo() {
		super();
	}
	@Override
	public String toString() {
		return "FilmoVo [_id=" + _id + ", movie_id=" + movie_id + ", movie_title=" + movie_title + ", poster=" + poster
				+ ", director=" + director + ", review_cnt=" + review_cnt + ", score=" + score + ", person=" + person
				+ "]";
	}

	
	
}
