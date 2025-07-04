package com.api.sales_system.repository;

import com.api.sales_system.entity.ProviderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderProductRepository extends JpaRepository<ProviderProduct, Long> {}
