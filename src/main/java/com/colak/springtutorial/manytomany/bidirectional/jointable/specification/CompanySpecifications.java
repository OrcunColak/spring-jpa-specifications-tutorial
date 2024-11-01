package com.colak.springtutorial.manytomany.bidirectional.jointable.specification;

import com.colak.springtutorial.manytomany.bidirectional.jointable.jpa.Company;
import com.colak.springtutorial.manytomany.bidirectional.jointable.jpa.Project;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Locale;

@UtilityClass
public class CompanySpecifications {

    /**
     * Example for CriteriaBuilder#equal()
     */
    public static Specification<Company> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Company> hasNameLike(String name) {
        return (root, query, criteriaBuilder) -> {
            String upperCaseName = name.toUpperCase(Locale.ROOT);
            Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), likePattern(upperCaseName));
            assert query != null;

            // we can also use an orderBy in the Specification
            query.orderBy(criteriaBuilder.asc(root.get("id")));
            return predicate;
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }

    /**
     * Example for Expression#in()
     */
    public static Specification<Company> hasNames(List<String> names) {
        return (root, query, criteriaBuilder) -> root.get("name").in(names);
    }

    /**
     * Example for CriteriaBuilder#between()
     */
    public static Specification<Company> isWithinIdRange(int min, int max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("id"), min, max);
    }

    public static Specification<Company> hasAtLeastProjects(int minProjects) {
        return (root, query, criteriaBuilder) -> {
            Join<Company, Project> projects = root.join("projects");
            assert query != null;

            query.groupBy(root.get("id"));
            Expression<Long> projectCount = criteriaBuilder.count(projects);
            criteriaBuilder.greaterThanOrEqualTo(projectCount, Long.valueOf(minProjects));
            return query.getRestriction();
        };
    }
}
