package com.app.byeolbyeolsseudam.repository.mypoint;

import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.QMypointDTO;
import com.app.byeolbyeolsseudam.entity.mypoint.QMypoint;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.mypoint.QMypoint.mypoint;

@Repository
@RequiredArgsConstructor
public class MypointCustomRepositoryImpl implements MypointCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MypointDTO> selectPoints() {
        List<MypointDTO> mypoints = jpaQueryFactory.select(new QMypointDTO(
                mypoint.mypointId, mypoint.mypointContent,
                mypoint.mypointInout, mypoint.createdDate, mypoint.mypointId))
                .from(mypoint)
                .orderBy(mypoint.mypointId.desc())
                .fetch();

        return mypoints;
    }
}
