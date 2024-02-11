package com.colak.springjpaspecificationstutorial.manytomany.bidirectional.jointable.specification.projection;

/**
 * Nested projection example
 */
public interface CompanyProjectProjection {

    String getName();

    interface ProjectProjection {
        String getName();
    }

    ProjectProjection getProjects();

}
