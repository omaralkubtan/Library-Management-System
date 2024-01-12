package com.legacy.lms.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ApiController {

    @Autowired
    protected ModelMapper modelMapper;

    protected static final String AUTH_PREFIX = "hasAnyAuthority(\"";
    protected static final String AUTH_SUFFIX = "\")";
    protected static final String AUTH_DIVIDER = ",";
}
