package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;
import com.bu3.skeleton.entity.Token;
import com.bu3.skeleton.entity.User;
import com.bu3.skeleton.enums.TokenType;
import com.bu3.skeleton.exception.ResourceDuplicateException;
import com.bu3.skeleton.exception.ResourceNotFoundException;
import com.bu3.skeleton.jwt.IJwtService;
import com.bu3.skeleton.mapper.UserDtoMapper;
import com.bu3.skeleton.repository.ITokenRepo;
import com.bu3.skeleton.repository.IUserRepo;
import com.bu3.skeleton.sevice.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
            throw new ResourceDuplicateException(Translator.toLocale(TransitionCode.EMAIL_DUPLICATE));
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
    public List<UserDto> findAllUser() {
        List<User> users = userRepo.findAll();

        return users.stream()
                .map(userDtoMapper)
                .toList();
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
                .orElseThrow(() -> new ResourceNotFoundException(Translator.toLocale(TransitionCode.USER_FIND_NOT_FOUND)));

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
}
