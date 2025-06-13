package com.example.demo.repository;

import com.example.demo.entity.DemoSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DemoSessionRepository extends JpaRepository<DemoSession, String>, PagingAndSortingRepository<DemoSession, String> {
    Page<DemoSession> findByDateGreaterThanEqual(LocalDate date, Pageable pageable);
    Page<DemoSession> findByTechnologyContainingIgnoreCase(String technology, Pageable pageable);
    Page<DemoSession> findByDifficulty(DemoSession.DifficultyLevel difficulty, Pageable pageable);
} 