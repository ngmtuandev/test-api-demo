package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;
import com.bu3.skeleton.dto.request.UserUpdateRequest;
import com.bu3.skeleton.dto.response.UserResponse;
import com.bu3.skeleton.dto.response.UserResponses;
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
import com.bu3.skeleton.util.BaseAmenity;
import com.bu3.skeleton.util.PageableResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    private final BaseAmenity baseAmenity;

    private final IRoleRepo roleRepo;

    @Override
    public void addUser(UserAddRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ApiRequestException(Translator.toLocale(TransitionCode.USER_CODE),
                    Translator.toLocale(TransitionCode.EMAIL_DUPLICATE));
        }

        userRepo.save(
                User.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .gender(request.getGender())
                        .dateOfBirth(request.getDateOfBirth())
                        .fullName(request.getFullName())
                        .phoneNumber(request.getPhoneNumber())
                        .address(request.getAddress())
                        .status(SystemConstant.USER_ACTIVE)
                        .build()
        );
    }

    @Override
    public void updateUser(UserUpdateRequest request) {
        User user = getUser(request.getEmail());

        if (!passwordEncoder.matches(user.getPassword(), request.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

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

        if (!user.getStatus().equals(request.getStatus())) {
            user.setStatus(request.getStatus());
        }

        userRepo.save(user);
    }

    private User getUser(String email) {
        return userRepo.findUserByEmail(email)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.USER_CODE), Translator.toLocale(TransitionCode.USER_FIND_NOT_FOUND)));
    }

    @Override
    public void deleteUser(String email) {
        User user = getUser(email);
        user.setStatus(SystemConstant.USER_NO_ACTIVE);
        userRepo.save(user);
    }


    @Override
    public UserResponses findAllUser(Integer currentPage, Integer limitPage) {
        Page<User> all = userRepo.findAll(baseAmenity.pageable(currentPage, limitPage));
        List<UserDto> userDtos = all.stream()
                .map(userDtoMapper)
                .toList();

        PageableResponse pageableResponse = baseAmenity.pageableResponse(currentPage, limitPage, all.getTotalPages());

        return UserResponses.builder()
                .code(Translator.toLocale(TransitionCode.USER_CODE))
                .status(200)
                .data(userDtos)
                .meta(pageableResponse)
                .message(Translator.toLocale(TransitionCode.USER_SUCCESS))
                .responseTime(baseAmenity.currentTimeSeconds())
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

        User user = userRepo.findUserByEmailAndStatus(request.getEmail(), SystemConstant.USER_ACTIVE)
                .orElseThrow(() -> new ApiRequestException(baseAmenity.getMessageNotification(TransitionCode.USER_CODE),
                        baseAmenity.getMessageNotification(TransitionCode.NOT_FOUND)));
        UserDto userDto = userDtoMapper.apply(user);
        var jwtToken = jwtService.generateToken(user);
        userDto.setJwtToken(jwtToken);
        saveUserToken(user, jwtToken);

        return UserResponse.builder()
                .code(Translator.toLocale(TransitionCode.USER_CODE))
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userDto)
                .message(baseAmenity.getMessageNotification(TransitionCode.USER_SUCCESS))
                .responseTime(baseAmenity.currentTimeSeconds())
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
