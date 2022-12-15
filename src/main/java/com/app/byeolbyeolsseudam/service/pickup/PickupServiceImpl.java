package com.app.byeolbyeolsseudam.service.pickup;

import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.mypoint.Mypoint;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mypoint.MypointRepository;
import com.app.byeolbyeolsseudam.repository.pickup.PickupCustomRepository;
import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptCustomRepository;
import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptRepository;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class PickupServiceImpl implements PickupService{
    private final PickupAcceptCustomRepository pickupAcceptCustomRepository;
    private final PickupAcceptRepository pickupAcceptRepository;
    private final PickupRepository pickupRepository;
    private final MypointRepository mypointRepository;
    private final MemberRepository memberRepository;

    /* 수거 대기중 리스트 / 수거 신청페이지 _ 해당되는 지역에 따라 동적쿼리 사용*/
    /* 동적 쿼리 - 수거대기중인 상태에서 소재지 키워드 Contain 여부 확인 */
    @Override
    public Page<PickupAcceptDTO> findListPickupStatusSojaeji(String searchSojaeji, Pageable pageable) {
        return pickupAcceptCustomRepository.findListPickupStatusSojaeji(searchSojaeji, pageable);
    }

    /*-----------------------------------------------------------------------------------------------*/

    /* 수거중 리스트 _ 수거현황 페이지 _ 기사님이 수락한 리스트 */
    @Override
    public List<PickupAcceptDTO> findListPickupStatusIng(Long memberId) {
        return pickupAcceptCustomRepository.findListPickupStatusIng(memberId);
    }
    /* 수거완료 리스트 _ 수거완료 페이지 _ 기사님이 수거 완료한 리스트 */
    @Override
    public List<PickupAcceptDTO> findListPickupStatusFinish(Long memberId) {
        return pickupAcceptCustomRepository.findListPickupStatusFinish(memberId);
    }

    /*-----------------------------------------------------------------------------------------------*/

    /* 수거 대기중,수거중,수거완료 _ 상세 페이지 이동 / 클릭시 디테일 이동( 픽업엡셋아이디로 조회 필요 신청하는 순간에는 기사님 멤버아이디(세션에있는것) 가져오기 */
    @Override
    public PickupAcceptDTO findPickupId(Long pickupAcceptId) {
        return pickupAcceptCustomRepository.findPickupId(pickupAcceptId);
    }

    /*-----------------------------------------------------------------------------------------------*/

    /* 수거대기중 상세페이지에서 _ 수락하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기 */
    @Override
    public Pickup updatePickupStatusIng(Long pickupId) {
        PickupDTO pickupDTO = new PickupDTO();
        pickupDTO.setPickupStatus(PickupStatus.수거중);
        Pickup pickup = pickupRepository.findById(pickupId).get();

        log.info(pickup.toString());

        pickup.update(pickupDTO);

        log.info(pickup.toString());

        return pickup;

    }
    /* 수거중 상세페이지에서 _ 수거완료하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기*/
    @Override
    public void updatePickupStatusFinish(Long pickupId) {
        PickupDTO pickupDTO = new PickupDTO();
        pickupDTO.setPickupStatus(PickupStatus.수거완료);
        Pickup pickup = pickupRepository.findById(pickupId).get();
        pickup.update(pickupDTO);

        /* 수거완료하기 버튼 누를 때 포인트 주기 - joinServiceImpl 참고 */
//        Member member = memberDTO.toEntity();
//        memberRepository.save(member);
//        MypointDTO mypointDTO = new MypointDTO();
//        mypointDTO.setMypointInout(1000);
//        mypointDTO.setMypointContent("쓰담수거 완료 포인트 지급");
//
//        Mypoint mypoint = mypointDTO.toEntity();
//        mypoint.changeMember(member);
//
//        mypointRepository.save(mypoint);

    }



    @Override
    public void savePickupAccept(PickupAccept pickupAccept) {
        pickupAcceptRepository.save(pickupAccept);
    }


}
