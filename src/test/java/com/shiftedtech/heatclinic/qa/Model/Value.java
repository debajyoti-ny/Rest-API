
package com.shiftedtech.heatclinic.qa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("valueKey")
    @Expose
    private String valueKey;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("minValue")
    @Expose
    private Integer minValue;
    @SerializedName("maxValue")
    @Expose
    private Integer maxValue;
    @SerializedName("value")
    @Expose
    private String value;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getValueKey() {
        return valueKey;
    }

    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
