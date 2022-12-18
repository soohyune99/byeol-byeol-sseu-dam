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
    /* 나의 배지 */
    public List<BadgeDTO> getBadgeList();
    public List<MybadgeDTO> getMybadgesList(Long memberId);

    /* 수강내역 */
    public List<MyprogramDTO> getMyprogramList(Long memberId, int page);
    public int getCountMyprogram(Long memberId);

    /* 포인트내역 */
    public List<MypointDTO> getMypointList(Long memberId, int page);

    /* 커뮤니티 */
    public List<BoardDTO> getMyCommunityList(Long memberId, int page);
    public List<CommentDTO> getMyCommentList(Long memberId, int page);

    /* 회원정보 조회, 정보 수정, 회원 탈퇴 */
    public MemberDTO getMyInfo(Long memberId);
    public Boolean checkPassword(Long memberId, String password);
    public MemberDTO updateMyInfo(MemberDTO memberDTO);
    public String sendVerificationNumber(String userPhoneNumber);
    public void dropOutMember(Long memberId);

    /* 주문내역 및 상세 */
    public List<OrderDTO> getMyOrderList(Long memberId, int page);
    public List<OrderDTO> getMyCancelList(Long memberId, int page);
    public OrderDTO getMyOrder(Long orderId);
    public Long cancelMyOrder(Long orderId);
    public int getCountMyorder(Long memberId);
    public int getCountMycancel(Long memberId);

    /* 나의 코스 */
    public CourseDTO getCourse(Long courseId);
    public List<CourseDTO> getCourseList(Long memberId);
    public List<MycourseDTO> getMyCourseList(Long memberId);

    /* 수거내역 */
    public List<PickupDTO> getMyPickupList(Long memberId, int page);
    public PickupDTO getMyPickup(Long pickupId);
    public int getCountMypickup(Long memberId);
}
