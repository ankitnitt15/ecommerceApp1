package com.ecom.catalogue.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Category")
public class Category implements Serializable {

    @Id
    private String categoryId;

    @NotNull
    private String name;

    @NotNull
    private int level;

    @NotNull
    private String l2CategoryId;

    @NotNull
    private String l2CategoryName;

    @NotNull
    private String l3CategoryId;

    @NotNull
    private String l3CategoryName;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getL2CategoryId() {
        return l2CategoryId;
    }

    public void setL2CategoryId(String l2CategoryId) {
        this.l2CategoryId = l2CategoryId;
    }

    public String getL2CategoryName() {
        return l2CategoryName;
    }

    public void setL2CategoryName(String l2CategoryName) {
        this.l2CategoryName = l2CategoryName;
    }

    public String getL3CategoryId() {
        return l3CategoryId;
    }

    public void setL3CategoryId(String l3CategoryId) {
        this.l3CategoryId = l3CategoryId;
    }

    public String getL3CategoryName() {
        return l3CategoryName;
    }

    public void setL3CategoryName(String l3CategoryName) {
        this.l3CategoryName = l3CategoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", l2CategoryId='" + l2CategoryId + '\'' +
                ", l2CategoryName='" + l2CategoryName + '\'' +
                ", l3CategoryId='" + l3CategoryId + '\'' +
                ", l3CategoryName='" + l3CategoryName + '\'' +
                '}';
    }
}
