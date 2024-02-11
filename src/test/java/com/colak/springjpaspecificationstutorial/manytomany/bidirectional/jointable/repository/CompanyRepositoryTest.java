package com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.repository;

import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.jpa.Company;
import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.specification.CompanySpecifications;
import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.specification.projection.CompanyNameProjection;
import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.specification.projection.CompanyProjectProjection;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.extractProperty;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Slf4j
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testHasName() {
        Specification<Company> specification = CompanySpecifications.hasName("company-1");
        List<Company> companyList = companyRepository.findAll(specification);
        assertThat(companyList).hasSize(1);
    }

    @Test
    void testHasNameLike() {
        Specification<Company> specification = CompanySpecifications.hasNameLike("company");
        List<Company> companyList = companyRepository.findAll(specification);

        List<String> expectedNames = List.of("company-1", "company-2");
        List<Object> actualNames = extractProperty("name").from(companyList);

        assertThat(actualNames).containsExactlyElementsOf(expectedNames);
    }

    @Test
    void testCombinedSpecification() {
        Specification<Company> hasAtLeastProjects = CompanySpecifications.hasAtLeastProjects(1);
        Specification<Company> specification = CompanySpecifications.hasNameLike("company")
                .and(hasAtLeastProjects);

        // The projects attribute is lazy
        List<Company> companyList = companyRepository.findAll(specification);

        List<String> expectedNames = List.of("company-1");
        List<Object> actualNames = extractProperty("name").from(companyList);

        assertThat(actualNames).containsExactlyElementsOf(expectedNames);
    }

    @Test
    void testCompanyProjection() {
        // The projects attribute is lazy
        List<CompanyNameProjection> companyList = companyRepository.findAllProjectedBy(CompanyNameProjection.class);

        List<String> expectedNames = List.of("company-1" , "company-2");
        List<Object> actualNames = extractProperty("name").from(companyList);

        assertThat(actualNames).containsExactlyElementsOf(expectedNames);
    }

    @Test
    void testCompanyProjectProjection() {
        // The projects attribute is lazy
        List<CompanyProjectProjection> companyList = companyRepository.findAllProjectedBy(CompanyProjectProjection.class);

        List<String> expectedNames = List.of("company-1" , "company-2");
        List<Object> actualNames = extractProperty("name").from(companyList);

        assertThat(actualNames).containsExactlyElementsOf(expectedNames);
    }

}
