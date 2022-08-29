package com.db.tycoonmusicfestivalus.repository;

import com.db.tycoonmusicfestivalus.entity.Collective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectiveRepository extends JpaRepository<Collective, Long> {

    List<Collective> findCollectiveByName(String name);
}
