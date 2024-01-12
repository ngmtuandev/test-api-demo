package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.repository.IUserRepo;
import com.bu3.skeleton.sevice.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepo userRepo;


}
