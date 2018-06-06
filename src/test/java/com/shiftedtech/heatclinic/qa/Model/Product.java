
package com.shiftedtech.heatclinic.qa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("longDescription")
    @Expose
    private String longDescription;
    @SerializedName("retailPrice")
    @Expose
    private RetailPrice retailPrice;
    @SerializedName("primaryMedia")
    @Expose
    private PrimaryMedia primaryMedia;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("salePrice")
    @Expose
    private SalePrice salePrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public RetailPrice getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(RetailPrice retailPrice) {
        this.retailPrice = retailPrice;
    }

    public PrimaryMedia getPrimaryMedia() {
        return primaryMedia;
    }

    public void setPrimaryMedia(PrimaryMedia primaryMedia) {
        this.primaryMedia = primaryMedia;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public SalePrice getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(SalePrice salePrice) {
        this.salePrice = salePrice;
    }

}
