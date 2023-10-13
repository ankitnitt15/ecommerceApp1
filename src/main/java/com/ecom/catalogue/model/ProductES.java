package com.ecom.catalogue.model;

import com.ecom.catalogue.utils.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(indexName = AppConstants.ES_INDEX)
public class ProductES {
    @Id
    private String productId;

    @Field(type= FieldType.Text, name="productName")
    private String productName;

    @Field(type= FieldType.Text, name="description")
    private String description;

    @Field(type= FieldType.Text, name="category")
    private String category;

    @Field(type= FieldType.Text, name="brand")
    private String brand;

    @Field(type= FieldType.Integer, name="minOQ")
    private int minOQ;

    @Field(type= FieldType.Integer, name="maxOQ")
    private int maxOQ;

    @Field(type= FieldType.Double, name="mrp")
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

    @Field(type= FieldType.Double, name="sellPrice")
    private double sellPrice;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Field(type= FieldType.Integer, name="stock")
    private int stock;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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
