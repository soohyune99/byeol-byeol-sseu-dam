package com.app.byeolbyeolsseudam.repository.basket;

import com.app.byeolbyeolsseudam.domain.basket.BasketDTO;
import com.app.byeolbyeolsseudam.domain.basket.QBasketDTO;
import com.app.byeolbyeolsseudam.entity.basket.QBasket;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.basket.QBasket.basket;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BasketCustomRepositoryImpl implements BasketCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    // 장바구니 전체 조회
    @Override
    public List<BasketDTO> selectBasketList(Long memberId){
        List<BasketDTO> baskets = jpaQueryFactory.select(new QBasketDTO(
                basket.basketId, basket.basketCount, basket.member.memberId,
                basket.product.productId, basket.product.productCategory,
                basket.product.productName, basket.product.productPrice,
                basket.product.productFileDetailName,basket.product.productFileDetailPath,
                basket.product.productFileDetailUuid, basket.product.productFileProfileName,
                basket.product.productFileProfilePath,
                basket.product.productFileProfileUuid))
                .from(basket)
                .where(basket.member.memberId.eq(memberId))
                .orderBy(basket.createdDate.desc())
                .fetch();

        return baskets;
    }

    // 장바구니 한개 조회
    @Override
    public BasketDTO selectBasket(Long basketId){
        return jpaQueryFactory.select(new QBasketDTO(
                basket.basketId, basket.basketCount, basket.member.memberId,
                basket.product.productId, basket.product.productCategory,
                basket.product.productName, basket.product.productPrice,
                basket.product.productFileDetailName,basket.product.productFileDetailPath,
                basket.product.productFileDetailUuid, basket.product.productFileProfileName,
                basket.product.productFileProfilePath,
                basket.product.productFileProfileUuid))
                .from(basket)
                .where(basket.basketId.eq(basketId))
                .fetchOne();
    }

}
