package com.app.byeolbyeolsseudam.repository.product;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.type.ProductCategory;

import java.util.List;

public interface ProductCustomRepository {

    // 마켓 전체 조회
    public List<ProductDTO> showAll();

    // 생활 카테고리 조회
    public List<ProductDTO> showLiving();
    // 주방 카테고리 조회
    public List<ProductDTO> showKitchen();
    // 욕실 카테고리 조회
    public List<ProductDTO> showBathroom();
    // 식품 카테고리 조회
    public List<ProductDTO> showFood();
    // 취미 카테고리 조회
    public List<ProductDTO> showHobby();
    // 문구 카테고리 조회
    public List<ProductDTO> showOffice();
    // 반려동물 카테고리 조회
    public List<ProductDTO> showPet();

    // 마켓 상세 조회
    public ProductDTO showDetail(Long productId);

}
