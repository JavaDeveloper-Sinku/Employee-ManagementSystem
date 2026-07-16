package com.example.Employee.Management.specification;

import com.example.Employee.Management.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasDepartment(
            String department
    ) {

        return (root, query, cb) -> {

            if (department == null || department.isBlank()) {
                return cb.conjunction();
            }

            return cb.equal(
                    root.get("department"),
                    department
            );
        };
    }


    public static Specification<Employee> hasMinSalary(
            Double minSalary
    ) {

        return (root, query, cb) -> {

            if (minSalary == null) {
                return cb.conjunction();
            }

            return cb.greaterThanOrEqualTo(
                    root.get("salary"),
                    minSalary
            );
        };
    }

    public static Specification<Employee> hasMaxSalary(
            Double maxSalary
    ) {

        return (root, query, cb) -> {

            if (maxSalary == null) {
                return cb.conjunction();
            }

            return cb.lessThanOrEqualTo(
                    root.get("salary"),
                    maxSalary
            );
        };
    }
}
