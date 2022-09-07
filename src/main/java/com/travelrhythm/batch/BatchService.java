package com.travelrhythm.batch;

import com.travelrhythm.domain.entity.Place;
import com.travelrhythm.domain.entity.PlaceBigCategory;
import com.travelrhythm.domain.entity.PlaceDetail;
import com.travelrhythm.domain.entity.PlaceSmallCategory;
import com.travelrhythm.domain.entity.Region;
import com.travelrhythm.domain.repository.category.PlaceBigCategoryRepository;
import com.travelrhythm.domain.repository.place.PlaceDetailRepository;
import com.travelrhythm.domain.repository.place.PlaceRepository;
import com.travelrhythm.domain.repository.category.PlaceSmallCategoryRepository;
import com.travelrhythm.domain.repository.region.RegionRepository;
import com.travelrhythm.util.HttpUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BatchService {

  @Autowired
  private RegionRepository regionRepository;
  @Autowired
  private PlaceRepository placeRepository;
  @Autowired
  private PlaceDetailRepository placeDetailRepository;
  @Autowired
  private PlaceBigCategoryRepository placeBigCategoryRepository;
  @Autowired
  private PlaceSmallCategoryRepository placeSmallCategoryRepository;
  @Autowired
  private HttpUtil httpUtil;

  @Transactional
  public void findPoisByDatalab() {
    List<Region> regionList = regionRepository.findAll();
    List<PlaceBigCategory> categoryList = placeBigCategoryRepository.findAll();

    // get region and category randomly
    int randomValue = (int)(Math.random() * regionList.size());
    Region region = regionList.get(randomValue);
    randomValue = (int)(Math.random() * categoryList.size());
    PlaceBigCategory bigCategory = categoryList.get(randomValue);

    log.info("findPoisByDatalab REQ : {} {} | {}", region.getSsgCode(), region.getSsgName(), bigCategory.getName());
    String responseValue = httpUtil.findPoisByDatalab(region.getSsgCode(), bigCategory.getName());
    log.info("findPoisByDatalab RES : {}", responseValue);

    List<Place> placeListForSave = new ArrayList<>();
    try {
      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObj = (JSONObject) jsonParser.parse(responseValue);
      JSONArray jsonArray = (JSONArray) jsonObj.get("list");
      for (int i=0; i<jsonArray.size(); ++i) {
        JSONObject jo = (JSONObject)jsonArray.get(i);

        String smallCategoryValue = jo.get("KTO_CATE_SCLS_NM").toString();
        PlaceSmallCategory smallCategory = placeSmallCategoryRepository
            .findByName(smallCategoryValue).orElse(null);

        // add new small category
        if (smallCategory == null) {
          log.info("new small category : {}", smallCategoryValue);
          smallCategory = new PlaceSmallCategory();
          smallCategory.setName(smallCategoryValue);
          smallCategory.setPlaceBigCategory(bigCategory);
          placeSmallCategoryRepository.save(smallCategory);
        }

        Place place = new Place();
        place.setName(jo.get("ITS_BRO_NM").toString());
        place.setAddressRoadName(jo.get("ADDR_ROAD_NM").toString());
        place.setPlaceBigCategory(bigCategory);
        place.setPlaceSmallCategory(smallCategory);
        place.setRegion(region);
        placeListForSave.add(place);
      }
    } catch (ParseException e) {
      log.error(e.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    placeRepository.saveAll(placeListForSave);
  }

  @Transactional
  public void addPoiDetailDataByNaver() {
    List<Place> placeList = placeRepository.findTop250ByPlaceDetailOrderByIdDesc(null);

    for (Place place: placeList) {
      log.info("addPoiDetailDataByNaver REQ : {}, {}", place.getId(), place.getName());
      String responseValue = httpUtil.findPoiDetailDataByNaver(place.getName());
      log.info("addPoiDetailDataByNaver RES : {}", responseValue);

      try {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(responseValue);
        JSONObject resultObj = (JSONObject) jsonParser.parse(jsonObj.get("result").toString());
        JSONObject placeObj = (JSONObject) jsonParser.parse(resultObj.get("place").toString());
        JSONObject dataObj = (JSONObject) ((JSONArray) placeObj.get("list")).get(0);

        PlaceDetail placeDetail = new PlaceDetail();
        placeDetail.setData(dataObj);
        placeDetail.setNaverId(dataObj.get("id").toString());
        placeDetail.setPosExact(dataObj.get("posExact").toString());
        placeDetail.setX(dataObj.get("x").toString());
        placeDetail.setY(dataObj.get("y").toString());
        placeDetailRepository.save(placeDetail);

        place.setPlaceDetail(placeDetail);
        placeRepository.save(place);
      } catch (ParseException e) {
        log.error(e.getMessage());
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    }
  }

}
