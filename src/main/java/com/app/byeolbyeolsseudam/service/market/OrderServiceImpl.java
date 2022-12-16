package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.Payment;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.order.Order;
import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.order.OrderRepository;
import com.app.byeolbyeolsseudam.repository.orderdetail.OrderDetailRepository;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final OrderDetailRepository orderDetailRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

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
        OrderDTO orderDTO = new OrderDTO();
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        Member member = memberRepository.findById(payment.getMemberId()).get();
        Product product = productRepository.findById(payment.getProductId()).get();

        orderDTO.setOrderAddress(payment.getZipcode()+ payment.getRoadAdd() + payment.getDetailAdd() + payment.getWriteAdd());
        orderDTO.setOrderMessage(payment.getMessage());
        orderDTO.setOrderStatus(OrderStatus.주문완료);

        orderDetailDTO.setOrderDetailCount(payment.getBuyCount());

        Order order = orderDTO.toEntity();
        order.changeMember(member);
        orderRepository.save(order);

        OrderDetail orderDetail = orderDetailDTO.toEntity();
        orderDetail.changeOrder(order);
        orderDetail.changeProduct(product);
        orderDetailRepository.save(orderDetail);

        return order.getOrderId();

    }

    @Override
    public OrderDTO getOrderList(Long memberId){
        return orderRepository.showReceipt(memberId);
    }



}
