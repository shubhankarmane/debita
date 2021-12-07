package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class CategoryRequest {
    @NotNull
    @Size(min = 1, max = 150)
    private String title;
    @Size(max = 150)
    private String description;
}
