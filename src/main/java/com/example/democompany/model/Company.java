package com.example.democompany.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@Column(name="company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String address;
	private String city;
	private String country;
	private String email;
	private String phoneNumber;

	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
	private Set<Owner> owners;

	public Company() {}
	
	public Company(Long id) {
		this.id = id;
	}
	
	@JsonCreator
    public Company(
    		@JsonProperty("id") Long id,
    		@JsonProperty("name") String name,
    		@JsonProperty("address") String address,
    		@JsonProperty("city") String city,
    		@JsonProperty("country") String country,
    		@JsonProperty(value="email", required=false) String email,
    		@JsonProperty(value="phoneNumber", required=false) String phoneNumber,
    		@JsonProperty(value="owners", required=false) Set<Owner> owners) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.owners = owners;
    }
	
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
	public Set<Owner> getOwners() {
		return owners;
	}
	public void setOwners(Set<Owner> owners) {
		this.owners = owners;
	}
}
