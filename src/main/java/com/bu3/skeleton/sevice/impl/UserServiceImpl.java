package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.constant.ResourceBundleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.dto.request.user.UserAddRequest;
import com.bu3.skeleton.dto.request.user.UserLoginRequest;
import com.bu3.skeleton.dto.request.user.UserUpdateRequest;
import com.bu3.skeleton.dto.response.PageableResponse;
import com.bu3.skeleton.dto.response.user.UserResponse;
import com.bu3.skeleton.dto.response.user.UserResponses;
import com.bu3.skeleton.entity.Token;
import com.bu3.skeleton.entity.User;
import com.bu3.skeleton.enums.TokenType;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.jwt.IJwtService;
import com.bu3.skeleton.mapper.UserDtoMapper;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.repository.ITokenRepo;
import com.bu3.skeleton.repository.IUserRepo;
import com.bu3.skeleton.sevice.IUserService;
import com.bu3.skeleton.util.BaseAmenityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final UserDtoMapper userDtoMapper;

    private final AuthenticationManager authenticationManager;

    private final IJwtService jwtService;

    private final ITokenRepo tokenRepo;

    private final IRoleRepo roleRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public UserResponse addUser(UserAddRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ApiRequestException(ResourceBundleConstant.USR_002, getMessageBundle(ResourceBundleConstant.USR_002));
        }

        User userSave = userRepo.save(
                User.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .gender(request.getGender())
                        .dateOfBirth(request.getDateOfBirth())
                        .fullName(request.getFullName())
                        .phoneNumber(request.getPhoneNumber())
                        .address(request.getAddress())
                        .isDeleted(SystemConstant.ACTIVE)
                        .build()
        );

        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_005)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userDtoMapper.apply(userSave))
                .message(getMessageBundle(ResourceBundleConstant.USR_005))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest request) {
        User user = getUser(request.getEmail());

        if (!user.getGender().equals(request.getGender())) {
            user.setGender(request.getGender());
        }

        if (!user.getDateOfBirth().equals(request.getDateOfBirth())) {
            user.setDateOfBirth(request.getDateOfBirth());
        }

        if (!user.getFullName().equals(request.getFullName())) {
            user.setFullName(request.getFullName());
        }

        if (!user.getPhoneNumber().equals(request.getPhoneNumber())) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        if (!user.getAddress().equals(request.getAddress())) {
            user.setAddress(request.getAddress());
        }

        User userSave = userRepo.save(user);

        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_003)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userDtoMapper.apply(userSave))
                .message(getMessageBundle(ResourceBundleConstant.USR_003))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    private User getUser(String email) {
        return userRepo.findUserByEmail(email)
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.USR_002, getMessageBundle(ResourceBundleConstant.USR_002)));
    }

    @Override
    public UserResponse deleteUser(String email) {
        User user = getUser(email);
        user.setIsDeleted(SystemConstant.NO_ACTIVE);
        User userSave = userRepo.save(user);

        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_007)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userDtoMapper.apply(userSave))
                .message(getMessageBundle(ResourceBundleConstant.USR_007))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }


    @Override
    public UserResponses findAllUser(Integer currentPage, Integer limitPage) {
        Page<User> all = userRepo.findAll(baseAmenityUtil.pageable(currentPage, limitPage));
        List<UserDto> userDtos = all.stream()
                .map(userDtoMapper)
                .toList();

        PageableResponse pageableResponse = baseAmenityUtil.pageableResponse(currentPage, limitPage, all.getTotalPages());

        return UserResponses.builder()
                .code(ResourceBundleConstant.USR_009)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userDtos)
                .meta(pageableResponse)
                .message(getMessageBundle(ResourceBundleConstant.USR_009))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponses findUsersByIsDeleted(Integer currentPage, Integer limitPage, Boolean isDeleted) {
        Pageable pageable = baseAmenityUtil.pageable(currentPage, limitPage);
        Page<User> users = userRepo.findUsersByIsDeleted(isDeleted, pageable);

        List<UserDto> userDtos = users.stream()
                .map(userDtoMapper)
                .toList();

        return UserResponses.builder()
                .code(ResourceBundleConstant.USR_009)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userDtos)
                .meta(baseAmenityUtil.pageableResponse(currentPage, limitPage, users.getTotalPages()))
                .message(getMessageBundle(ResourceBundleConstant.USR_009))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse authenticated(UserLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepo.findUserByEmailAndIsDeleted(request.getEmail(), SystemConstant.ACTIVE)
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.UD_002, getMessageBundle(ResourceBundleConstant.UD_002)));
        UserDto userDto = userDtoMapper.apply(user);
        var jwtToken = jwtService.generateToken(user);
        userDto.setJwtToken(jwtToken);
        saveUserToken(user, jwtToken);

        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_011)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userDto)
                .message(getMessageBundle(ResourceBundleConstant.USR_011))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .tokenType(TokenType.BEARER)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }
}
