package com.travelrhythm.web.service;

import com.travelrhythm.domain.repository.region.RegionRepository;
import com.travelrhythm.web.dto.PagingDTO;
import com.travelrhythm.web.dto.RegionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class RegionService {

  @Autowired
  private RegionRepository regionRepository;

  public Page<RegionResponseDTO> getRegions(PagingDTO dto) {
    return regionRepository.findAllRegions(dto.getPageRequest());
  }

}
