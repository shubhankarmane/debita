package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryRequest {
    private Integer userId;
    private String title;
    private String description;
}
