package com.travelrhythm.domain.repository.place;

import static com.travelrhythm.domain.entity.QPlace.place;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelrhythm.domain.entity.Place;
import com.travelrhythm.domain.entity.QPlaceBigCategory;
import com.travelrhythm.domain.entity.QPlaceDetail;
import com.travelrhythm.domain.entity.QPlaceSmallCategory;
import com.travelrhythm.domain.entity.QRegion;
import com.travelrhythm.domain.repository.place.param.PlaceSearchParam;
import com.travelrhythm.web.dto.PlaceResponseDTO;
import com.travelrhythm.web.dto.QPlaceResponseDTO;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PlaceRepositoryImpl extends QuerydslRepositorySupport implements
    PlaceRepositoryCustom {

  private final JPAQueryFactory query;
  private final QRegion region;
  private final QPlaceBigCategory bigCategory;
  private final QPlaceSmallCategory smallCategory;
  private final QPlaceDetail placeDetail;

  public PlaceRepositoryImpl(EntityManager em) {
    super(Place.class);
    this.query = new JPAQueryFactory(em);
    this.region = new QRegion("region");
    this.bigCategory = new QPlaceBigCategory("bigCategory");
    this.smallCategory = new QPlaceSmallCategory("smallCategory");
    this.placeDetail = new QPlaceDetail("placeDetail");
  }

  @Override
  public Page<PlaceResponseDTO> findAll(PlaceSearchParam param, Pageable pageable) {

    BooleanBuilder whereCondition = getWhereCondition(param);

    JPAQuery<PlaceResponseDTO> jpaQuery = query
        .select(new QPlaceResponseDTO(
            place.id,
            place.name,
            place.addressRoadName,
            region.id,
            region.ssgCode,
            region.ssgName,
            bigCategory.id,
            bigCategory.name,
            smallCategory.id,
            smallCategory.name,
            placeDetail.id,
            placeDetail.naverId,
            placeDetail.posExact,
            placeDetail.x,
            placeDetail.y,
            placeDetail.data
        ))
        .from(place)
        .innerJoin(place.region, region)
        .innerJoin(place.placeBigCategory, bigCategory)
        .innerJoin(place.placeSmallCategory, smallCategory)
        .leftJoin(place.placeDetail, placeDetail)
        .where(whereCondition)
        .orderBy(place.id.desc());

    long totalCount = jpaQuery.fetchCount();
    List<PlaceResponseDTO> results = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }

  private BooleanBuilder getWhereCondition(PlaceSearchParam param) {
    BooleanBuilder whereCondition = new BooleanBuilder();
    if (param.getRegionId() != null) {
      whereCondition.and(region.id.eq(param.getRegionId()));
    }
    if (param.getBigCategoryId() != null) {
      whereCondition.and(bigCategory.id.eq(param.getBigCategoryId()));
    }
    if (param.getSmallCategoryId() != null) {
      whereCondition.and(smallCategory.id.eq(param.getSmallCategoryId()));
    }
    return whereCondition;
  }

}