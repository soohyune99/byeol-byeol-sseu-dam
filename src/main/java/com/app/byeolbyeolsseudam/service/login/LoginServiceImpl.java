package com.app.byeolbyeolsseudam.service.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{
    private final MemberRepository memberRepository;
    private final JavaMailSender mailSender;

    @Override
    public Member login(MemberDTO memberDTO){
        if(Optional.ofNullable(memberRepository.findByMemberEmail(memberDTO.getMemberEmail())).isPresent()){
            Member member = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
            if(memberDTO.getMemberPassword().equals(
                    new String(Base64.decode(member.getMemberPassword())))){
                return member;
            }
        }
        return null;
    }

    @Override
    public Member loginOauth(MemberDTO memberDTO){
        if(Optional.ofNullable(memberRepository.findOauth(memberDTO)).isPresent()){
            return memberRepository.findOauth(memberDTO);
        }else {
            return null;
        }
    }

    @Override
    public MemberDTO getMemberDTO(Long memberId){
        return memberRepository.selectMember(memberId);
    }

    /* 임시 비밀번호로 업데이트 */
    public Member UpdatePassword(MemberDTO memberDTO, String tempPassword){
        if(Optional.ofNullable(memberRepository.findByMemberEmail(memberDTO.getMemberEmail())).isPresent()){
            Member member = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
            if(member.getMemberCategory() == MemberCategory.탈퇴회원){
                return null;
            }
            memberDTO.setMemberPassword(Base64.encode(tempPassword.getBytes()));
            member.updateMemberPassword(memberDTO);
            memberRepository.save(member);
            return member;
        }
        return null;
    }

    /* 임시 비밀번호 생성 */
    public String GetTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
        String temp = "";
        int index1 = 0;

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성
        for (int i = 0; i < 10; i++) {
            index1 = (int) (charSet.length * Math.random());
            temp += charSet[index1];
        }
        return temp;
    }


    /* 이메일 전송 */
    @Override
    public Boolean send(MemberDTO memberDTO){
        SimpleMailMessage message = new SimpleMailMessage();
        String tempPassword = GetTempPassword();

        if(Optional.ofNullable(UpdatePassword(memberDTO, tempPassword)).isEmpty()){
            return false;
        }
        Member member = UpdatePassword(memberDTO, tempPassword);

        message.setFrom("iloveEarth@byeolbyeol.com");
        message.setTo(member.getMemberEmail());
        message.setSubject("[별별쓰담] 임시비밀번호 안내 이메일 입니다");
        message.setText("안녕하세요. " + member.getMemberName() + "님\n"
                + "별별쓰담 임시비밀번호 안내 관련 이메일 입니다.\n"
                + member.getMemberName() + "님의 임시 비밀번호는 "
                + tempPassword + " 입니다.\n로그인 후에 비밀번호 변경 부탁드립니다.");

        mailSender.send(message);
        return true;
    }
}
