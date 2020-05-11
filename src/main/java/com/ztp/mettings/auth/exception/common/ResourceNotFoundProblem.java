package com.ztp.mettings.auth.exception.common;

import org.springframework.util.StringUtils;
import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;
import java.util.Map;

import static java.lang.String.format;
import static org.zalando.problem.Status.NOT_FOUND;

public class ResourceNotFoundProblem extends AbstractThrowableProblem {

    private static final URI type = URI.create("not-found");

    public ResourceNotFoundProblem(String resourceName, String resourceFieldName, String resourceId) {
        super(
                type,
                format("%s Not Found", StringUtils.capitalize(resourceName)),
                NOT_FOUND,
                format("Resource %s with %s %s not found", resourceName, resourceFieldName, resourceId),
                null,
                null,
                Map.of(resourceFieldName, resourceId));
    }
}
