package com.db.tycoonmusicfestivalus.repository;

import com.db.tycoonmusicfestivalus.entity.Songwriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongwriterRepository extends JpaRepository<Songwriter, Long> {
}
