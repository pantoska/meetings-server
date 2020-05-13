package com.ztp.mettings.error.common;

import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

public final class InternalServerErrorProblem extends AbstractThrowableProblem {

    private static final URI type = URI.create("interval-server-error");

    public InternalServerErrorProblem(String message) {
        super(type, "Internal Server Error", INTERNAL_SERVER_ERROR, message, null, null, null);
    }
}

