package com.example.democompany.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.democompany.exception.NotFoundException;
import com.example.democompany.model.Company;
import com.example.democompany.model.Owner;
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
	
	public Company getCompanyById(String id) throws NotFoundException {
		Optional<Company> optionalCompany = companyRepository.findById(id);

		if (!optionalCompany.isPresent()) {
			throw new NotFoundException();
		}

		return optionalCompany.get();
	}

	public Company addOwnerToCompany(Company company, Owner owner) {
		company.getOwners().add(owner);
		return companyRepository.save(company);
	}
}
