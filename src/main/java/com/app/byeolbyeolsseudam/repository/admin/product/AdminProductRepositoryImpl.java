package com.app.byeolbyeolsseudam.repository.admin.product;

import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.domain.product.QProductDTO;
import com.app.byeolbyeolsseudam.entity.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.product.QProduct.product;

@Repository
@RequiredArgsConstructor
public class AdminProductRepositoryImpl implements AdminProductCustomRepository{
private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductDTO> searchProduct(String keyword) {
        return jpaQueryFactory.select(new QProductDTO(
                product.productId,
                product.productCategory,
                product.productName,
                product.productPrice,
                product.productCount,
                product.productFileDetailName,
                product.productFileDetailPath,
                product.productFileDetailUuid,
                product.productFileProfileName,
                product.productFileProfilePath,
                product.productFileProfileUuid
        )).from(product)
                .where(product.productName.contains(keyword))
                .orderBy(product.productId.desc())
                .fetch();
    }

    @Override
    public List<ProductDTO> searchProductPaging(String keyword, Pageable pageable) {
        return jpaQueryFactory.select(new QProductDTO(
                product.productId,
                product.productCategory,
                product.productName,
                product.productPrice,
                product.productCount,
                product.productFileDetailName,
                product.productFileDetailPath,
                product.productFileDetailUuid,
                product.productFileProfileName,
                product.productFileProfilePath,
                product.productFileProfileUuid
        )).from(product)
                .where(product.productName.contains(keyword))
                .orderBy(product.productId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public ProductDTO selectById(Long productId) {
        return jpaQueryFactory.select(new QProductDTO(
                product.productId,
                product.productCategory,
                product.productName,
                product.productPrice,
                product.productCount,
                product.productFileDetailName,
                product.productFileDetailPath,
                product.productFileDetailUuid,
                product.productFileProfileName,
                product.productFileProfilePath,
                product.productFileProfileUuid
        )).from(product)
                .where(product.productId.eq(productId))
                .fetchOne();
    }

    @Override
    public void update(ProductDTO productDTO) {
        jpaQueryFactory.selectFrom(product)
                .where(product.productId.eq(productDTO.getProductId()))
                .fetchOne().update(productDTO);
    }
}
