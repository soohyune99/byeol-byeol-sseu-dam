package com.app.byeolbyeolsseudam.service.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import org.springframework.stereotype.Service;

@Service
public interface JoinService {
    //     회원 가입
    public void memberJoin(MemberDTO memberDTO);
    //         기사 가입
    public void crewJoin(MemberDTO memberDTO);
    //         이메일 중복검사
    public  boolean checkEmail(String memberEmail);
//       회원 전체 조회
//    public List<MemberDTO> findAll();
////     회원 조회
//    public Member find(Long memberId);
////     회원 조회(Email)
//    public Member findByEmail(String memberEmail);
////     회원 수정
//    public void update(MemberDTO memberDTO);
////     회원 삭제
//    public void delete(Long memberNumber);
//

}

