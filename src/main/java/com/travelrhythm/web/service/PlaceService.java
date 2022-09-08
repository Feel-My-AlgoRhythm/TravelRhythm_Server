package com.travelrhythm.web.service;

import com.travelrhythm.domain.repository.place.PlaceRepository;
import com.travelrhythm.domain.repository.place.param.PlaceSearchParam;
import com.travelrhythm.web.dto.PlaceRequestDTO;
import com.travelrhythm.web.dto.PlaceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PlaceService {

  @Autowired
  private PlaceRepository placeRepository;

  public Page<PlaceResponseDTO> getPlaces(PlaceRequestDTO dto) {
    return placeRepository.findAll(PlaceSearchParam.valueOf(dto), dto.getPageRequest());
  }

}
