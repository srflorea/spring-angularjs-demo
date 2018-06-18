package com.example.democompany.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.democompany.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class CompanyControllerTest {

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setup() throws Exception {

	}

	private List<Long> companiesToBeDeleted = new ArrayList<>();

	@After
	public void finish() throws Exception {
		for (Long articleId : companiesToBeDeleted) {
			template.delete("/companies/" + articleId);
		}
	}
	
	@Test
	public void testCompanyShouldBeCreated() throws Exception {
		HttpEntity<Object> company = getHttpEntity("{\"name\":\"company\", \"address\":\"address\", \"city\": \"city\", \"country\": \"country\"}");
		ResponseEntity<Company> resultAsset = template.postForEntity("/companies", company, Company.class);
		
		assertThat(resultAsset.getBody().getId()).isNotNull();
		
		companiesToBeDeleted.add(resultAsset.getBody().getId());
	}
	
	@Test
	public void testCompanyShouldBeCreatedAndRetrieved() throws Exception {
		HttpEntity<Object> company = getHttpEntity("{\"name\":\"company\", \"address\":\"address\", \"city\": \"city\", \"country\": \"country\"}");
		ResponseEntity<Company> resultAssetExpected = template.postForEntity("/companies", company, Company.class);
		
		Long id = resultAssetExpected.getBody().getId();
		companiesToBeDeleted.add(id);
		
		ResponseEntity<Company> resultAssetActual = template.getForEntity("/companies/" + id, Company.class);
		assertThat(resultAssetActual.getBody().getId()).isNotNull();		
		assertThat(resultAssetActual.getBody()).isEqualTo(resultAssetExpected.getBody());
	}
	
	@Test
	public void testCompanyShouldBeCreatedAndOwnerShouldBeAdded() throws Exception {
		HttpEntity<Object> company = getHttpEntity("{\"name\":\"company\", \"address\":\"address\", \"city\": \"city\", \"country\": \"country\"}");
		ResponseEntity<Company> resultAsset = template.postForEntity("/companies", company, Company.class);

		Long id = resultAsset.getBody().getId();
		companiesToBeDeleted.add(id);

		HttpEntity<Object> owner = getHttpEntity("{\"name\":\"owner\"}");
		ResponseEntity<Company> resultAssetWithOwner = template.postForEntity("/companies/" + id + "/owner", owner, Company.class);
		assertThat(resultAssetWithOwner.getBody().getId()).isNotNull();
		assertThat(resultAssetWithOwner.getBody().getOwners()).isNotNull();
		assertThat(resultAssetWithOwner.getBody().getOwners().size()).isEqualTo(1);
	}
	
	@Test
	public void testCompanyShouldBeCreatedAndUpdated() throws Exception {
		HttpEntity<Object> company = getHttpEntity("{\"name\":\"company\", \"address\":\"address\", \"city\": \"city\", \"country\": \"country\"}");
		ResponseEntity<Company> resultAsset = template.postForEntity("/companies", company, Company.class);

		Long id = resultAsset.getBody().getId();
		companiesToBeDeleted.add(id);

		HttpEntity<Object> companyUpdated = getHttpEntity("{\"id\":\"" + id + "\", \"name\":\"company updated\", \"address\":\"address\", \"city\": \"city\", \"country\": \"country\"}");
		ResponseEntity<Company> resultAssetAfterUpdate = template.postForEntity("/companies/" + id + "/owner", companyUpdated, Company.class);
		assertThat(resultAssetAfterUpdate.getBody().getId()).isNotNull();
		
		ResponseEntity<Company> resultAssetActual = template.getForEntity("/companies/" + id, Company.class);
		assertThat(resultAssetActual.getBody()).isEqualTo(resultAssetAfterUpdate.getBody());
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}
}
