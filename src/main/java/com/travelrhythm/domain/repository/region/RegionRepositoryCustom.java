package com.travelrhythm.domain.repository.region;

import com.travelrhythm.web.dto.RegionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegionRepositoryCustom {

  Page<RegionResponseDTO> findAllRegions(Pageable pageable);

}
