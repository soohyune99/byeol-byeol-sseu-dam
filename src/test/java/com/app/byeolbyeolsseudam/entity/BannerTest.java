//package com.app.byeolbyeolsseudam.entity;
//
//import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
//import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
//import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
//import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
//import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
//import com.app.byeolbyeolsseudam.entity.banner.Banner;
//import com.app.byeolbyeolsseudam.entity.notice.Notice;
//import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
//import com.app.byeolbyeolsseudam.repository.admin.program.AdminProgramRepository;
//import com.app.byeolbyeolsseudam.repository.banner.BannerRepository;
//import com.app.byeolbyeolsseudam.repository.banner.BannerRepositoryImpl;
//import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
//import com.app.byeolbyeolsseudam.repository.notice.NoticeRepository;
//import com.app.byeolbyeolsseudam.repository.order.OrderRepository;
//import com.app.byeolbyeolsseudam.repository.orderdetail.OrderDetailRepository;
//import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
//import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
//import com.app.byeolbyeolsseudam.service.main.BannerService;
//import com.app.byeolbyeolsseudam.type.MemberCategory;
//import com.app.byeolbyeolsseudam.type.MemberLoginType;
//import com.app.byeolbyeolsseudam.type.NoticeCategory;
//import com.app.byeolbyeolsseudam.type.PickupStatus;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.servlet.ServletContext;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static com.app.byeolbyeolsseudam.entity.notice.QNotice.notice;
//
//
//@SpringBootTest
//@Slf4j
//@Transactional
//@Rollback(false)
//public class BannerTest{
//
//
////    @Test
////    public void pathTest(){
////
////        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
////        log.info("경로1: " + path);
////    }
//
//    @Autowired
//    private BannerRepository bannerRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private BannerService bannerService;
//
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//
//    @Autowired
//    private NoticeRepository noticeRepository;
//
//
//    @Autowired
//    private AdminProgramRepository adminProgramRepository;
//
//    @Test
//    public void adminProgramSaveTest(){
//
////        for (int i = 0; i < 53; i++) {
////            ProgramDTO programDTO = new ProgramDTO();
////
////            programDTO.setProgramName("자바 기초 강의");
////            programDTO.setProgramPlace("한국IT 4층 C강의장");
////            programDTO.setOpeningDate(LocalDateTime.of(2022,12,12,12,0));
////            programDTO.setClosingDate();
////            programDTO.setProgramTime();
////            programDTO.setProgramDate();
////            programDTO.setProgramContent();
////            programDTO.setProgramLimitCount();
////            programDTO.setProgramStatus();
////            programDTO.setProgramFileProfileName();
////            programDTO.setProgramFileProfilePath();
////            programDTO.setProgramFileProfileUuid();
////            programDTO.setProgramFileDetailName();
////            programDTO.setProgramFileDetailPath();
////            programDTO.setProgramFileDetailUuid();
////            programDTO.setCreatedDate();
////
////            adminProgramRepository.save(programDTO.toEntity());
////        }
//    }
//
//
////    @Test
////    public void saveTest(){
////
////        for (int i = 0; i < 7; i++){
////            BannerDTO bannerDTO = new BannerDTO();
////
////            UUID uuid = UUID.randomUUID();
////            String filName = uuid.toString() + "banner" + (i+1) + ".png";
////
////            bannerDTO.setBannerUuid(uuid.toString());
////            bannerDTO.setBannerPath("C://upload");
////            bannerDTO.setBannerName(filName);
////            bannerRepository.save(bannerDTO.toEntity());
////        }
////    }
//
////    @Test
////    public void findTest(){
////        Optional<Banner> banner = bannerRepository.findById(1L);
////
////        if(banner.isPresent()){
////            log.info("bannerName: " + banner.get().getBannerName());
////        }
////    }
//
////    @Test
////    public void findAllTest(){
//////        List<Banner> bannerList = jpaQueryFactory.selectFrom(QBanner.banner)
//////                .orderBy(QBanner.banner.bannerId.desc())
//////                .offset(0)
//////                .limit(3)
//////                .fetch();
////
////        List<Banner> banners = bannerRepository.findAll(Sort.by(Sort.Direction.DESC, "bannerId"));
////        banners.stream().map(Banner::getBannerName).forEach(log::info);
////    }
//
////    @Test
////    public void updateTest(){
////        UUID uuid = UUID.randomUUID();
////        BannerDTO bannerDTO = new BannerDTO();
////
////        bannerDTO.setBannerUuid(uuid.toString());
////        bannerDTO.setBannerName(uuid.toString() + "end.png");
////        bannerDTO.setBannerPath("/images/main/slide5.png");
////
////        bannerRepository.findAll().get(6).update(bannerDTO);
////    }
//
//
////    @Test
////    public void deleteTest(){
////        bannerRepository.deleteAll();
////    }
//
//
//
////    @Test
////    public void memberSaveTest(){
////        for(int i = 0; i < 4; i++){
////            MemberDTO memberDTO = new MemberDTO();
////
////            memberDTO.setMemberLoginType(MemberLoginType.네이버);
////            memberDTO.setMemberCategory(MemberCategory.일반회원);
////            memberDTO.setMemberName("한동석");
////            memberDTO.setMemberEmail("tedhan@gmail.com");
////            memberDTO.setMemberPassword("1");
////            memberDTO.setMemberPhone("01077777777");
////
////            memberRepository.save(memberDTO.toEntity());
////        }
////    }
//
////    @Test
////    public void orderDetailSaveTest(){
////        OrderDetailDTO orderDetail1 = new OrderDetailDTO();
////        OrderDetailDTO orderDetail2 = new OrderDetailDTO();
////        OrderDetailDTO orderDetail3 = new OrderDetailDTO();
////
////        orderDetail1.setOrderDetailCount(5);
////        com.app.byeolbyeolsseudam.entity.OrderDetail orderDetailEntity = orderDetail1.toEntity();
////        orderDetailEntity.changeOrder(orderRepository.findById(6L).get());
////        orderDetailEntity.changeProduct(productRepository.findById(3L).get());
////        orderDetailRepository.save(orderDetailEntity);
////
////        orderDetail2.setOrderDetailCount(1);
////        com.app.byeolbyeolsseudam.entity.OrderDetail orderDetailEntity2 = orderDetail2.toEntity();
////        orderDetailEntity2.changeOrder(orderRepository.findById(6L).get());
////        orderDetailEntity2.changeProduct(productRepository.findById(5L).get());
////        orderDetailRepository.save(orderDetailEntity2);
////
////        orderDetail3.setOrderDetailCount(2);
////        com.app.byeolbyeolsseudam.entity.OrderDetail orderDetailEntity3 = orderDetail3.toEntity();
////        orderDetailEntity3.changeOrder(orderRepository.findById(6L).get());
////        orderDetailEntity3.changeProduct(productRepository.findById(4L).get());
////        orderDetailRepository.save(orderDetailEntity3);
////    }
//
////    @Test
////    public void orderFindTest(){
////        List<Order> orders = orderRepository.findAll();
////
////        for (int i = 0; i < orders.size(); i++) {
////            log.info(
////                    "orderId: " + orders.get(i).getOrderId()
////                            + "orderCreatedDate: " + orders.get(i).getCreatedDate()
////                            + "memberId: " + orders.get(i).getMember().getMemberName()
////                            + "orderStatus" + orders.get(i).getOrderStatus()
////            );
////        }
////    }
////
////    @Test
////    public void orderDetailFindTest(){
////        Optional<Order> order = orderRepository.findById(6L);
////
////
////        log.info("orderId: " + order.get().getOrderId()
////                + "orderCreatedDate: " + order.get().getCreatedDate()
////                + "memberId: " + order.get().getMember().getMemberName()
////                + "orderStatus: " + order.get().getOrderStatus()
////                + "orderStatus" + order.get().getOrderStatus()
////                + "orderMessage: " + order.get().getOrderMessage()
////        );
////
////        List<com.app.byeolbyeolsseudam.entity.OrderDetail> orderDetails = jpaQueryFactory.selectFrom(orderDetail)
////                .where(orderDetail.order.orderId.eq(6L))
////                .fetch();
////
////        for (int i = 0; i < 3; i++){
////
////            log.info(
////                    "productCategory: " + orderDetails.get(i).getProduct().getProductCategory()
////                    + "productName" + orderDetails.get(i).getProduct().getProductName()
////                    + "orderDetailCount" + orderDetails.get(i).getOrderDetailCount()
////                    + "productPrice" + orderDetails.get(i).getProduct().getProductPrice()
////            );
////
////        }
////    }
//
////    @Test
////    public void BannerServiceTest(){
////        List<BannerDTO> bannerDTOList = bannerService.show();
////
////        for (int i = 0; i < bannerDTOList.size(); i++) {
////            log.info("bannerName: " + bannerDTOList.get(i).getBannerName() + "bannerPath: " + bannerDTOList.get(i).getBannerPath() + "bannerUUID: " + bannerDTOList.get(i).getBannerUuid());
////        }
////    }
//
//    @Autowired
//    private PickupRepository pickupRepository;
//
//    @Test
//    public void NoticeSaveTest(){
//        PickupDTO pickupDTO = new PickupDTO();
//        pickupDTO.setPetCount(5);
//        pickupDTO.setGlassCount(10);
//        pickupDTO.setPickupAddress("서울특별시 역삼역");
//        pickupDTO.setPickupMessage("오후12시 이후 방문");
//        pickupDTO.setPickupStatus(PickupStatus.수거대기중);
//        pickupDTO.setMemberId(224L);
//
//        Pickup pickup = pickupDTO.toEntity();
//        pickup.changeMember(memberRepository.findById(pickupDTO.getMemberId()).get());
//        pickupRepository.save(pickup);
//    }
//
//}
