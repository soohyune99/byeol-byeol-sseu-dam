package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.MemberDTO;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
                MemberDTO memberDTO = new MemberDTO();

                memberDTO.setMemberLoginType(MemberLoginType.네이버);
                memberDTO.setMemberCategory(MemberCategory.일반회원);
                memberDTO.setMemberName("장선홍");
                memberDTO.setMemberEmail("jsh5060@dreamwiz.com");
                memberDTO.setMemberPassword("1234");
                memberDTO.setMemberAddress("서울강동");
                memberDTO.setMemberPhone("01012345678");

                memberRepository.save(memberDTO.toEntity());
            }

     @Test
        public void findTest(){
         assertThat(memberRepository.findByMemberEmail("jsh5060@dreamwiz.com").get(0).getMemberEmail()).isEqualTo("jsh5060@dreamwiz.com");
     }

     @Test
    public void updateTest(){
        Optional<Member> updateMember = memberRepository.findById(1L);

        if(updateMember.isPresent()){
            updateMember.get().update(updateMember.get().getMemberLoginType() ,MemberCategory.기사회원, "조혜인","1111","1234",
                    "jsh5060@dreamwiz.com", 2,
                    updateMember.get().getMemberProfileName(), updateMember.get().getMemberProfilePath(),
                    updateMember.get().getMemberProfileUuid());
        }


     }


     @Test
    public void deleteTest(){
        Optional<Member> deleteMember = memberRepository.findById(7L);

        if(deleteMember.isPresent()){
            memberRepository.delete(deleteMember.get());
        }

     }



}
