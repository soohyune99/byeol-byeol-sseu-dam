package com.app.byeolbyeolsseudam.repository.admin.product;

import com.app.byeolbyeolsseudam.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProductRepository extends JpaRepository<Product, Long>, AdminProductCustomRepository {
}
