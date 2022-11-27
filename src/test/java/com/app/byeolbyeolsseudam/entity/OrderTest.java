package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.MemberDTO;
import com.app.byeolbyeolsseudam.domain.OrderDTO;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.OrderRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        OrderDTO order1 = new OrderDTO();
        MemberDTO member1 = new MemberDTO();
        OrderDTO order2 = new OrderDTO();
        MemberDTO member2 = new MemberDTO();

        member1.setMemberLoginType(MemberLoginType.네이버);
        member1.setMemberCategory(MemberCategory.일반회원);
        member1.setMemberName("은지");
        member1.setMemberEmail("sej07@naver.com");
        member1.setMemberPassword("password486!");
        member1.setMemberAddress("역삼역 3번 출구");
        member1.setMemberPhone("01012345678");
        member1.setMemberPoint(3000);
        member1.setMemberProfileFile("noimage.png");

        memberRepository.save(member1.toEntity());

        order1.setOrderStatus(OrderStatus.배송대기중);
        order1.setMember(memberRepository.findAll().get(0));

        orderRepository.save(order1.toEntity());

        member2.setMemberLoginType(MemberLoginType.카카오);
        member2.setMemberCategory(MemberCategory.일반회원);
        member2.setMemberName("홍수현");
        member2.setMemberEmail("soohyune1211@google.com");
        member2.setMemberPassword("password1234!");
        member2.setMemberAddress("서울대입구");
        member2.setMemberPhone("01012341234");
        member2.setMemberPoint(3000);
        member2.setMemberProfileFile("noimage.png");

        memberRepository.save(member2.toEntity());

        order2.setOrderStatus(OrderStatus.배송중);
        order2.setMember(memberRepository.findAll().get(1));

        orderRepository.save(order2.toEntity());
    }

    @Test
    public void findTest(){
        Optional<Order> findOrder = orderRepository.findById(2L);

        if(findOrder.isPresent()){
            Assertions.assertThat(findOrder.get().getMember().getMemberName().equals("은지"));

            log.info("OrderStatus : " + findOrder.get().getOrderStatus());
        }
    }

    @Test
    public void updateTest(){
        Optional<Order> updateOrder = orderRepository.findById(2L);

        if(updateOrder.isPresent()){
            updateOrder.get().update(OrderStatus.배송대기중);
        }
    }

    @Test
    public void deleteTest(){
        Optional<Order> deleteOrder = orderRepository.findById(2L);

        if(deleteOrder.isPresent()){
            orderRepository.delete(deleteOrder.get());
        }
    }
}
