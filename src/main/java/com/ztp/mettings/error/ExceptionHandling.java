package com.ztp.mettings.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;


@Slf4j
@ControllerAdvice
final class ExceptionHandling implements ProblemHandling {

    @Override
    public ResponseEntity<Problem> handleProblem(ThrowableProblem problem, NativeWebRequest request) {
        HttpStatus status = getStatus(problem);

        log.warn("Problem: ", problem);
        return new ResponseEntity<>(problem, status);
    }

    @Override
    public boolean isCausalChainsEnabled() {
        return true;
    }

    private HttpStatus getStatus(ThrowableProblem throwableProblem) {
        final int status = throwableProblem.getStatus().getStatusCode();
        return HttpStatus.valueOf(status);
    }
}
