package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransactionRequest {
    private Integer categoryId;
    private Double amount;
    private String note;
}
