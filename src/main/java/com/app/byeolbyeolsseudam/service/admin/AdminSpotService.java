package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.spot.SpotDTO;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.spot.Spot;
import com.app.byeolbyeolsseudam.repository.admin.course.AdminCourseRepository;
import com.app.byeolbyeolsseudam.repository.admin.spot.AdminSpotRepository;
import com.app.byeolbyeolsseudam.repository.spot.SpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminSpotService {
    private final AdminSpotRepository adminSpotRepository;
    private final AdminCourseRepository adminCourseRepository;

    public Page<SpotDTO> searchSpot(Pageable pageable){
        List<SpotDTO> spots = adminSpotRepository.searchSpotPaging("", pageable);
        final Page<SpotDTO> spotList = new PageImpl<>(spots, pageable, adminSpotRepository.findAll().size());
        return spotList;
    }

    public Page<SpotDTO> searchSpotPaging(String keyword, Pageable pageable){
        List<SpotDTO> search = adminSpotRepository.searchSpot(keyword);
        List<SpotDTO> spots = adminSpotRepository.searchSpotPaging(keyword,pageable);

        final Page<SpotDTO> spotSearchList = new PageImpl<>(spots, pageable, search.size());
        return spotSearchList;
    }
    public SpotDTO selectById(String spotIdstr){
        Long spotId = Long.parseLong(spotIdstr);
        return adminSpotRepository.selectById(spotId);
    }

    public void updateSpot(SpotDTO spotDTO, Long spotId){
        adminSpotRepository.update(spotDTO);
        log.info(spotDTO.getCourseId()+": 코스아이디");
        Course course = adminCourseRepository.findById(spotDTO.getCourseId()).get();
        Spot spot = adminSpotRepository.findById(spotId).get();

        spot.changeCourse(course);
        adminSpotRepository.save(spot);
    }
    public void removeSpot(List<String> spotIdstr){
        List<Long> spotIds = new ArrayList<>();
        spotIdstr.stream().map(Long::parseLong).forEach(spotIds::add);
        spotIds.forEach(adminSpotRepository::deleteById);

    }
}
