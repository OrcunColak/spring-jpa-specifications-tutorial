package com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.repository;

import com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.jpa.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
