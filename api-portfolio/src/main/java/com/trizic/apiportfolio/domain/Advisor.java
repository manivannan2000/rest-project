package com.trizic.apiportfolio.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Advisor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@Id
//	@GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name="system-uuid", strategy = "uuid")
//	@Column(name = "id", columnDefinition = "BINARY(16)")
//	protected UUID id;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String plannerId;
	

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String userId;
	
	
	@OneToMany
	private List<Model> cities; 	

	protected Advisor() {
		// no-args constructor required by JPA spec
		// this one is protected since it shouldn't be used directly
	}

	public Advisor(String name, String userId) {
		this.name = name;
		this.userId = userId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public List<Model> getCities() {
		return cities;
	}
	
	public String getPlannerId() {
		return plannerId;
	}
	
}
