package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.dto.RoomDto;
import com.bu3.skeleton.dto.RoomImageDto;
import com.bu3.skeleton.dto.request.room.RoomInfoRequest;
import com.bu3.skeleton.dto.request.room.RoomRequest;
import com.bu3.skeleton.dto.response.room.RoomResponse;
import com.bu3.skeleton.entity.Room;
import com.bu3.skeleton.entity.RoomImage;
import com.bu3.skeleton.entity.RoomInfo;
import com.bu3.skeleton.enums.Language;
import com.bu3.skeleton.repository.IRoomImageRepo;
import com.bu3.skeleton.repository.IRoomInfoRepo;
import com.bu3.skeleton.repository.IRoomRepo;
import com.bu3.skeleton.sevice.IRoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

    private final IRoomRepo roomRepo;
    private final IRoomImageRepo roomImageRepo;
    private final IRoomInfoRepo roomInfoRepo;
//    private final RoomDtoMapper roomDtoMapper;



    private Room buildRoomEntity(RoomRequest roomRequest) {
        return Room.builder()
                .price(roomRequest.getPrice())
                .quantityRoom(roomRequest.getQuantityRoom())
                .acreage(roomRequest.getAcreage())
                .capacity(roomRequest.getCapacity())
                .adultQuantity(roomRequest.getAdultQuantity())
                .childrenQuantity(roomRequest.getChildrenQuantity())
                .extraBedQuantity(roomRequest.getExtraBedQuantity())
                .singleBedQuantity(roomRequest.getSingleBedQuantity())
                .twinBedQuantity(roomRequest.getTwinBedQuantity())
                .status(roomRequest.getStatus())
                .build();
    }


    private RoomImage buildRoomImageEntity(RoomImageDto roomImageDto, Room room) {
        return RoomImage.builder()
                .name(roomImageDto.getName())
                .data(roomImageDto.getData())
                .uploadDate(roomImageDto.getUploadDate())
                .room(room)
                .isDeleted(false)
                .build();
    }

    private RoomInfo buildRoomInfoEntity(RoomInfoRequest roomInfoRequest, Room room) {
        String languageCodeReq = String.valueOf(roomInfoRequest.getLanguageCode());
        Language languageCode = Language.valueOf(languageCodeReq);

        return RoomInfo.builder()
                .name(roomInfoRequest.getName())
                .description(roomInfoRequest.getDescription())
//                .status(roomInfoRequest.getStatus())
//                .language(languageCode)
                .room(room)
                .build();
    }





    @Override
    public void addRoom(RoomRequest roomRequest) {
        // Create Room entity

        System.out.println("check ->>>" + roomRequest.getQuantityRoom());

        Room room = buildRoomEntity(roomRequest);

        Room savedRoom = roomRepo.save(room);

        // Create and save RoomImage entities
        List<RoomImage> roomImages = roomRequest.getRoomImages().stream()
                .map(item -> buildRoomImageEntity(item, savedRoom))
                .collect(Collectors.toList());
        roomImageRepo.saveAll(roomImages);

        // Create and save RoomInfo entities
        List<RoomInfo> roomInfoList = roomRequest.getRoomInfo().stream()
                .map(item -> buildRoomInfoEntity(item, savedRoom))
                .collect(Collectors.toList());

        roomInfoRepo.saveAll(roomInfoList);

        System.out.println("save ->>> ");

    }

    @Override
    public RoomResponse findRooms(Integer currentPage, Integer limitPage) {
        return null;
    }

    @Override
    public RoomDto getRoomByUUID(UUID uuid) {
        return null;
    }

    @Override
    public RoomResponse updateRoom(RoomRequest roomRequest, UUID idRoom) {


        Optional<Room> findRoom = roomRepo.findById(idRoom);
        Room roomToUpdate = findRoom.get();
        if (findRoom.isPresent())
        {

            if (!Objects.equals(roomRequest.getQuantityRoom(), roomToUpdate.getQuantityRoom())) {
                roomToUpdate.setQuantityRoom(roomRequest.getQuantityRoom());
            }

            if (!Objects.equals(roomRequest.getAcreage(), roomToUpdate.getAcreage())) {
                roomToUpdate.setAcreage(roomRequest.getAcreage());
            }

            if (!Objects.equals(roomRequest.getCapacity(), roomToUpdate.getCapacity())) {
                roomToUpdate.setCapacity(roomRequest.getCapacity());
            }

            if (!Objects.equals(roomRequest.getTwinBedQuantity(), roomToUpdate.getTwinBedQuantity())) {
                roomToUpdate.setTwinBedQuantity(roomRequest.getTwinBedQuantity());
            }

            if (!Objects.equals(roomRequest.getPrice(), roomToUpdate.getPrice())) {
                roomToUpdate.setPrice(roomRequest.getPrice());
            }

            if (!Objects.equals(roomRequest.getSingleBedQuantity(), roomToUpdate.getSingleBedQuantity())) {
                roomToUpdate.setSingleBedQuantity(roomRequest.getSingleBedQuantity());
            }

            if (!Objects.equals(roomRequest.getChildrenQuantity(), roomToUpdate.getChildrenQuantity())) {
                roomToUpdate.setChildrenQuantity(roomRequest.getChildrenQuantity());
            }

            if (!Objects.equals(roomRequest.getStatus(), roomToUpdate.getStatus())) {
                roomToUpdate.setStatus(roomRequest.getStatus());
            }

            if (!Objects.equals(roomRequest.getAdultQuantity(), roomToUpdate.getAdultQuantity())) {
                roomToUpdate.setAdultQuantity(roomRequest.getAdultQuantity());
            }

            if (!Objects.equals(roomRequest.getExtraBedQuantity(), roomToUpdate.getExtraBedQuantity())) {
                roomToUpdate.setExtraBedQuantity(roomRequest.getExtraBedQuantity());
            }

//                    roomToUpdate.setHotel(roomRequest.());


            Room savedRoom = roomRepo.save(roomToUpdate);

            if (roomRequest.getRoomImages() != null) {

                System.out.println("test existing image room");


                List<String> listImageExist = new ArrayList<>();

                roomImageRepo.findAllByRoomId(roomToUpdate.getId())
                        .stream()
                        .map(item -> listImageExist.add(item.getName()));

                for (RoomImageDto item : roomRequest.getRoomImages()) {
                    if (!listImageExist.contains(item.getName())) {
                        RoomImage roomImage = buildRoomImageEntity(item, roomToUpdate);
                        roomImageRepo.save(roomImage);
                    }
                }
            }


            if (roomRequest.getRoomInfo() != null) {

                List<RoomInfo> infoRoomDelete = roomInfoRepo.findAllByRoomId(roomToUpdate.getId());
                roomInfoRepo.deleteAll(infoRoomDelete);

                for (RoomInfoRequest item : roomRequest.getRoomInfo()) {
                    RoomInfo roomInfo = buildRoomInfoEntity(item, roomToUpdate);
                    roomInfoRepo.save(roomInfo);

                }
            }


            roomRepo.save(roomToUpdate);

            return null;
        }

        return null;
    }

    @Override
    public RoomResponse deleteRoom(UUID uuid) {

        Optional<Room> findRoom = roomRepo.findById(uuid);

        if (findRoom.isPresent()) {
            List<RoomImage> imageRoomDelete = roomImageRepo.findAllByRoomId(uuid);
            imageRoomDelete.stream().forEach(item -> item.setIsDeleted(true));
            roomImageRepo.saveAll(imageRoomDelete);

            System.out.println("delete ->>>");

//                List<RoomInfo> infoRoomDelete = roomInfoRepo.findAllByRoomId(uuid);
            // check lai do khong co isDelete
//                roomInfoRepo.deleteAll(infoRoomDelete);

//                roomRepo.deleteById(uuid);
        }


        return null;
    }
    private int getTotalPage(int size, int limit) {
        return (int) Math.ceil((double) size / limit);
    }
}
