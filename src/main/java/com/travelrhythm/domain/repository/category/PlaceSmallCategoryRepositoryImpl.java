package com.travelrhythm.domain.repository.category;

import static com.travelrhythm.domain.entity.QPlaceSmallCategory.placeSmallCategory;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelrhythm.domain.entity.PlaceSmallCategory;
import com.travelrhythm.domain.entity.QPlaceBigCategory;
import com.travelrhythm.domain.repository.category.param.PlaceSmallCategorySearchParam;
import com.travelrhythm.web.dto.PlaceSmallCategoryResponseDTO;
import com.travelrhythm.web.dto.QPlaceSmallCategoryResponseDTO;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PlaceSmallCategoryRepositoryImpl extends QuerydslRepositorySupport implements
    PlaceSmallCategoryRepositoryCustom {

  private final JPAQueryFactory query;
  private final QPlaceBigCategory bigCategory;

  public PlaceSmallCategoryRepositoryImpl(EntityManager em) {
    super(PlaceSmallCategory.class);
    this.query = new JPAQueryFactory(em);
    this.bigCategory = new QPlaceBigCategory("bigCategory");
  }

  @Override
  public Page<PlaceSmallCategoryResponseDTO> findAll(PlaceSmallCategorySearchParam param,
      Pageable pageable) {

    BooleanBuilder whereCondition = getWhereCondition(param);

    JPAQuery<PlaceSmallCategoryResponseDTO> jpaQuery = query
        .select(new QPlaceSmallCategoryResponseDTO(
            placeSmallCategory.id,
            placeSmallCategory.name,
            bigCategory.id,
            bigCategory.name
        ))
        .from(placeSmallCategory)
        .innerJoin(placeSmallCategory.placeBigCategory, bigCategory)
        .where(whereCondition)
        .orderBy(bigCategory.id.asc());

    long totalCount = jpaQuery.fetchCount();
    List<PlaceSmallCategoryResponseDTO> results = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }

  private BooleanBuilder getWhereCondition(PlaceSmallCategorySearchParam param) {
    BooleanBuilder whereCondition = new BooleanBuilder();
    if (param.getPlaceBigCategoryId() != null) {
      whereCondition.and(bigCategory.id.eq(param.getPlaceBigCategoryId()));
    }
    return whereCondition;
  }
}
