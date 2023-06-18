package com.danielbenami.dragontail.repository;

import com.danielbenami.dragontail.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByNameIn(List<String> names);
    Optional<Stock> findByName(String name);
}