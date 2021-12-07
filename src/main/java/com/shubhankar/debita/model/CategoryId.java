package com.shubhankar.debita.model;

import java.io.Serializable;
import java.util.Objects;

public class CategoryId implements Serializable {
    private Integer id;
    private String title;

    public CategoryId() {
    }

    public CategoryId(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryId that = (CategoryId) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
