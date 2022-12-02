package com.app.byeolbyeolsseudam.service.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.repository.notice.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDTO> showListCategory(){
        return noticeRepository.showCategory();
    }

    @Override
    public NoticeDTO showListDetail(Long noticeId){
        return noticeRepository.showDetail(noticeId);
    }

    @Override
    public List<NoticeDTO> search(String keyword){
        return noticeRepository.findByTitleContaining(keyword);
    }



}
