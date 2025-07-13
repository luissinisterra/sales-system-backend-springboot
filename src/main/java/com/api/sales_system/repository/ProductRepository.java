package com.api.sales_system.repository;

import com.api.sales_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Búsqueda por nombre (case-insensitive, partial match)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Búsqueda por categoría (case-insensitive)
    List<Product> findByCategoryIgnoreCase(String category);

    // Búsqueda por proveedor
    List<Product> findByProviderId(Long providerId);

    // Búsqueda por rango de precios
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Productos con stock bajo
    List<Product> findByStockLessThan(Integer threshold);

    // Productos sin stock
    List<Product> findByStock(Integer stock);

    // Productos disponibles (con stock)
    List<Product> findByStockGreaterThan(Integer stock);

    // Productos más caros (usando consulta personalizada para el límite)
    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> findTopByOrderByPriceDesc(@Param("limit") Integer limit);

    // Otra opción para los productos más caros usando Pageable
    // List<Product> findAllByOrderByPriceDesc(Pageable pageable);

    // Obtener todas las categorías únicas
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.category IS NOT NULL ORDER BY p.category")
    List<String> findDistinctCategories();

    // Contar productos por categoría
    Long countByCategoryIgnoreCase(String category);
}