package com.ztp.mettings.auth.exception.common;

import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static org.zalando.problem.Status.FORBIDDEN;

public final class ForbiddenProblem extends AbstractThrowableProblem {

    private static final URI type = URI.create("forbidden");

    public ForbiddenProblem(String message) {
        super(type, "Forbidden", FORBIDDEN, message);
    }
}
