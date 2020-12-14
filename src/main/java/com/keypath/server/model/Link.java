package com.keypath.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {
    @JsonProperty("source")
    public int Source;

    @JsonProperty("target")
    public int Target;

    @JsonProperty("color")
    public String Color = "grey";

    @JsonProperty("strokeWidth")
    public double StrokeWidth = 0.5;

    @JsonProperty("opacity")
    public double Opacity = 0.5;

    public Link(int source, int target) {
        this.Source = source;
        this.Target = target;

    }

    public Link setIsPath() {
        this.StrokeWidth = 4;
        this.Color = "black";
        this.Opacity = 1;

        return this;
    }
}
