package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.mypoint.Mypoint;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.entity.order.Order;
import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.repository.admin.order.AdminOrderRepository;
import com.app.byeolbyeolsseudam.repository.admin.orderdetail.AdminOrderDetailRepository;
import com.app.byeolbyeolsseudam.repository.admin.product.AdminProductRepository;
import com.app.byeolbyeolsseudam.repository.admin.review.AdminReviewRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mypoint.MypointRepository;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminProductService {
    private final AdminProductRepository adminProductRepository;
    private final AdminOrderRepository adminOrderRepository;
    private final AdminReviewRepository adminReviewRepository;
    private final AdminOrderDetailRepository adminOrderDetailRepository;
    private final MypointRepository mypointRepository;
    private final MemberRepository memberRepository;

    public Page<ProductDTO> searchProduct(Pageable pageable){
       List<ProductDTO> products = adminProductRepository.searchProductPaging("",pageable);

       Page<ProductDTO> productList = new PageImpl<>(products,pageable,adminProductRepository.findAll().size());
       return productList;
    }

    public Page<ProductDTO> searchProductPaging(String keyword, Pageable pageable){

        List<ProductDTO> search = adminProductRepository.searchProduct(keyword);
        List<ProductDTO> products = adminProductRepository.searchProductPaging(keyword, pageable);

        Page<ProductDTO> productSearchList = new PageImpl<>(products,pageable,search.size());
        return productSearchList;

    }

    public void saveProduct(ProductDTO productDTO){
        adminProductRepository.save(productDTO.toEntity());
    }

    public void removeProduct(List<String> productIdstr){
        List<Long> productId = new ArrayList<>();
        productIdstr.stream().map(Long::parseLong).forEach(productId::add);
        productId.forEach(adminProductRepository::deleteById);
    }

    public ProductDTO selectById(Long productId){
        return adminProductRepository.selectById(productId);
    }

    public void updateProduct(ProductDTO productDTO, Long productId){
        adminProductRepository.update(productDTO);
        Product product = adminProductRepository.findById(productId).get();
        adminProductRepository.save(product);
    }

    public OrderDTO showOrderDetail(Long orderId){
        return adminOrderRepository.showOrderDetail(orderId);
    }


    public List<OrderDTO> showAdminOrderList(){
        return adminOrderRepository.showAdminOrder();
    }

    public Page<OrderDTO> searchOrder(Pageable pageable){
        List<OrderDTO> orders = adminOrderRepository.showOrderList(pageable);
        Page<OrderDTO> orderList = new PageImpl<>(orders, pageable, adminOrderRepository.findAll().size());
        return orderList;
    }

    public void updateOrder(OrderDTO orderDTO, Long orderId){
//      제품가격과 주문 수량 들어있는 orderDetail
        List<OrderDetailDTO> orderDetail = adminOrderDetailRepository.showDetail(orderId);
//      수량 곱하기 가격 해서 모아놓은 리스트
        List<Integer> orderPrices = orderDetail.stream().map(orderDetailDTO -> orderDetailDTO.getOrderDetailCount()*orderDetailDTO.getProductPrice()).collect(Collectors.toList());
        int totalPrice = 0;
//      총 가격의 10%를 포인트로 지급
        for (int i = 0; i < orderPrices.size(); i++){
            totalPrice += orderPrices.get(i)*0.01;
        }
//      adminOrderRepository.update(orderDTO);
        Order order = adminOrderRepository.findById(orderId).get();
        order.updateStatus(orderDTO.getOrderStatus());

        adminOrderRepository.save(order);

        if(orderDTO.getOrderStatus().equals(OrderStatus.배송완료)){
//      주문 당사자
        Member member = order.getMember();

        MypointDTO mypointDTO = new MypointDTO();
        mypointDTO.setMypointInout(totalPrice);
        mypointDTO.setMypointContent("결제 포인트 지급");

        Mypoint mypoint= mypointDTO.toEntity();
        mypoint.changeMember(member);
        mypointRepository.save(mypoint);

        MemberDTO memberDTO = memberRepository.selectMember(order.getMember().getMemberId());
        memberDTO.setMemberPoint(memberDTO.getMemberPoint()+ totalPrice);
        member.updateMemberPoint(memberDTO);

        memberRepository.save(member);
        }
    }

    public void removeOrder(List<String> orderIdstr){
        List<Long> orderId = new ArrayList<>();
        orderIdstr.stream().map(Long::parseLong).forEach(orderId::add);
        orderId.forEach(adminOrderRepository::deleteById);
    }

    public Page<ReviewDTO> showReviewList(Pageable pageable){
        List<ReviewDTO> reviews = adminReviewRepository.showReviewList(pageable);
        Page<ReviewDTO> reviewList = new PageImpl<>(reviews, pageable, adminReviewRepository.findAll().size());
        return reviewList;
    }

    public void removeReview(List<String> reviewIdstr){
        List<Long> reviewId = new ArrayList<>();
        reviewIdstr.stream().map(Long::parseLong).forEach(reviewId::add);
        reviewId.forEach(adminReviewRepository::deleteById);
    }

}
