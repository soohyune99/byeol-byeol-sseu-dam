package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.BasketDTO;
import com.app.byeolbyeolsseudam.repository.BasketRepository;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.ProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.QBasket.basket;
import static com.app.byeolbyeolsseudam.entity.QMember.member;
import static com.app.byeolbyeolsseudam.entity.QNotice.notice;
import static com.app.byeolbyeolsseudam.entity.QProduct.product;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class BasketTest {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){
        BasketDTO basket = new BasketDTO();

        basket.setMember(memberRepository.findAll().get(0));
        basket.setProduct(productRepository.findAll().get(0));

        basketRepository.save(basket.toEntity());
    }

    @Test
    public void findTest(){
        Optional<Basket> findBasket = basketRepository.findById(8L);

        if(findBasket.isPresent()){
            Assertions.assertThat(findBasket.get().getMember().getMemberName().equals("은지"));

            log.info("ProductName : " + findBasket.get().getProduct().getProductName());
        }
    }

    @Test
    public void queryUpdateTest(){
        jpaQueryFactory.selectFrom(basket)
                .where(basket.basketId.eq(8L))
                .orderBy(basket.basketId.desc())
                .limit(1)
                .fetchOne()
                .update(
                        jpaQueryFactory.selectFrom(member).limit(1).fetchOne(),
                        jpaQueryFactory.selectFrom(product).limit(1).fetchOne());
    }

    @Test
    public void queryDeleteTest(){
        jpaQueryFactory.delete(basket)
                .where(basket.basketId.eq(16L))
                .execute();
    }
}
