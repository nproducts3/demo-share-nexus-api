package com.example.demo.repository;

import com.example.demo.entity.AdvancedSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvancedSettingsRepository extends JpaRepository<AdvancedSettings, String> {
} 