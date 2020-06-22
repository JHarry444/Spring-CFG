package com.qa.demo.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.persistence.domain.Flower;

@Repository
public interface FlowerRepo extends JpaRepository<Flower, Long>{

	List<Flower> findByType(String type);
}
