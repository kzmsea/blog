package com.kzm.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Type {
    private Long id;
    private String name;

    private List<Blog> blogs=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(id, type.id) &&
                Objects.equals(name, type.name) &&
                Objects.equals(blogs, type.blogs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, blogs);
    }
}
