package net.sb.departmentservice.service;

import net.sb.departmentservice.entity.Department;

public interface DepartmentService {
    Department saveDepartment(Department department);
    Department getDepartmentById(Long departmentId);
}
