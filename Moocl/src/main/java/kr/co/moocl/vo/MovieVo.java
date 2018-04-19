package kr.co.moocl.vo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

@Document(collection ="movie_daum")
public class MovieVo {

	@Id
	private String _id;
	private List<BasicDBObject> movie_id;
	private String movie_title;
	private int running_time;
	private List<BasicDBObject>  review_cnt;
	private List<BasicDBObject>  score;
	
	public MovieVo(String _id, List<BasicDBObject> movie_id, String movie_title, int running_time,
			List<BasicDBObject> review_cnt, List<BasicDBObject> score) {
		this._id = _id;
		this.movie_id = movie_id;
		this.movie_title = movie_title;
		this.running_time = running_time;
		this.review_cnt = review_cnt;
		this.score = score;
	}

	public MovieVo() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public List<BasicDBObject> getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(List<BasicDBObject> movie_id) {
		this.movie_id = movie_id;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public int getRunning_time() {
		return running_time;
	}

	public void setRunning_time(int running_time) {
		this.running_time = running_time;
	}

	public List<BasicDBObject> getReview_cnt() {
		return review_cnt;
	}

	public void setReview_cnt(List<BasicDBObject> review_cnt) {
		this.review_cnt = review_cnt;
	}

	public List<BasicDBObject> getScore() {
		return score;
	}

	public void setScore(List<BasicDBObject> score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "MovieVo [_id=" + _id + ", movie_id=" + movie_id + ", movie_title=" + movie_title + ", running_time="
				+ running_time + ", review_cnt=" + review_cnt + ", score=" + score + "]";
	}
	
	
}
