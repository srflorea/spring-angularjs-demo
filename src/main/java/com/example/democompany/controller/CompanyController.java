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
	public HttpEntity<Company> getCompany(@PathVariable String id) throws NotFoundException {
		/*for (Company company : companies) {
			if (company.getId().equals(id)) {
				return new HttpEntity<Company>(company);
			}
		}*/

		throw new NotFoundException();
	}
}
