package com.example.demo.repository;

import com.example.demo.entity.UserManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserManagementRepository extends JpaRepository<UserManagement, String>, PagingAndSortingRepository<UserManagement, String> {
    Optional<UserManagement> findByEmail(String email);
    Page<UserManagement> findByDepartment(String department, Pageable pageable);
    Page<UserManagement> findByRole(UserManagement.UserRole role, Pageable pageable);
    Page<UserManagement> findByStatus(UserManagement.UserStatus status, Pageable pageable);
    Page<UserManagement> findBySkillLevel(UserManagement.SkillLevel skillLevel, Pageable pageable);
    boolean existsByEmail(String email);

} 