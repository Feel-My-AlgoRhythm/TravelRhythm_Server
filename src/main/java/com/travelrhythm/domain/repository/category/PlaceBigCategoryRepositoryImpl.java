package com.travelrhythm.domain.repository.category;

import static com.travelrhythm.domain.entity.QPlaceBigCategory.placeBigCategory;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelrhythm.domain.entity.PlaceBigCategory;
import com.travelrhythm.web.dto.PlaceBigCategoryResponseDTO;
import com.travelrhythm.web.dto.QPlaceBigCategoryResponseDTO;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PlaceBigCategoryRepositoryImpl extends QuerydslRepositorySupport implements
    PlaceBigCategoryRepositoryCustom {

  private final JPAQueryFactory query;

  public PlaceBigCategoryRepositoryImpl(EntityManager em) {
    super(PlaceBigCategory.class);
    this.query = new JPAQueryFactory(em);
  }

  @Override
  public Page<PlaceBigCategoryResponseDTO> findAllPlaceBigCategories(Pageable pageable) {

    JPAQuery<PlaceBigCategoryResponseDTO> jpaQuery = query
        .select(new QPlaceBigCategoryResponseDTO(
            placeBigCategory.id,
            placeBigCategory.name
        ))
        .from(placeBigCategory);

    long totalCount = jpaQuery.fetchCount();
    List<PlaceBigCategoryResponseDTO> results = getQuerydsl().applyPagination(pageable, jpaQuery)
        .fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }
}
