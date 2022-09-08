package com.travelrhythm.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceSmallCategoryResponseDTO {

  private Long id;
  private String name;

  private Long bigCategoryId;
  private String bigCategoryName;

  @QueryProjection
  public PlaceSmallCategoryResponseDTO(Long id, String name, Long bigCategoryId,
      String bigCategoryName) {
    this.id = id;
    this.name = name;
    this.bigCategoryId = bigCategoryId;
    this.bigCategoryName = bigCategoryName;
  }
}
