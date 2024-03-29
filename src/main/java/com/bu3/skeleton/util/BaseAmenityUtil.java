package com.bu3.skeleton.util;

import com.bu3.skeleton.dto.response.PageableResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@Component
public class BaseAmenityUtil {

    public String getMessageBundle(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("skeleton");
        return resourceBundle.getString(key);
    }

    public long currentTimeSeconds() {
        long currentTimeMillis = System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis);
    }

    public PageableResponse pageableResponse(int currentPage, int limitPage, int totalPage) {
        Pageable pageable = pageable(currentPage, limitPage);

        return PageableResponse.builder()
                .totalPage(totalPage)
                .meta(pageable)
                .build();
    }

    public Pageable pageable(int currentPage, int limitPage) {
        return PageRequest.of(currentPage - 1, limitPage);
    }

}
