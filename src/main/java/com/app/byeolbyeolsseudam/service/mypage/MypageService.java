package com.app.byeolbyeolsseudam.service.mypage;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MypageService {
    public List<BadgeDTO> getBadgeList();
    public List<MybadgeDTO> getMybadgesList(Long memberId);

    public List<MyprogramDTO> getMyprogramList(Long memberId, int page);
    public List<MypointDTO> getMypointList(Long memberId, int page);
    public List<BoardDTO> getMyCommunityList(Long memberId, int page);
    public List<CommentDTO> getMyCommentList(Long memberId, int page);

    public MemberDTO getMyInfo(Long memberId);
    public Boolean checkPassword(Long memberId, String password);
    public MemberDTO updateMyInfo(MemberDTO memberDTO);
    public String sendVerificationNumber(String userPhoneNumber);
    public void dropOutMember(Long memberId);

    public List<OrderDTO> getMyOrderList(Long memberId, int page);
    public List<OrderDTO> getMyCancelList(Long memberId, int page);
    public OrderDTO getMyOrder(Long orderId);
    public Long cancelMyOrder(Long orderId);

    public List<CourseDTO> getCourseList(Long memberId);
    public List<MycourseDTO> getMyCourseList(Long memberId);

    public List<PickupDTO> getMyPickupList(Long memberId, int page);
    public PickupDTO getMyPickup(Long pickupId);
}
