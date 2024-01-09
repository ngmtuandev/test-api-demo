package com.bu3.skeleton.controller.internal;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.SendMailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SystemConstant.GROUP_INTERNAL)
public class SampleInternalController {

    @PostMapping(SystemConstant.VERSION_1 + SystemConstant.SEND_MAIL)
    public ResponseEntity sendMail(@RequestBody SendMailRequest request) {
        return null;
    }
}
