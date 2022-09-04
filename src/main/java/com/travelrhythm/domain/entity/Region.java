package com.travelrhythm.domain.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Region {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "region_id")
  private Long id;

  @Column(unique = true)
  private String ssgCode;   //ssgCd
  private String ssgName;   //ssgNm

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Region)) {
      return false;
    }
    Region that = (Region) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
