package com.api.sales_system.repository;

import com.api.sales_system.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetaillRepository extends JpaRepository<SaleDetail, Long> {}
