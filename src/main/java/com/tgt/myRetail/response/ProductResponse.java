package com.tgt.myRetail.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {

    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;
    @JsonProperty("current_price")
    ProductPricing productPricing;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductPricing getProductPricing() {
        return productPricing;
    }

    public void setProductPricing(ProductPricing productPricing) {
        this.productPricing = productPricing;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", productPricing=" + productPricing +
                '}';
    }
}
