package com.app.byeolbyeolsseudam.repository.admin.member;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;

import java.util.List;

public interface AdminMemberCustomRepository {
    public List<MemberDTO> showMemberList();
}
