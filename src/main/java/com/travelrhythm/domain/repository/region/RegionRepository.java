package com.travelrhythm.domain.repository.region;

import com.travelrhythm.domain.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long>, RegionRepositoryCustom {

}
