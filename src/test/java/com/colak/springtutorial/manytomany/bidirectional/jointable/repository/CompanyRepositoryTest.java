package com.colak.springtutorial.manytomany.bidirectional.jointable.repository;

import com.colak.springtutorial.manytomany.bidirectional.jointable.jpa.Company;
import com.colak.springtutorial.manytomany.bidirectional.jointable.specification.CompanySpecifications;
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

}
