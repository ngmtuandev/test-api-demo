package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;
import com.bu3.skeleton.dto.response.UsersResponse;
import com.bu3.skeleton.entity.Token;
import com.bu3.skeleton.entity.User;
import com.bu3.skeleton.enums.TokenType;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.jwt.IJwtService;
import com.bu3.skeleton.mapper.UserDtoMapper;
import com.bu3.skeleton.repository.ITokenRepo;
import com.bu3.skeleton.repository.IUserRepo;
import com.bu3.skeleton.sevice.IUserService;
import com.bu3.skeleton.util.PageableResponse;
import com.bu3.skeleton.util.TimeUnitResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public UsersResponse findAllUser(Integer currentPage, Integer limitPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, limitPage);
        List<User> users = userRepo.findAll();

        List<UserDto> userDtos = users.stream()
                .map(userDtoMapper)
                .toList();

        PageableResponse pageableResponse = PageableResponse.builder()
                .totalPage(getTotalPage(userDtos.size(), limitPage))
                .meta(pageable)
                .build();

        return UsersResponse.builder()
                .code(Translator.toLocale(TransitionCode.USER_CODE))
                .status(200)
                .data(userDtos)
                .meta(pageableResponse)
                .message(Translator.toLocale(TransitionCode.USER_SUCCESS))
                .responseTime(TimeUnitResponse.currentTimeSeconds())
                .build();
    }

    @Override
    public UserDto authenticated(UserLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepo.findUserByEmailAndStatus(request.getEmail(), SystemConstant.USER_ACTIVE)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.USER_CODE),
                        Translator.toLocale(TransitionCode.USER_FIND_NOT_FOUND)));

        UserDto userDto = userDtoMapper.apply(user);
        var jwtToken = jwtService.generateToken(user);
        userDto.setJwtToken(jwtToken);
        saveUserToken(user, jwtToken);
        return userDto;
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

    private int getTotalPage(int size, int limit) {
        return (int) Math.ceil((double) size / limit);
    }
}
