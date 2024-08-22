package com.colak.springtutorial.manytomany.bidirectional.jointable.service;

import com.colak.springtutorial.manytomany.bidirectional.jointable.jpa.Company;
import com.colak.springtutorial.manytomany.bidirectional.jointable.repository.CompanyRepository;
import com.colak.springtutorial.manytomany.bidirectional.jointable.specification.CompanySpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public List<Company> findAllWithName(String name) {
        Specification<Company> specification = CompanySpecifications.hasName(name);
        return companyRepository.findAll(specification);
    }
}
