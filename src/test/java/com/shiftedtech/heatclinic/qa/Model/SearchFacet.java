
package com.shiftedtech.heatclinic.qa.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchFacet {

    @SerializedName("fieldName")
    @Expose
    private String fieldName;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("values")
    @Expose
    private List<Value> values = null;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

}
