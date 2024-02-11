package com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.service;

import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.jpa.Company;
import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.repository.CompanyRepository;
import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.specification.CompanySpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
