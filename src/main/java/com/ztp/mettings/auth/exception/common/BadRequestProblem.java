package com.ztp.mettings.auth.exception.common;

import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static org.zalando.problem.Status.BAD_REQUEST;

public final class BadRequestProblem extends AbstractThrowableProblem {

    private static final URI type = URI.create("bad-request");

    public BadRequestProblem(String message) {
        super(type, "Bad Request", BAD_REQUEST, message);
    }
}

