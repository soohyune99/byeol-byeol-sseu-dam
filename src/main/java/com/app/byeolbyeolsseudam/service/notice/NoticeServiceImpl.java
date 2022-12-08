package com.app.byeolbyeolsseudam.service.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.repository.notice.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    // 공지사항 일반 10개 조회
    @Override
    public Page<NoticeDTO> showListAll(Pageable pageable){

        List<NoticeDTO> notices = noticeRepository.showAll(pageable);
        final Page<NoticeDTO> noticePage = new PageImpl<>(notices, pageable, noticeRepository.findAll().size());

        return noticePage;
    }

    // 공지사항 중요5개, 일반 5개 조회
    @Override
    public Page<NoticeDTO> showListCategory(Pageable pageable){

        List<NoticeDTO> notice =  noticeRepository.showCategory(pageable);
        final Page<NoticeDTO> noticeCate = new PageImpl<>(notice, pageable, noticeRepository.findAll().size());

        return noticeCate;
    }

    // 공지사항 상세보기
    @Override
    public NoticeDTO showListDetail(Long noticeId){
        return noticeRepository.showDetail(noticeId);
    }

    //공지사항 검색
    @Override
    public Page<NoticeDTO> search(String keyword, Pageable pageable){
        log.info("================ : " + pageable.getPageSize());
        List<NoticeDTO> search = noticeRepository.searchList(keyword);
        List<NoticeDTO> result =  noticeRepository.searchNotice(keyword, pageable);
        final Page<NoticeDTO> searchList = new PageImpl<>(result, pageable, search.size());

        return searchList;
    }



}
