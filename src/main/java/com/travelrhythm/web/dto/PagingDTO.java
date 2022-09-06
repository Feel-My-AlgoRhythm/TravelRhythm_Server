package com.travelrhythm.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class PagingDTO {

  @Min(value = 0)
  private int page = 0;
  @Min(value = 1)
  @Max(value = 100)
  private int size = 20;

  public Pageable getPageRequest() {
    return PageRequest.of(this.page, this.size);
  }

}
