package com.app.byeolbyeolsseudam.repository.admin.orderdetail;

import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminOrderDetailCustomRepository {
    public List<OrderDetailDTO> showOrderDetailList(Pageable pageable);
}
