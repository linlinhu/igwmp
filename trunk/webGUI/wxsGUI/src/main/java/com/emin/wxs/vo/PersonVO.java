package com.emin.wxs.vo;

import com.emin.platform.domain.Person;

public class PersonVO {
	
	private Long id;
	
	private String name;
	
	private String mobilePhone;
	
	private String email;
	
	/**
	 * 1:男 ,0:女
	 */
	private Integer gender;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public static PersonVO personToVO(Person person){
		
		if(person==null)return null;
		PersonVO vo = new PersonVO();
		vo.setId(person.getId());
		vo.setEmail(person.getEmail());
		vo.setMobilePhone(person.getMobilephone());
		vo.setName(person.getName());
		vo.setGender(person.getGender());
		return vo;		
	}
	public  Person convertToPerson(Person person){
		Person p = person;
		if(p==null){
			 p = new Person();
		}
		
		p.setId(this.id);
		p.setType(Person.ACCESS_PERSON);
		p.setName(this.name);
		p.setMobilephone(this.mobilePhone);
		p.setGender(this.gender);
		p.setEmail(this.email);
		p.setCompanyId(1l);
		return p;
	}
}
