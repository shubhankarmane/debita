package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class TransactionRequest {
    @NotNull
    private Integer categoryId;
    @NotNull
    private Double amount;
    @NotNull
    private String note;
}
