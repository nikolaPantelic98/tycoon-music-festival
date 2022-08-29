package com.db.tycoonmusicfestivalus.repository;

import com.db.tycoonmusicfestivalus.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
