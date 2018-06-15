package com.example.democompany.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.democompany.model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {

}
