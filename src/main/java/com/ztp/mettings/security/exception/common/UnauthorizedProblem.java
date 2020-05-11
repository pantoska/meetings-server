package com.ztp.mettings.security.exception.common;

import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static org.zalando.problem.Status.UNAUTHORIZED;

public final class UnauthorizedProblem extends AbstractThrowableProblem {

    private static final URI type = URI.create("unauthorized");

    public UnauthorizedProblem(String message) {
        super(type, "Unauthorized", UNAUTHORIZED, message);
    }
}
