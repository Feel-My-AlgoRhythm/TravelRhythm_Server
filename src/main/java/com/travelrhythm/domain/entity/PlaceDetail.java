package com.travelrhythm.domain.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Getter
@Setter
@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class PlaceDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "place_detail_id")
  private Long id;

  private String naverId;
  private String posExact;
  private Double x;
  private Double y;

  @Type(type = "json")
  @Column(name = "data", columnDefinition = "json")
  private Map<String, Object> data;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlaceDetail)) {
      return false;
    }
    PlaceDetail that = (PlaceDetail) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
