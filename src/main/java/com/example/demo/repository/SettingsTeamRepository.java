package com.example.demo.repository;

import com.example.demo.entity.SettingsTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsTeamRepository extends JpaRepository<SettingsTeam, String> {
} 