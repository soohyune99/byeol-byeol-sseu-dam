package com.app.byeolbyeolsseudam.repository.admin.spot;

import com.app.byeolbyeolsseudam.domain.spot.SpotDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminSpotCustomRepository {
    public List<SpotDTO> searchSpot(String keyword);
    public List<SpotDTO> searchSpotPaging(String keyword, Pageable pageable);
    public SpotDTO selectById(Long spotId);
    public void update(SpotDTO spotDTO);
}
