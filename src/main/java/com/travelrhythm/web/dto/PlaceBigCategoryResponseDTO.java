package com.travelrhythm.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceBigCategoryResponseDTO {

  private Long id;
  private String name;

  @QueryProjection
  public PlaceBigCategoryResponseDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
