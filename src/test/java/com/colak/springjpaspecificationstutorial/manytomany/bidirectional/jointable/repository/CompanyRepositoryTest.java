package com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.repository;

import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.jpa.Company;
import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.specification.CompanySpecifications;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

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
        assertThat(companyList).hasSize(2);
    }
}
