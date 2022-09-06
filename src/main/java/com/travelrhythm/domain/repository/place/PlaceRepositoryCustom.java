package com.travelrhythm.domain.repository.place;

import com.travelrhythm.domain.repository.place.param.PlaceSearchParam;
import com.travelrhythm.web.dto.PlaceResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceRepositoryCustom {

  Page<PlaceResponseDTO> findAll(PlaceSearchParam param, Pageable pageable);

}
