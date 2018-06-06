
package com.shiftedtech.heatclinic.qa.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResults {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("product")
    @Expose
    private List<Product> product = null;
    @SerializedName("searchFacet")
    @Expose
    private List<SearchFacet> searchFacet = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<SearchFacet> getSearchFacet() {
        return searchFacet;
    }

    public void setSearchFacet(List<SearchFacet> searchFacet) {
        this.searchFacet = searchFacet;
    }

}
