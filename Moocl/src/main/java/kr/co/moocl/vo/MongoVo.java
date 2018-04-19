package kr.co.moocl.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "local")    //작성시 user Document에 작성하겠다.
public class MongoVo {	
	
	@Id //_id변수가 DB안에서 id값을 나타낸다는 Annotation
	private String _id;
	private String name;
	private int age;
	private String status;
	
	
	public MongoVo() {
	}
	public MongoVo(String _id, String name, int age, String status) {
		super();
		this._id = _id;
		this.name = name;
		this.age = age;
		this.status = status;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MongoVo [_id=" + _id + ", name=" + name + ", age=" + age + ", status=" + status + "]";
	}
	
	

}
