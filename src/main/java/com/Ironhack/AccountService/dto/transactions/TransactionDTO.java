package com.Ironhack.AccountService.dto.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    protected Long id;

    protected LocalDateTime transactionDate;

    protected BigDecimal transactionAmount;

    @NotNull
    protected Long transferAccountId;

    @NotNull
    protected Long receivingAccountId;

    public TransactionDTO(BigDecimal transactionAmount, Long transferAccountId, Long receivingAccountId) {
        this.transactionDate = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        this.transactionAmount = transactionAmount;
        this.transferAccountId = transferAccountId;
        this.receivingAccountId = receivingAccountId;
    }

}
