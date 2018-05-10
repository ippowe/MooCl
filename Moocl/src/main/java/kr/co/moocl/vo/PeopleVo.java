package kr.co.moocl.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
public class PeopleVo {

	@Id
	private Object id;	

	@DBRef
	private people person;
		
	public Object getId() {
		return id;
	}

	public people getPerson() {
		return person;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public void setPerson(people person) {
		this.person = person;
	}

	public PeopleVo() {
	}

	public PeopleVo(Object id, people person) {
		super();
		this.id = id;
		this.person = person;
	}

	@Override
	public String toString() {
		return "PeopleVo [id=" + id + ", person=" + person + "]";
	}
}

class people {
	private Object _id;	
	private String name;
	private String people_img;
	
	public people() {
		super();
	}

	public people(Object _id, String name, String people_img) {
		this._id = _id;
		this.name = name;
		this.people_img = people_img;
	}

	public Object get_id() {
		return _id;
	}

	public String getName() {
		return name;
	}

	public String getPeople_img() {
		return people_img;
	}

	public void set_id(Object _id) {
		this._id = _id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPeople_img(String people_img) {
		this.people_img = people_img;
	}

	@Override
	public String toString() {
		return "people [_id=" + _id + ", name=" + name + ", people_img=" + people_img + "]";
	}
	
	
}
