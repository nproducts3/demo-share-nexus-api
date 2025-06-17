package com.example.demo.repository;

import com.example.demo.entity.SettingsProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsProfileRepository extends JpaRepository<SettingsProfile, String> {
    boolean existsByEmail(String email);
} 