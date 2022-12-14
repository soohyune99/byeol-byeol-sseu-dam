package com.app.byeolbyeolsseudam.repository.admin.pickup;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminPickupCustomRepository {
    public List<PickupDTO> showAdminPickup();
    public List<PickupDTO> showPickupList(Pageable pageable);
    public PickupDTO selectById(Long pickupId);

}
