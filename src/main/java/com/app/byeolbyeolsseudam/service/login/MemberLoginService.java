package com.app.byeolbyeolsseudam.service.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.ArrayList;

@Service
@AllArgsConstructor
@Slf4j
public class MemberLoginService {
   private final MemberRepository memberRepository;

    JavaMailSender mailSender;


    public Member Get(MemberDTO memberDTO){

        return memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

    }

    public boolean login(MemberDTO memberDTO){


        Member finderMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

//        Member finderMember = memberLoginService.Get(memberDTO);

        if(finderMember ==  null){

            return false;

        }

        if(!finderMember.getMemberPassword().equals(memberDTO.getMemberPassword())){

            return false;
        }

        return true;

    }


    //임시 비밀번호로 업데이트
    public void UpdatePassword(MemberDTO memberDTO, String tempPassword){

       Member member = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
       member.setMemberPassword(tempPassword);
       memberRepository.save(member);

    }

//  임시비밀번호 얻기

    public String GetTempPassword(){

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};

        String temp = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성
        int index1 = 0;
        for (int i = 0; i < 10; i++) {
            index1 = (int) (charSet.length * Math.random());
            temp += charSet[index1];
        }
        return temp;

    }


    public void Send(MemberDTO memberDTO){

        SimpleMailMessage message = new SimpleMailMessage();

        String tempPassword = GetTempPassword();

        UpdatePassword(memberDTO, tempPassword);


        message.setFrom("jsh5060@dreamwiz.com");
        message.setTo(memberDTO.getMemberEmail());
        message.setSubject("별별쓰담 임시비밀번호 안내 이메일 입니다");
        message.setText("안녕하세요. 별별쓰담 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                + tempPassword + " 입니다." + "로그인 후에 비밀번호를 변경을 해주세요");


        mailSender.send(message);


    }


}
