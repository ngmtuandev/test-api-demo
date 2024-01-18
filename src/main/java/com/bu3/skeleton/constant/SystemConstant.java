package com.bu3.skeleton.constant;

public class SystemConstant {

    private SystemConstant() {
    }

    public static final Integer STATUS_CODE_SUCCESS = 200;
    public static final Integer STATUS_CODE_BAD_REQUEST = 400;
    public static final Integer STATUS_CODE_INTERNAL = 500;

    public static final String API_SKELETON = "/skeleton";

    public static final String API_USER_LOGOUT = "/logout";

    public static final String API_PUBLIC = "/public";

    public static final String API_ADMIN = "/admin";
    public static final String VERSION_1 = "/v1";
    public static final String GROUP_INTERNAL = "/internal";
    public static final String SEND_MAIL = "/send-mail";

    public static final Boolean ACTIVE = false;
    public static final Boolean NO_ACTIVE = true;
    // JwtFilter
    public static final String BEARER = "Bearer ";

    public static final String AUTHORIZATION = "Authorization";
}
