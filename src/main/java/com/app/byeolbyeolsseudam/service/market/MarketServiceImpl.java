package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final ProductRepository productRepository;

    // 상품 전체 조회
    @Override
    public List<ProductDTO> showList(){
        return productRepository.showAll();
    }


    // 생활 카테고리 조회
    @Override
    public List<ProductDTO> showLivingList(){return productRepository.showLiving();}

    // 주방 카테고리 조회
    @Override
    public List<ProductDTO> showKitchenList(){return productRepository.showKitchen();}

    // 욕실 카테고리 조회
    @Override
    public List<ProductDTO> showBathroomList(){return productRepository.showBathroom();}

    // 식품 카테고리 조회
    @Override
    public List<ProductDTO> showFoodList(){return productRepository.showFood();}

    // 취미 카테고리 조회
    @Override
    public List<ProductDTO> showHobbyList(){return productRepository.showHobby();}

    // 문구 카테고리 조회
    @Override
    public List<ProductDTO> showOfficeList(){return productRepository.showOffice();}

    // 반려동물 카테고리 조회
    @Override
    public List<ProductDTO> showPetList(){return productRepository.showPet();}

    // 상품 상세 조회
    @Override
    public ProductDTO showListDetail(Long productId){
        return productRepository.showDetail(productId);
    }
}
