package com.travelrhythm.domain.repository.category;

import com.travelrhythm.web.dto.PlaceBigCategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceBigCategoryRepositoryCustom {

  Page<PlaceBigCategoryResponseDTO> findAllPlaceBigCategories(Pageable pageable);

}
