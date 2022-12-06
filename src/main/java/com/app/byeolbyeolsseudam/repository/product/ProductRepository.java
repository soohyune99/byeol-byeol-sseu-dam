package com.app.byeolbyeolsseudam.repository.product;

import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {


}
