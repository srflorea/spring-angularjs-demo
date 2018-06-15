package com.example.democompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.democompany.model.Company;
import com.example.democompany.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}
	
	public Iterable<Company> getAllCompanies() {
		return companyRepository.findAll();
	}
}
