package com.db.tycoonmusicfestivalus.repository;

import com.db.tycoonmusicfestivalus.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
