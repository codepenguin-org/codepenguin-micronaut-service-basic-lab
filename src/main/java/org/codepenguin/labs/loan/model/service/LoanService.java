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

package org.codepenguin.labs.loan.model.service;

import io.micronaut.context.annotation.Bean;
import org.apache.poi.ss.formula.functions.Finance;
import org.codepenguin.labs.loan.model.domain.LoanRequest;
import org.codepenguin.labs.loan.model.domain.LoanResponse;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.RoundingMode.HALF_UP;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.codepenguin.labs.loan.model.domain.ResponseStatus.ERROR;
import static org.codepenguin.labs.loan.model.domain.ResponseStatus.OK;

/**
 * Business service for loans.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@Bean
public class LoanService {

    private static final int INTEGER_ONE_HUNDRED = 100;

    /**
     * Calculates the monthly installment value for a loan.
     *
     * @param request the loan request.
     * @return the response with the error validations or the monthly installment value.
     */
    public LoanResponse calculate(final LoanRequest request) {
        return validate(request).orElseGet(() -> new LoanResponse(OK, "Successful", request,
                -BigDecimal.valueOf(Finance.pmt(request.monthlyEffectiveRate() / INTEGER_ONE_HUNDRED,
                        request.monthlyInstallments(), request.value())).setScale(INTEGER_TWO, HALF_UP).doubleValue()));
    }


    private Optional<LoanResponse> validate(final LoanRequest request) {
        if (request.value() < INTEGER_ZERO) {
            return Optional.of(buildErrorResponse("Value must be greater than 0"));
        }

        if (request.monthlyInstallments() < INTEGER_ZERO) {
            return Optional.of(buildErrorResponse("Monthly Installments must be greater than 0"));
        }

        if (request.monthlyEffectiveRate() < INTEGER_ZERO || request.monthlyEffectiveRate() > INTEGER_ONE_HUNDRED) {
            return Optional.of(buildErrorResponse("Monthly Effective Rate must be between 0 and 100"));
        }

        return Optional.empty();
    }

    private LoanResponse buildErrorResponse(final String message) {
        return new LoanResponse(ERROR, message, null, null);
    }
}
