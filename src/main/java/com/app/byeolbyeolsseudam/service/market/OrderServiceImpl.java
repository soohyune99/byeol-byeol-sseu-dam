package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.Payment;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.entity.basket.Basket;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.order.Order;
import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.repository.basket.BasketRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.order.OrderRepository;
import com.app.byeolbyeolsseudam.repository.orderdetail.OrderDetailRepository;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

//    private final OrderDetailRepository orderDetailRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final BasketRepository basketRepository;

    @Override
    public ProductDTO getOrderDetailList(Long productId){
        return productRepository.selectProduct(productId);
    }

    @Override
    public List<ProductDTO> getOrderDetail(Long productId){
        return productRepository.selectProductAll(productId);
    }

    @Override
    public MemberDTO getOrderMember(Long memberId){
        return memberRepository.selectMember(memberId);
    }

    @Override
    public Long order(Payment payment){
        log.info("=========aaaaa=========" + payment);
        OrderDTO orderDTO = new OrderDTO();
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        Member member = memberRepository.findById(payment.getMemberId()).get();
        orderDTO.setOrderAddress(payment.getZipcode()+ payment.getRoadAdd() + payment.getDetailAdd() + payment.getWriteAdd());
        orderDTO.setOrderMessage(payment.getMessage());
        orderDTO.setOrderStatus(OrderStatus.주문완료);

        orderDetailDTO.setOrderDetailCount(payment.getBuyCount());

        Order orders = orderDTO.toEntity();
        orders.changeMember(member);
        orderRepository.save(orders);

        OrderDetail orderDetail = orderDetailDTO.toEntity();
        orderDetail.changeOrder(orders);

        if(productRepository.findById(payment.getProductId()).get() != null){
            orderDetail.changeProduct(productRepository.findById(payment.getProductId()).get());
            orderDetailRepository.save(orderDetail);
        }else{
            Basket basket = basketRepository.findById(Long.valueOf(payment.getBasketId())).get();
            log.info("1111111111111111111111" + basket);
            orderDetail.changeProduct(basket.getProduct());
            log.info(",,,,,,,,,,,,,,,,,,," + basket.getProduct());
            log.info("22222222222222222" + orderDetail);
            orderDetailRepository.save(orderDetail);
        }
//        Product product = productRepository.findById(payment.getProductId()).get();
//        Basket basket = basketRepository.findById(payment.getBasketId()).get();

        return orders.getOrderId();

    }

    @Override
    public OrderDTO getOrderList(Long memberId){
        return orderRepository.showReceipt(memberId);
    }



}
