package com.keypath.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdKeyNote {
    @JsonProperty("id")
    public int Id;

    @JsonProperty("hkey")
    public String Key;

    @JsonProperty("note")
    public String Note;

    @JsonProperty("chord")
    public String Chord;

    @JsonProperty("keyAndNote")
    public String KeyNoteChord;

    @JsonProperty("color")
    public String Color;

    @JsonProperty("labelText")
    public String LabelText;

    public IdKeyNote(int id, String key, String note, String chord, String color) {
        this.Id = id;
        this.Key = key;
        this.Note = note;
        this.Chord = chord;
        this.KeyNoteChord = this.Key + "," + this.Note + "-" + this.Chord;
        this.LabelText = this.Key + "," + this.Note + "-" + this.Chord;
        this.Color = color;
    }
}