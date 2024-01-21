package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.room.RoomRequest;
import com.bu3.skeleton.dto.response.room.RoomResponse;
import com.bu3.skeleton.sevice.IRoomService;
import io.netty.channel.unix.Errors;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_1 + SystemConstant.API_ROOM )
@RequiredArgsConstructor

public class RoomAdminController {

    private final IRoomService roomService;

    @GetMapping("/{currentPage}/{limitPage}")
    public ResponseEntity<RoomResponse> findRooms(
            @PathVariable("currentPage") Optional<Integer> currentPage,
            @PathVariable("limitPage") Optional<Integer> limitPage
    ) {
        return new ResponseEntity<>(roomService.findRooms(currentPage.orElse(1), limitPage.orElse(8)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody RoomRequest request) {
//        validationService.handleValidate(errors);
        System.out.println("ok");
        roomService.addRoom(request);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editRoom(@Valid @RequestBody RoomRequest request, @PathVariable UUID id) {
        RoomResponse result = roomService.updateRoom(request, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom( @PathVariable UUID id) {
        RoomResponse result = roomService.deleteRoom(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
