
package com.mustofa.englishessence.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ItemsModel implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productPrice")
    @Expose
    private Integer productPrice;
    @SerializedName("productImage")
    @Expose
    private Integer productImage;



    public ItemsModel() {
    }

    /**
     *  @param id
     * @param productName
     * @param productPrice
     */
    public ItemsModel(Integer id, String productName, Integer productPrice) {
        super();
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductImage() {
        return productImage;
    }

    public void setProductImage(Integer productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "ItemsModel{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}

