package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class CategoryRequest {
    @NotNull
    private String title;
    private String description;
}
