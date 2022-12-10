package com.app.byeolbyeolsseudam.service.mypage;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
import com.app.byeolbyeolsseudam.repository.comment.CommentRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mybadge.MybadgeRepository;
import com.app.byeolbyeolsseudam.repository.mypoint.MypointRepository;
import com.app.byeolbyeolsseudam.repository.myprogram.MyprogramRepository;
import com.app.byeolbyeolsseudam.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
