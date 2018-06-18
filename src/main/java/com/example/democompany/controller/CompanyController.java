package com.example.democompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.democompany.exception.NotFoundException;
import com.example.democompany.model.Company;
import com.example.democompany.model.Owner;
import com.example.democompany.service.CompanyService;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "/companies", method = RequestMethod.POST)
	public HttpEntity<Company> addCompany(@RequestBody Company company) {
		
		Company savedCompany = companyService.saveCompany(company);
		
		return new HttpEntity<>(savedCompany);
	}
	
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public HttpEntity<Iterable<Company>> getCompanies() {

		Iterable<Company> companies = companyService.getAllCompanies();
		
		return new HttpEntity<>(companies);
	}
	
	@RequestMapping(value = "companies/{id}", method = RequestMethod.GET)
	public HttpEntity<Company> getCompany(@PathVariable Long id) throws NotFoundException {
		Company company = companyService.getCompanyById(id);

		return new HttpEntity<Company>(company);
	}

	@RequestMapping(value = "companies/{id}/owner", method = RequestMethod.POST, consumes="application/json")
	public HttpEntity<Company> addOwner(@PathVariable Long id, @RequestBody Owner owner) throws NotFoundException {
		Company company = companyService.getCompanyById(id);
		
		company = companyService.addOwnerToCompany(company, owner);

		return new HttpEntity<Company>(company);
	}

	@RequestMapping(value = "/companies", method = RequestMethod.PUT)
	public HttpEntity<Company> updateCompany(@RequestBody Company company) throws NotFoundException {
		Company savedCompany = companyService.saveCompany(company);
		
		return new HttpEntity<>(savedCompany);
	}

	@RequestMapping(value = "/companies{id}", method = RequestMethod.DELETE)
	public void deleteCompany(@PathVariable Long id) throws NotFoundException {
		Company company = companyService.getCompanyById(id);

		companyService.deleteCompany(company);
	}
}
