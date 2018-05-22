package org.tw.jpa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name="teacher")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 9181998751400657281L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String sex;

	@Version
	private Integer version=0;
	
	@JSONField(format="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="teacher_student")
	private Set<Student> students = new HashSet<Student>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
