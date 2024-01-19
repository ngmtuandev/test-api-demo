package com.bu3.skeleton.dto;


import com.bu3.skeleton.entity.ServiceForRoom;
import com.bu3.skeleton.enums.Language;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RoomInfoDto {

    private String name;

    private String description;

    private String status;

    private Language languageCode;

    private List<ServiceForRoom> serviceForRooms;
}
