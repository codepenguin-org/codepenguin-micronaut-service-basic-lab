/*
 * Copyright (C) 2022  CodePenguin.org - Jorge Garcia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.codepenguin.labs.loan.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.codepenguin.labs.loan.model.domain.LoanRequest;
import org.codepenguin.labs.loan.model.domain.LoanResponse;
import org.codepenguin.labs.loan.model.service.LoanService;

import java.util.Objects;

import static org.codepenguin.labs.loan.model.domain.ResponseStatus.OK;

/**
 * REST controller for loans.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@Slf4j
@Controller("/loans")
public class LoanController {

    @Inject
    private LoanService loanService;

    /**
     * Calculates the monthly installment value for a loan.
     *
     * @param request the loan request.
     * @return the loan response.
     */
    @Get(value = "/calculate", consumes = "application/json", produces = "application/json")
    public HttpResponse<LoanResponse> calculate(@Body LoanRequest request) {
        log.info(Objects.toString(request));

        final var response = loanService.calculate(request);
        log.info(Objects.toString(response));

        return response.status().equals(OK) ? HttpResponse.ok(response) : HttpResponse.badRequest(response);
    }

}