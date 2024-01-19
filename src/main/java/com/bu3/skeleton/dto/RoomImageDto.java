package com.bu3.skeleton.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class RoomImageDto {

    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotNull(message = "data cannot be null")
    @NotEmpty(message = "data cannot be empty")
    private byte[] data;

    private LocalDateTime uploadDate;

    private UUID roomId;
}