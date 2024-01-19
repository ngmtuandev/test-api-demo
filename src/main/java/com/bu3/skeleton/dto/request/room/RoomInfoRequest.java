package com.bu3.skeleton.dto.request.room;

import com.bu3.skeleton.entity.ServiceForRoom;
import com.bu3.skeleton.enums.Language;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoomInfoRequest {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "description cannot be null")
    @NotEmpty(message = "description cannot be empty")
    private String description;

    @NotNull(message = "status cannot be null")
    @NotEmpty(message = "status cannot be empty")
    private String status;

    private Language languageCode;
//
//    @NotNull(message = "serviceForRooms cannot be null")
//    @NotEmpty(message = "serviceForRooms cannot be empty")
    private List<ServiceForRoom> serviceForRooms;
}

