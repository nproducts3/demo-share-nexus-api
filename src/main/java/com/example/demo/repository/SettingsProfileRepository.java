package com.example.demo.repository;

import com.example.demo.entity.SettingsProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SettingsProfileRepository extends JpaRepository<SettingsProfile, UUID> {
    boolean existsByEmail(String email);
} 