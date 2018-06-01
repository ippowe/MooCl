package kr.co.moocl.vo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
public class PeopleVo {
	
	@Id
	private Object _id;
	private Object person_id;
	private String person_name;
	private String role;
	private String part;
	private String people_img;
	private String wordcloudDate;
	private List<String> wordcloudList;
	
	public PeopleVo(Object _id, Object person_id, String person_name, String role, String part, String people_img) {
		this._id = _id;
		this.person_id = person_id;
		this.person_name = person_name;
		this.role = role;
		this.part = part;
		this.people_img = people_img;
	}
	
	public PeopleVo() {
	}
	
	public Object get_id() {
		return _id;
	}
	
	public Object getPerson_id() {
		return person_id;
	}
	
	public String getPerson_name() {
		return person_name;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getPart() {
		return part;
	}
	
	public String getPeople_img() {
		return people_img;
	}
	
	public void set_id(Object _id) {
		this._id = _id;
	}
	
	public void setPerson_id(Object person_id) {
		this.person_id = person_id;
	}
	
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setPart(String part) {
		this.part = part;
	}
	
	public void setPeople_img(String people_img) {
		this.people_img = people_img;
	}
	
	public String getWordcloudDate() {
		return wordcloudDate;
	}

	public void setWordcloudDate(String wordcloudDate) {
		this.wordcloudDate = wordcloudDate;
	}

	public List<String> getWordcloudList() {
		return wordcloudList;
	}

	public void setWordcloudList(List<String> wordcloudList) {
		this.wordcloudList = wordcloudList;
	}

	@Override
	public String toString() {
		return "PeopleVo [_id=" + _id + ", person_id=" + person_id + ", person_name=" + person_name + ", role=" + role
				+ ", part=" + part + ", people_img=" + people_img + ", wordcloudDate=" + wordcloudDate
				+ ", wordcloudList=" + wordcloudList + "]";
	}


	
}
