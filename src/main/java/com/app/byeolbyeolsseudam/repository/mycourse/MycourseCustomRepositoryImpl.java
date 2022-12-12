package com.app.byeolbyeolsseudam.repository.mycourse;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.QMycourseDTO;
import com.app.byeolbyeolsseudam.entity.course.QCourse;
import com.app.byeolbyeolsseudam.entity.mycourse.QMycourse;
import com.app.byeolbyeolsseudam.entity.spot.QSpot;
import com.app.byeolbyeolsseudam.repository.mybadge.MybadgeCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.course.QCourse.course;
import static com.app.byeolbyeolsseudam.entity.mycourse.QMycourse.mycourse;
import static com.app.byeolbyeolsseudam.entity.spot.QSpot.spot;

@Repository
@RequiredArgsConstructor
public class MycourseCustomRepositoryImpl implements MycourseCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MycourseDTO> selectMyCourseList(Long memberId){
        return jpaQueryFactory.select(new QMycourseDTO(mycourse.mycourseId,
                mycourse.courseFinishedStatus, mycourse.member.memberId, mycourse.course.courseId,
                mycourse.course.courseName, mycourse.spot.spotId, mycourse.spot.spotName,
                mycourse.spot.spotNumber))
                .from(mycourse)
                .where(mycourse.member.memberId.eq(memberId))
                .orderBy(mycourse.course.courseName.asc())
                .orderBy(mycourse.spot.spotName.asc())
                .fetch();
    }
}
