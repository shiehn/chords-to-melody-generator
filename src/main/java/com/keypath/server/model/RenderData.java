package com.keypath.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RenderData {
    @JsonProperty("nodes")
    public List<IdKeyNote> Nodes = new ArrayList<>();

    @JsonProperty("links")
    public List<Link> Links = new ArrayList<>();

    @JsonProperty("midi")
    public String MidiURL;

    @JsonProperty("error")
    public String ErrorMessage;

    public RenderData(List<IdKeyNote> nodes, List<Link> links) {
        this.Nodes = nodes;
        this.Links = links;
    }
}
