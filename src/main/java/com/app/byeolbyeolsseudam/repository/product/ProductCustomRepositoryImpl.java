package com.app.byeolbyeolsseudam.repository.product;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.domain.product.QProductDTO;
import com.app.byeolbyeolsseudam.domain.review.QReviewDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.product.QProduct;
import com.app.byeolbyeolsseudam.entity.review.QReview;
import com.app.byeolbyeolsseudam.entity.review.Review;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.product.QProduct.product;
import static com.app.byeolbyeolsseudam.entity.review.QReview.review;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {
    public final JPAQueryFactory jpaQueryFactory;

    // 전체 조회
    @Override
    public List<ProductDTO> showAll(){
        List<ProductDTO> products = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .orderBy(product.createdDate.desc())
                .fetch();

        return products;
    }

    // 카테고리 생활 클릭 시 조회
    @Override
    public List<ProductDTO> showLiving(){
        List<ProductDTO> productLiving = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productCategory.eq(ProductCategory.생활))
                .orderBy(product.createdDate.desc())
                .fetch();
        return productLiving;
    }

    // 카테고리 주방 클릭 시 조회
    @Override
    public List<ProductDTO> showKitchen(){
        List<ProductDTO> productKitchen = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productCategory.eq(ProductCategory.주방))
                .orderBy(product.createdDate.desc())
                .fetch();
        return productKitchen;
    }

    // 카테고리 욕실 클릭 시 조회
    @Override
    public List<ProductDTO> showBathroom(){
        List<ProductDTO> productBathroom = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productCategory.eq(ProductCategory.욕실))
                .orderBy(product.createdDate.desc())
                .fetch();
        return productBathroom;
    }

    // 카테고리 식품 클릭 시 조회
    @Override
    public List<ProductDTO> showFood(){
        List<ProductDTO> productFood = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productCategory.eq(ProductCategory.식품))
                .orderBy(product.createdDate.desc())
                .fetch();
        return productFood;
    }

    // 카테고리 취미 클릭 시 조회
    @Override
    public List<ProductDTO> showHobby(){
        List<ProductDTO> productHobby = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productCategory.eq(ProductCategory.취미))
                .orderBy(product.createdDate.desc())
                .fetch();
        return productHobby;
    }

    // 카테고리 문구 클릭 시 조회
    @Override
    public List<ProductDTO> showOffice(){
        List<ProductDTO> productOffice= jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productCategory.eq(ProductCategory.문구))
                .orderBy(product.createdDate.desc())
                .fetch();
        return productOffice;
    }

    // 카테고리 반려동물 클릭 시 조회
    @Override
    public List<ProductDTO> showPet(){
        List<ProductDTO> productPet = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productCategory.eq(ProductCategory.반려동물))
                .orderBy(product.createdDate.desc())
                .fetch();
        return productPet;
    }

    // 상세 조회
    public ProductDTO showDetail(Long productId){
        ProductDTO show = jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid))
                .from(product)
                .where(product.productId.eq(productId))
                .orderBy(product.createdDate.desc())
                .limit(1)
                .fetchOne();

        List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
                review.reviewId, review.reviewContent, review.reviewStar,
                review.product.productId,review.member.memberId, review.member.memberName,
                review.reviewFileName, review.reviewFilePath, review.reviewFileUuid,
                review.createdDate))
                .from(review)
                .where(review.product.productId.eq(productId))
                .fetch();
        return show;
    }
}
