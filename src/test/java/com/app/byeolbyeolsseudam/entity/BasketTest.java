package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.BasketDTO;
import com.app.byeolbyeolsseudam.repository.BasketRepository;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.ProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.hibernate.criterion.Projection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.QBasket.basket;
import static com.app.byeolbyeolsseudam.entity.QMember.member;
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
        BasketDTO basket1 = new BasketDTO();
        BasketDTO basket2 = new BasketDTO();

        basket1.setBasketCount(6);
        Basket basketEntity1 = basket1.toEntity();
        basketEntity1.changeMember(memberRepository.findById(1L).get());
        basketEntity1.changeProduct(productRepository.findById(5L).get());
        basketRepository.save(basketEntity1);

        basket2.setBasketCount(3);
        Basket basketEntity2 = basket1.toEntity();
        basketEntity2.changeMember(memberRepository.findById(1L).get());
        basketEntity2.changeProduct(productRepository.findById(4L).get());
        basketRepository.save(basketEntity2);

    }

    @Test
    public void findTest(){
        Optional<Basket> findBasket = basketRepository.findById(11L);

        if(findBasket.isPresent()){
            Assertions.assertThat(findBasket.get().getMember().getMemberName().equals("홍수현"));

            log.info("ProductName : " + findBasket.get().getProduct().getProductName());
        }
    }

//    @Test
//    public void queryUpdateTest(){
//        jpaQueryFactory.selectFrom(basket)
//                .where(basket.basketId.eq(8L))
//                .orderBy(basket.basketId.desc())
//                .limit(1)
//                .fetchOne()
//                .update(
//                        jpaQueryFactory.selectFrom(member).limit(1).fetchOne(),
//                        jpaQueryFactory.selectFrom(product).limit(1).fetchOne());
//    }

    @Test
    public void queryDeleteTest(){
        jpaQueryFactory.delete(basket)
                .where(basket.basketId.eq(12L))
                .execute();
    }
}
