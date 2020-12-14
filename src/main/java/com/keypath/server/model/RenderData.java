package com.keypath.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RenderData {
    @JsonProperty("nodes")
    public List<IdKeyNote> Nodes = new ArrayList<>();

    @JsonProperty("links")
    public List<Link> Links = new ArrayList<>();

    public RenderData(List<IdKeyNote> nodes, List<Link> links) {
        this.Nodes = nodes;
        this.Links = links;
    }
}
