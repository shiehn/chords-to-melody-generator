package com.keypath.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import play.mvc.WebSocket;

import java.util.ArrayList;
import java.util.List;

public class RenderData {
    @JsonProperty("nodes")
    public List<IdKeyNote> Nodes = new ArrayList<>();

    @JsonProperty("links")
    public List<Link> Links = new ArrayList<>();

    @JsonProperty("midi")
    public String MidiURL;

    @JsonProperty("origin")
    public Integer Origin;

    @JsonProperty("destination")
    public Integer Destination;

    @JsonProperty("error")
    public String ErrorMessage;

    public RenderData(List<IdKeyNote> nodes, List<Link> links, Integer origin, Integer destination) {
        this.Nodes = nodes;
        this.Links = links;
        this.Origin = origin;
        this.Destination = destination;
    }
}
