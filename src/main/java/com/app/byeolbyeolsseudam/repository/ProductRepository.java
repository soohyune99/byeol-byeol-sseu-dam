package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
