package com.example.team3.domain;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameApiResponse {
	private AppDetailsData appDetails;

    // JsonAnySetter 어노테이션을 사용하여 동적 키 처리
    // 첫 번째 파라미터는 JSON의 키(여기서는 "730"), 두 번째 파라미터는 값
	
    @JsonAnySetter
    public void setDynamicAppDetails(String key, AppDetailsData value) {
    	
        // 이 메소드에서 어떤 키가 들어오든, 그 값을 appDetails 필드에 할당합니다.
        // Steam API의 경우 한 번에 하나의 앱 ID만 요청하므로 간단히 처리할 수 있습니다.
    	
        this.appDetails = value;
    }
}