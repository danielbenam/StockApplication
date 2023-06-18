package com.danielbenami.dragontail.repository;

import com.danielbenami.dragontail.model.StockUpdatedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockUpdatedDataRepository extends JpaRepository<StockUpdatedData, Long> {
}
