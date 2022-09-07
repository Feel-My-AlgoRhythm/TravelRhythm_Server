package com.travelrhythm.domain.repository.category;

import com.travelrhythm.domain.repository.category.param.PlaceSmallCategorySearchParam;
import com.travelrhythm.web.dto.PlaceSmallCategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceSmallCategoryRepositoryCustom {

  Page<PlaceSmallCategoryResponseDTO> findAll(
      PlaceSmallCategorySearchParam param, Pageable pageable);

}
