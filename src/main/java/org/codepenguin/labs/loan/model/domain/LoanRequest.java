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

package org.codepenguin.labs.loan.model.domain;

import java.io.Serial;
import java.io.Serializable;

/**
 * Loan request.
 *
 * @param value                the value.
 * @param monthlyInstallments  the number of monthly installments.
 * @param monthlyEffectiveRate the monthly effective rate.
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
public record LoanRequest(double value, int monthlyInstallments, double monthlyEffectiveRate) implements Serializable {

    @Serial
    private static final long serialVersionUID = -8223544747410035044L;
}
