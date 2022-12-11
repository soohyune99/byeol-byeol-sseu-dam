package com.app.byeolbyeolsseudam.service.mypage;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.order.Order;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
import com.app.byeolbyeolsseudam.repository.comment.CommentRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mybadge.MybadgeRepository;
import com.app.byeolbyeolsseudam.repository.mypoint.MypointRepository;
import com.app.byeolbyeolsseudam.repository.myprogram.MyprogramRepository;
import com.app.byeolbyeolsseudam.repository.order.OrderRepository;
import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
    private final MypointRepository mypointRepository;
    private final BadgeRepository badgeRepository;
    private final MybadgeRepository mybadgeRepository;
    private final MyprogramRepository myprogramRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final PickupRepository pickupRepository;

    @Override
    public List<MybadgeDTO> getMybadgesList(){
        return mybadgeRepository.selectMybadges();
    }

    @Override
    public List<BadgeDTO> getBadgeList(){
        return badgeRepository.showBadgeList();
    }

    @Override
    public List<MyprogramDTO> getMyprogramList(Long memberId, int page){
        return myprogramRepository.selectMyprogramList(memberId, page);
    }

    @Override
    public List<MypointDTO> getMypointList(Long memberId, int page){
        return mypointRepository.showMypointList(memberId, page);
    }

    @Override
    public List<BoardDTO> getMyCommunityList(Long memberId, int page){
        return boardRepository.selectBoardsofMypage(memberId, page);
    }

    @Override
    public List<CommentDTO> getMyCommentList(Long memberId, int page){
        return commentRepository.selectMyCommentList(memberId, page);
    }

    @Override
    public MemberDTO getMyInfo(Long memberId){
        return memberRepository.selectMember(memberId);
    }

    @Override
    public Boolean checkPassword(Long memberId, String password){
        String memberPassword = memberRepository.findById(memberId).get().getMemberPassword();
        memberPassword = "\"" + memberPassword + "\"";
        return memberPassword.equals(password);
    }

    @Override
    public MemberDTO updateMyInfo(MemberDTO memberDTO){
        Member member = memberRepository.findById(memberDTO.getMemberId()).get();
        if(memberDTO.getMemberPhone() == "" && member.getMemberPhone() != null){
            memberDTO.setMemberPhone(member.getMemberPhone());
        }
        member.update(memberDTO);
        memberRepository.save(member);
        return memberRepository.selectMember(member.getMemberId());
    }

    @Override
    public List<OrderDTO> getMyOrderList(Long memberId, int page){
        return orderRepository.selectMyOrderList(memberId, page);
    }

    @Override
    public List<OrderDTO> getMyCancelList(Long memberId, int page){
        return orderRepository.selectMyCancelList(memberId, page);
    }

    @Override
    public OrderDTO getMyOrder(Long orderId){
        return orderRepository.selectMyOrder(orderId);
    }

    @Override
    public Long cancelMyOrder(Long orderId){
        Order order = orderRepository.findById(orderId).get();
        order.updateStatus(OrderStatus.주문취소);
        orderRepository.save(order);
        return orderId;
    }

    @Override
    public void dropOutMember(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        member.updateMemberCategory(MemberCategory.탈퇴회원);
        memberRepository.save(member);
    }

    @Override
    public List<PickupDTO> getMyPickupList(Long memberId, int page){
        return pickupRepository.getMyPickupList(memberId, page);
    }

    @Override
    public PickupDTO getMyPickup(Long pickupId){
        return pickupRepository.getMyPickup(pickupId);
    }
}
