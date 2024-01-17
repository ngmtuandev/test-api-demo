package com.bu3.skeleton.dto.response;

import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.util.BasesResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class UsersResponse extends BasesResponse<UserDto> {
}
