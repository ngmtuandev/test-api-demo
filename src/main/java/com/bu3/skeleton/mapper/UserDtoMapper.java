package com.bu3.skeleton.mapper;

import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .isDeleted(user.getIsDeleted())
                .build();
    }
}
