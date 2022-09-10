package com.travelrhythm.web.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceRequestDTO extends PagingDTO {

  private List<Long> regionIdList;
  private List<Long> bigCategoryIdList;
  private Long smallCategoryId;

}
