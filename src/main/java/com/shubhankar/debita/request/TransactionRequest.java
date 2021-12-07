package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class TransactionRequest {
    @NotNull
    private Integer categoryId;
    @NotNull
    @Min(value = 0)
    private Double amount;
    @NotNull
    @Size(min = 1, max = 150)
    private String note;
}
