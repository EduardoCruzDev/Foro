package com.eduardocruzdev.foro.infra.security;

public class AccesRules {

    protected static final String[] FOR_EVERYONE = {
            "/hello",
            "/login",
            "/error",
            "/users/**"
    };

    protected static final String[] FOR_AUTHORIZED_USERS = {
            "/user/**",
            "/topics/new/**",
            "/topics/delete/**",
            "/section/delete/**",
            "/section/new/**",
            "/post/**",
            "/myprofile/**"
    };

    protected static final String[] FOR_ADMINS = {
            "/admin/**",
            "/a/**",
            "/users/**",
            "/section/new"
    };

    protected static final String[] ADMINS_ROLES = {
            "HEAD_ADMIN",
            "ADMIN"
    };
}
