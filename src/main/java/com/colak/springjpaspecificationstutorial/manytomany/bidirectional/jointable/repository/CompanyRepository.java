package com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.repository;

import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.jpa.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// We can also use QuerydslPredicateExecutor  instead of JpaSpecificationExecutor
public interface CompanyRepository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {
}
