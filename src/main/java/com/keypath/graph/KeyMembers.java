package com.keypath.graph;

import com.keypath.server.model.IdKeyNote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyMembers {
    private Map<String, List<IdKeyNote>> keyNoteMap = new HashMap<>();
    private List<IdKeyNote> keyNotes = new ArrayList<>();
    private int Id;

    public KeyMembers() {
        this.Id = -1;
        this.populateKeyNoteMap();
    }

    public Map<String, List<IdKeyNote>> getKeyNoteMap() {
        return this.keyNoteMap;
    }

    private int incrementId() {
        this.Id = this.Id + 1;
        return this.Id;
    }

    private void populateKeyNoteMap() {

        // A NATURAL
        List<IdKeyNote> nodes = new ArrayList<>();
        String key = "an";
        String color = "green";
        nodes.add(new IdKeyNote(this.incrementId(), key, "an", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "bn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "dn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "en", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gs", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // A SHARP
        nodes = new ArrayList<>();
        key = "as";
        color = "pink";

        nodes.add(new IdKeyNote(this.incrementId(), key, "as", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "ds", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fn", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "an", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // B NATURAL
        nodes = new ArrayList<>();
        key = "bn";
        color = "yellow";

        nodes.add(new IdKeyNote(this.incrementId(), key, "bn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "ds", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "en", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fs", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "as", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // C NATURAL
        nodes = new ArrayList<>();
        key = "cn";
        color = "orange";

        nodes.add(new IdKeyNote(this.incrementId(), key, "cn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "ds", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "en", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gn", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "an", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "bn", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // C SHARP
        nodes = new ArrayList<>();
        key = "cs";
        color = "black";

        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "ds", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fs", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gs", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "as", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cn", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // D NATURAL
        nodes = new ArrayList<>();
        key = "dn";
        color = "red";

        nodes.add(new IdKeyNote(this.incrementId(), key, "dn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "en", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "an", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "bn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // D SHARP
        nodes = new ArrayList<>();
        key = "ds";
        color = "maroon";

        nodes.add(new IdKeyNote(this.incrementId(), key, "ds", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gs", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "as", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "dn", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // E NATURAL
        nodes = new ArrayList<>();
        key = "en";
        color = "teal";

        nodes.add(new IdKeyNote(this.incrementId(), key, "en", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "an", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "bn", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "ds", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // F NATURAL
        nodes = new ArrayList<>();
        key = "fn";
        color = "brown";

        nodes.add(new IdKeyNote(this.incrementId(), key, "fn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "an", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "as", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cn", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "dn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "en", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // F SHARP
        nodes = new ArrayList<>();
        key = "fs";
        color = "purple";

        nodes.add(new IdKeyNote(this.incrementId(), key, "fs", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gs", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "as", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "bn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "ds", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "es", "m7b5", color));

        this.keyNoteMap.put(key, nodes);

        // G NATURAL
        nodes = new ArrayList<>();
        key = "gn";
        color = "lime";

        nodes.add(new IdKeyNote(this.incrementId(), key, "gn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "an", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "bn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cn", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "dn", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "en", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fs", "m7b5", color));

        this.keyNoteMap.put(key, nodes);


        // G SHARP
        nodes = new ArrayList<>();
        key = "gs";
        color = "blue";

        nodes.add(new IdKeyNote(this.incrementId(), key, "gs", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "as", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "ds", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "fn", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "gn", "m7b5", color));

        this.keyNoteMap.put(key, nodes);


        List<IdKeyNote> allKeys = new ArrayList<>();
        for (Map.Entry<String, List<IdKeyNote>> entry : this.keyNoteMap.entrySet()) {
            allKeys.addAll(entry.getValue());
        }
        this.keyNotes = allKeys;
    }

    public List<IdKeyNote> getByKey(String key) {
        return this.keyNoteMap.get(key);
    }

    public IdKeyNote getById(int id) {
        return this.keyNotes.stream().filter(kn -> kn.Id == id).findFirst().orElse(null);
    }

    public List<IdKeyNote> getAllKeys() {
        return this.keyNotes;
    }
}
