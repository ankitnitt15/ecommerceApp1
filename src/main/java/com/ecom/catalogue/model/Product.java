package com.ecom.catalogue.model;

import com.ecom.catalogue.utils.AppConstants;
import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Catalogue")
public class Product {
    @Id
    private long productId;

    @NotNull
    @Size(max=100)
    @Indexed(unique = true)
    private String productName;

    private String description;

    @NotNull
    private String category;

    @NotNull
    private String brand;

    private int minOQ;
    private int maxOQ;

    @NotNull
    private double mrp;

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    @NotNull
    private double sellPrice;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private int stock;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMinOQ() {
        return minOQ;
    }

    public void setMinOQ(int minOQ) {
        this.minOQ = minOQ;
    }

    public int getMaxOQ() {
        return maxOQ;
    }

    public void setMaxOQ(int maxOQ) {
        this.maxOQ = maxOQ;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", minOQ=" + minOQ +
                ", maxOQ=" + maxOQ +
                ", mrp=" + mrp +
                ", sellPrice=" + sellPrice +
                ", stock=" + stock +
                '}';
    }
}
