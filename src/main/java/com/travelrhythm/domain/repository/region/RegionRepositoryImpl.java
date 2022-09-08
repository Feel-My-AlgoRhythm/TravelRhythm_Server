package com.travelrhythm.domain.repository.region;

import static com.travelrhythm.domain.entity.QRegion.region;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelrhythm.domain.entity.Region;
import com.travelrhythm.web.dto.QRegionResponseDTO;
import com.travelrhythm.web.dto.RegionResponseDTO;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class RegionRepositoryImpl extends QuerydslRepositorySupport implements
    RegionRepositoryCustom {

  private final JPAQueryFactory query;

  public RegionRepositoryImpl(EntityManager em) {
    super(Region.class);
    this.query = new JPAQueryFactory(em);
  }

  @Override
  public Page<RegionResponseDTO> findAllRegions(Pageable pageable) {
    JPAQuery<RegionResponseDTO> jpaQuery = query
        .select(new QRegionResponseDTO(
            region.id,
            region.ssgCode,
            region.ssgName
        )).from(region);

    long totalCount = jpaQuery.fetchCount();
    List<RegionResponseDTO> results = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }
}
