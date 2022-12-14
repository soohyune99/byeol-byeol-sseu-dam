package com.app.byeolbyeolsseudam.repository.admin.member;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminMemberCustomRepository {
    public List<MemberDTO> showAdminMember();
    public List<MemberDTO> showMemberList(Pageable pageable);
    public MemberDTO showAdminList();
}
