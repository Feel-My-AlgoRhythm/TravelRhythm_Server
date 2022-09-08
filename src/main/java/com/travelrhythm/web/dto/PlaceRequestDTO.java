package com.travelrhythm.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceRequestDTO extends PagingDTO {

  private Long regionId;
  private Long bigCategoryId;
  private Long smallCategoryId;

}
