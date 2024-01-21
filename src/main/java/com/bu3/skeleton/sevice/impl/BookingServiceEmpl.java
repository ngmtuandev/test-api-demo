package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.constant.ResourceBundleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.GuestInfoDto;
import com.bu3.skeleton.dto.RoomDto;
import com.bu3.skeleton.dto.RoomInfoDto;
import com.bu3.skeleton.dto.request.booking.BookingRequest;
import com.bu3.skeleton.dto.response.booking.BookingDetailResponse;
import com.bu3.skeleton.dto.response.booking.BookingResponse;
import com.bu3.skeleton.dto.response.booking.BookingResponseData;
import com.bu3.skeleton.dto.response.role.RoleResponse;
import com.bu3.skeleton.entity.*;
import com.bu3.skeleton.enums.RoomStatus;
import com.bu3.skeleton.repository.*;
import com.bu3.skeleton.sevice.IBookingService;
import com.bu3.skeleton.util.BaseAmenityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceEmpl implements IBookingService {
    private final IBookingRepo bookingRepo;

//    private final BookingDtoMapper bookingDtoMapper;

    private final IGuestInfoRepo guestInfoRepo;

    private final IPaymentRepo methodPaymentRepo;

    private final IBookingDetailRepo bookingDetailRepo;

    private final IRoomRepo roomRepo;

    private final BaseAmenityUtil baseAmenityUtil;



    private GuestInfo buildGuestInfo(GuestInfoDto guestInfoDto, Booking booking) {

            return GuestInfo.builder()
                    .email(guestInfoDto.getEmail())
                    .phoneNumber(guestInfoDto.getPhoneNumber())
                    .titles(guestInfoDto.getTitles())
                    .name(guestInfoDto.getName())
                    .booking(booking)
                    .build();
    }

    private GuestInfoDto buildGuestInfoDto(GuestInfo guestInfo) {
        return GuestInfoDto.builder()
                .titles(guestInfo.getTitles())
                .name(guestInfo.getName())
                .build();
    }
    private BookingDetail buildBookingDetailForGuestInfo(Booking booking, UUID roomId) {
        Room room = roomRepo.findById(roomId).orElse(null);

        if (room != null) {
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setIsDeleted(false);
            bookingDetail.setRoom(room);
            bookingDetail.setBooking(booking);

            return bookingDetail;
        }

        return null;
    }



    int adult;
    int children;


    private Boolean checkConfirmChildren(List<GuestInfoDto> listGuestInfo) {

        listGuestInfo.stream().forEach(item -> {
            if (item.getAge() > 12) {
                adult++;
            }
            else {
                children++;
            }
        });


        if (children >= 1 && adult == 0) {
            System.out.println("children > adult  ==> Fail");
            adult = 0;
            children = 0;
            return false;
        }

        adult = 0;
        children = 0;
        System.out.println("confirm success");
        return true;


    }

    public BigDecimal totalBooking(List<Room> rooms, List<GuestInfoDto> guestInfoDtos) {

        BigDecimal totalBooking = BigDecimal.ZERO;

        guestInfoDtos.stream().forEach(item -> {
            if (item.getAge() < 12) {
                children++;
            }
        });

        for (Room room : rooms) {
            totalBooking = totalBooking.add(room.getPrice());
        }

        if (children>0) {
            totalBooking = totalBooking.add(BigDecimal.valueOf(children * 100000));
        }

        return totalBooking;
    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Booking newBooking = new Booking();

        Payment methodPayment = new Payment();


        Boolean resultCheckConfirm = checkConfirmChildren(bookingRequest.getGuestInfos()) ;

        System.out.println("check confirm ->>>>" + resultCheckConfirm);

        if (resultCheckConfirm){
            methodPayment.setDateOfPayment(bookingRequest.getPayment().getDateOfPayment());
            methodPayment.setStatus(bookingRequest.getPayment().getStatus());
            methodPayment.setAmountPaid(bookingRequest.getPayment().getAmountPaid());
            methodPayment.setDateOfPayment(bookingRequest.getPayment().getDateOfPayment());

            methodPaymentRepo.save(methodPayment);

            BookingDetailResponse bookingDetailResponse = new BookingDetailResponse();


            if ((!bookingRequest.getCustomerName().isEmpty()) && (!bookingRequest.getCustomerPhoneNumber().isEmpty()) && (!bookingRequest.getCustomerMail().isEmpty())) {

                newBooking.setCustomerMail(bookingRequest.getCustomerMail());
                newBooking.setCustomerName(bookingRequest.getCustomerName());
                newBooking.setCustomerPhoneNumber(bookingRequest.getCustomerPhoneNumber());
                newBooking.setIsDeleted(false);

                // set Payment for booking

                // save basic
                newBooking.setPayment(methodPayment);

                bookingRepo.save(newBooking);

                if (bookingRequest.getIdRooms() != null && !bookingRequest.getIdRooms().isEmpty()) {
                    List<UUID> idRooms = bookingRequest.getIdRooms();

                    List<Room> rooms = roomRepo.findAllById(idRooms).stream()
                            .toList();

                    if (rooms.size() == idRooms.size()) {
                        List<BookingDetail> bookingDetails = rooms.stream()
                                .map(room -> BookingDetail.builder()
                                        .room(room)
                                        .booking(newBooking)
                                        .isDeleted(false)
                                        .build())
                                .collect(Collectors.toList());

                        List<RoomDto> roomDtos = rooms.stream().map(
                                roomItem -> RoomDto.builder()
                                        .quantityRoom(roomItem.getQuantityRoom())
                                        .price(roomItem.getPrice())
                                        .roomInfo(roomItem.getRoomInfos().stream()
                                                .map(item_room -> RoomInfoDto.builder()
                                                        .name(item_room.getName())
                                                        .description(item_room.getDescription())
                                                        .build()
                                                ).collect(Collectors.toList())
                                        )
                                        .build())
                                .collect(Collectors.toList());


                        bookingDetailRepo.saveAll(bookingDetails);
                        System.out.println("BookingDetail đã được thêm thành công");

                        bookingDetails.stream().forEach(bookingDetailItem -> {
                            // Thêm repo guest_info
                            if (bookingRequest.getGuestInfos() != null) {
                                List<GuestInfoDto> guestInfoDtoList = bookingRequest.getGuestInfos();

                                // dùng stream() -> lặp qua lần lượt booking_detail -> map(thực hiện như bên dưới nhưng th)

                                List<GuestInfo> guestInfos = guestInfoDtoList.stream()
                                        .map(guestInfoDto -> GuestInfo.builder()
                                                .titles(guestInfoDto.getTitles())
                                                .name(guestInfoDto.getName())
                                                .phoneNumber(guestInfoDto.getPhoneNumber())
                                                .email(guestInfoDto.getEmail())
                                                .booking(newBooking)
                                                .bookingDetail(bookingDetailItem)
                                                .build())
                                        .collect(Collectors.toList());



                                guestInfoRepo.saveAll(guestInfos);
//                                bookingDetailResponse.setGuestInfos(guestInfos);
                                bookingRequest.getIdRooms().stream().forEach(item -> {
                                   Optional<Room> roomBooked = roomRepo.findById(item);
                                   if (roomBooked.isPresent()) {
                                       roomBooked.get().setStatus(RoomStatus.IN_ACTIVE);
                                   }
                                });


                            }

                        });

                        List<GuestInfoDto> guestInfoDtoList = bookingRequest.getGuestInfos();
                        List<GuestInfoDto> guestInfoDtos = guestInfoDtoList.stream()
                                .map(guestInfoDto -> GuestInfoDto.builder()
                                        .titles(guestInfoDto.getTitles())
                                        .name(guestInfoDto.getName())

                                        .build())
                                .collect(Collectors.toList());

//                        bookingDetailResponse.setBooking(newBooking);
                        return BookingResponse.builder()
                                .code("BK_001")
                                .status(201)
                                // roomDtos guestInfoDtos
                                .data(BookingResponseData.builder()
                                        .rooms(roomDtos)
                                        .guestInfos(guestInfoDtos)
                                        .build()
                                )
                                .message("booking successfully")
                                .responseTime(baseAmenityUtil.currentTimeSeconds())
                                .build();

                    } else {
                        System.out.println("Có id phòng sai");
                    }
                }

            }

        }
         return null;


    }

    @Override
    public BookingResponse getBooking(UUID booking_id) {

        Optional<Booking> booking = bookingRepo.findById(booking_id);

        if (booking.isPresent()) {
            List<GuestInfo> guestInfosBooking = guestInfoRepo.findByBooking_Id(booking_id);

            List<GuestInfoDto> listGuestInfo = guestInfosBooking.stream().map(item -> GuestInfoDto.builder()
                            .titles(item.getTitles())
                            .name(item.getName())
                            .age(item.getAge())
                            .build())
                    .collect(Collectors.toList());


            List<Room> listRooms = roomRepo.findRoomsByBookingId(booking_id);

            BigDecimal total = totalBooking(listRooms, listGuestInfo);

            System.out.println("total -> " + total);

            List<RoomDto> listRoomDtoBooking = listRooms.stream().map(item -> RoomDto.builder()
                    .quantityRoom(item.getQuantityRoom())
                    .childrenQuantity(item.getChildrenQuantity())
                    .price(item.getPrice())
                    .build())
                    .collect(Collectors.toList());

            if (!listRooms.isEmpty()) {
                System.out.println("tim thay roooom");
            }

            return BookingResponse.builder()
                    .code("BK_002")
                    .status(201)
                    // listGuestInfo
//                    .data(listRoomDtoBooking)
                    .data(BookingResponseData.builder()
                            .rooms(listRoomDtoBooking)
                            .guestInfos(listGuestInfo)
                            .build())
                    .message("booking successfully")
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

    return null;
    }

}
