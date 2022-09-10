package com.travelrhythm.util;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HttpUtil {

  private final String DATALAB_API_PRERIX = "https://datalab.visitkorea.or.kr";
  private final String NAVER_MAP_SEARCH_API_PREFIX = "https://map.naver.com/v5/api/search";

  @Autowired
  private RestTemplate restTemplate;

  public String findPlaceListByDatalab(String ssgCode, String bigCategory) throws RestClientException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    LocalDate now = LocalDate.now();
    LocalDate startDate = now.minusYears(1);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("SGG_CD", ssgCode);
    map.add("txtSGG_CD", "1");
    map.add("txtSIDO_ARR", "1");
    map.add("BASE_YM1", startDate.format(DateTimeFormatter.ofPattern("yyyyMM")));
    map.add("BASE_YM2", now.format(DateTimeFormatter.ofPattern("yyyyMM")));
    map.add("TMAP_CATE_MCLS_CD", bigCategory);
    map.add("srchAreaDate", "1");
    map.add("qid", "BDT_03_04_003");

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(
        URI.create(DATALAB_API_PRERIX + "/visualize/getTempleteData.do"), request, String.class);

    log.info("HTTP RES : {} | {}", response.getStatusCodeValue(), response.getBody());
    if (response.getStatusCodeValue() != 200) {
      throw new RestClientException("Invalid Http Status Code");
    }
    return response.getBody();
  }

  public String findPlaceDetailByNaver(String placeName) throws RestClientException {
    String basicParams = "?caller=pcweb&type=all&page=1&displayCount=1&isPlaceRecommendationReplace=true&lang=ko";

    ResponseEntity<String> response = restTemplate.getForEntity(
        NAVER_MAP_SEARCH_API_PREFIX + basicParams + "&query=" + placeName
        , String.class);

    log.info("HTTP RES : {} | {}", response.getStatusCodeValue(), response.getBody());
    if (response.getStatusCodeValue() != 200) {
      throw new RestClientException("Invalid Http Status Code");
    }
    return response.getBody();
  }
}
