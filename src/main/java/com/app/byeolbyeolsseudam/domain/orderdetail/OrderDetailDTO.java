package com.app.byeolbyeolsseudam.domain.orderdetail;

import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private Long orderDetailId;
    private int orderDetailCount;
    private Long orderId;
    private Long productId;
    private ProductCategory productCategory;
    private String productName;
    private int productPrice;
    private String productFileProfileName;

    @QueryProjection
    public OrderDetailDTO(Long orderDetailId, int orderDetailCount, Long orderId, Long productId, ProductCategory productCategory, String productName, int productPrice, String productFileProfileName) {
        this.orderDetailId = orderDetailId;
        this.orderDetailCount = orderDetailCount;
        this.orderId = orderId;
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productFileProfileName = productFileProfileName;
    }

    public OrderDetail toEntity(){
        return OrderDetail.builder()
                .orderDetailCount(orderDetailCount)
                .build();
    }
}
