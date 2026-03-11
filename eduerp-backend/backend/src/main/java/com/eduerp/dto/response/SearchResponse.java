package com.eduerp.dto.response;

public class SearchResponse {

    private Long id;
    private String name;
    private String type;

    public SearchResponse(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
