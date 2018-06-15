package com.example.democompany.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "owner")
public class Owner {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@JsonCreator
    public Owner(
    		@JsonProperty("id") String id,
    		@JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}

	@JsonCreator
    public Owner(@JsonProperty("name") String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
