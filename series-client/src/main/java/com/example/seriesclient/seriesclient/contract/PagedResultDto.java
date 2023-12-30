package com.example.seriesclient.seriesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PagedResultDto {
    private List<SeriesSummaryDto> results;
    @JsonProperty("page")
    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;

    public List<SeriesSummaryDto> getResults() {
        return results;
    }

    public void setResults(List<SeriesSummaryDto> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
