package com.example.democompany.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "company")
public class Company {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String name;
	private String address;
	private String city;
	private String country;
	private String email;
	private String phoneNumber;
	
	@OneToMany(targetEntity=Owner.class, mappedBy="id", fetch=FetchType.EAGER)
	private List<String> owners;

	public Company() {}
	
	public Company(String id) {
		this.id = id;
	}
	
	@JsonCreator
    public Company(
    		@JsonProperty("id") String id,
    		@JsonProperty("name") String name,
    		@JsonProperty("address") String address,
    		@JsonProperty("city") String city,
    		@JsonProperty("country") String country,
    		@JsonProperty(value="email", required=false) String email,
    		@JsonProperty(value="phoneNumber", required=false) String phoneNumber,
    		@JsonProperty(value="owners", required=false) List<String> owners) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.owners = owners;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<String> getOwners() {
		return owners;
	}
	public void setOwners(List<String> owners) {
		this.owners = owners;
	}
}
