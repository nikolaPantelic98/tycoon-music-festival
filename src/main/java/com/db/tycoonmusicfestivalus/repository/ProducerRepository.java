package com.db.tycoonmusicfestivalus.repository;

import com.db.tycoonmusicfestivalus.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    List<Producer> findProducerByNickname(String nickname);
}
