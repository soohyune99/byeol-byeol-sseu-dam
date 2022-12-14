package com.app.byeolbyeolsseudam.repository.product;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.domain.product.QProductDTO;
import com.app.byeolbyeolsseudam.domain.review.QReviewDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.entity.product.QProduct;
import com.app.byeolbyeolsseudam.entity.review.QReview;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.product.QProduct.product;
import static com.app.byeolbyeolsseudam.entity.review.QReview.review;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {
    public final JPAQueryFactory jpaQueryFactory;

    // 전체 조회, 검색 및 카테고리 선택 시, 무한 스크롤
    @Override
    public List<ProductDTO> selectProducts(Criteria criteria){
        List<ProductDTO> products = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(nameLike(criteria.getKeyword()), categoryEq(criteria.getCategory()) )
                .orderBy(product.createdDate.desc())
                .offset(criteria.getPage() * 10)
                .limit(12)
                .fetch();

        products.stream().forEach(review -> {
            List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
                    QReview.review.reviewId, QReview.review.reviewContent, QReview.review.reviewStar,
                    QReview.review.product.productId,QReview.review.member.memberId,
                    QReview.review.member.memberName, QReview.review.reviewFileName,
                    QReview.review.reviewFilePath, QReview.review.reviewFileUuid,
                    QReview.review.createdDate))
                    .from(QReview.review)
                    .where(QReview.review.product.productId.eq(product.productId))
                    .fetch();
            review.setReviews(reviews);
        });
        return products;
    }

    /* 동적 쿼리 조건 */
    private BooleanExpression nameLike(String name){
        return StringUtils.hasText(name)? product.productName.contains(name) : null;
    }

    private BooleanExpression categoryEq(String category){
        return StringUtils.hasText(category)? product.productCategory.eq(ProductCategory.valueOf(category)) : null;
    }

    // 상세 조회 + 댓글
    @Override
    public ProductDTO readProduct(Long productId){

        ProductDTO productDTO = jpaQueryFactory.select(new QProductDTO(
                QProduct.product.productId,QProduct.product.productCategory, QProduct.product.productName,
                QProduct.product.productPrice,QProduct.product.productCount,QProduct.product.productFileDetailName,
                QProduct.product.productFileDetailPath,QProduct.product.productFileDetailUuid,QProduct.product.productFileProfileName,
                QProduct.product.productFileProfilePath,QProduct.product.productFileProfileUuid))
                .from(QProduct.product)
                .where(QProduct.product.productId.eq(productId))
                .fetchOne();

        List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
                review.reviewId, review.reviewContent, review.reviewStar,
                review.product.productId,review.member.memberId, review.member.memberName,
                review.reviewFileName, review.reviewFilePath, review.reviewFileUuid,
                review.createdDate))
                .from(review)
                .where(review.product.productId.eq(productId))
                .fetch();

        productDTO.setReviews(reviews);

        return productDTO;
    }


    // 카테고리 클릭 시 조회
/*    @Override
    public List<ProductDTO> selectProductofCategory(ProductCategory productCategory){
        List<ProductDTO> products = jpaQueryFactory.select(new QProductDTO(
                QProduct.product.productId, QProduct.product.productCategory,QProduct.product.productName,
                QProduct.product.productPrice, QProduct.product.productCount, QProduct.product.productFileDetailName,
                QProduct.product.productFileDetailPath, QProduct.product.productFileDetailUuid, QProduct.product.productFileProfileName,
                QProduct.product.productFileProfilePath, QProduct.product.productFileProfileUuid ))
                .from(QProduct.product)
                .where(QProduct.product.productCategory.eq(productCategory))
                .orderBy(QProduct.product.createdDate.desc())
                .limit(12)
                .fetch();


        products.stream().forEach(review -> {
            List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
                    QReview.review.reviewId, QReview.review.reviewContent, QReview.review.reviewStar,
                    QReview.review.product.productId,QReview.review.member.memberId,
                    QReview.review.member.memberName, QReview.review.reviewFileName,
                    QReview.review.reviewFilePath, QReview.review.reviewFileUuid,
                    QReview.review.createdDate))
                    .from(QReview.review)
                    .where(QReview.review.product.productId.eq(product.productId))
                    .fetch();
            review.setReviews(reviews);
        });
        return products;
    }*/

    // 마켓 검색
  /*  @Override
    public List<ProductDTO> selectProductofKeyword(String keyword){
        List<ProductDTO> products = jpaQueryFactory.select(new QProductDTO(
                QProduct.product.productId, QProduct.product.productCategory,QProduct.product.productName,
                QProduct.product.productPrice, QProduct.product.productCount, QProduct.product.productFileDetailName,
                QProduct.product.productFileDetailPath, QProduct.product.productFileDetailUuid, QProduct.product.productFileProfileName,
                QProduct.product.productFileProfilePath, QProduct.product.productFileProfileUuid ))
                .from(QProduct.product)
                .where(product.productName.contains(keyword))
                .orderBy(product.createdDate.desc())
                .limit(12)
                .fetch();

        products.stream().forEach(product -> {
            List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
                    QReview.review.reviewId, QReview.review.reviewContent, QReview.review.reviewStar,
                    QReview.review.product.productId,QReview.review.member.memberId,
                    QReview.review.member.memberName, QReview.review.reviewFileName,
                    QReview.review.reviewFilePath, QReview.review.reviewFileUuid,
                    QReview.review.createdDate))
                    .from(QReview.review)
                    .where(QReview.review.product.productId.eq(product.getProductId()))
                    .fetch();
            product.setReviews(reviews);
        });
        return products;
    }*/

    // 무한 스크롤
    /*@Override
    public List<ProductDTO> selectScrollProducts(Criteria criteria){
        List<ProductDTO> products = jpaQueryFactory.select(new QProductDTO(
                QProduct.product.productId, QProduct.product.productCategory,QProduct.product.productName,
                QProduct.product.productPrice, QProduct.product.productCount, QProduct.product.productFileDetailName,
                QProduct.product.productFileDetailPath, QProduct.product.productFileDetailUuid, QProduct.product.productFileProfileName,
                QProduct.product.productFileProfilePath, QProduct.product.productFileProfileUuid ))
                .from(QProduct.product)
                .where(nameLike(criteria.getKeyword()), categoryEq(criteria.getCategory()))
                .offset(criteria.getPage() * 10)
                .limit(12)
                .fetch();

        products.stream().forEach(product -> {
            List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
                    QReview.review.reviewId, QReview.review.reviewContent, QReview.review.reviewStar,
                    QReview.review.product.productId,QReview.review.member.memberId,
                    QReview.review.member.memberName, QReview.review.reviewFileName,
                    QReview.review.reviewFilePath, QReview.review.reviewFileUuid,
                    QReview.review.createdDate))
                    .from(QReview.review)
                    .where(QReview.review.product.productId.eq(product.getProductId()))
                    .fetch();
            product.setReviews(reviews);
        });
        return products;
    }*/

    // 주문하기
    @Override
    public ProductDTO selectProduct(Long productId){
        return jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productId.eq(productId))
                .fetchOne();
    }
}
