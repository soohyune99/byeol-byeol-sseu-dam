package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarketService {

    // 상품 전체 조회
    public List<ProductDTO> showList();

    // 생활 카테고리 조회
    public List<ProductDTO> showLivingList();
    // 주방 카테고리 조회
    public List<ProductDTO> showKitchenList();
    // 욕실 카테고리 조회
    public List<ProductDTO> showBathroomList();
    // 식품 카테고리 조회
    public List<ProductDTO> showFoodList();
    // 취미 카테고리 조회
    public List<ProductDTO> showHobbyList();
    // 문구 카테고리 조회
    public List<ProductDTO> showOfficeList();
    // 반려동물 카테고리 조회
    public List<ProductDTO> showPetList();

    // 상품 상세 조회
    public ProductDTO showListDetail(Long productId);
}
