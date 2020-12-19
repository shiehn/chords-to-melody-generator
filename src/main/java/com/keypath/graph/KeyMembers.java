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
        String key = "a";
        String color = "green";
        nodes.add(new IdKeyNote(this.incrementId(), key, "a", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "b", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "e", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f3", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g#", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // A SHARP
        nodes = new ArrayList<>();
        key = "a#";
        color = "pink";

        nodes.add(new IdKeyNote(this.incrementId(), key, "a#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // B NATURAL
        nodes = new ArrayList<>();
        key = "b";
        color = "yellow";

        nodes.add(new IdKeyNote(this.incrementId(), key, "b", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "e", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f#", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a#", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // C NATURAL
        nodes = new ArrayList<>();
        key = "c";
        color = "orange";

        nodes.add(new IdKeyNote(this.incrementId(), key, "c", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "e", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "b", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // C SHARP
        nodes = new ArrayList<>();
        key = "c#";
        color = "black";

        nodes.add(new IdKeyNote(this.incrementId(), key, "c#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g#", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // D NATURAL
        nodes = new ArrayList<>();
        key = "d";
        color = "red";

        nodes.add(new IdKeyNote(this.incrementId(), key, "d", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "e", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "b", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "cs", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // D SHARP
        nodes = new ArrayList<>();
        key = "d#";
        color = "maroon";

        nodes.add(new IdKeyNote(this.incrementId(), key, "d#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a#", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // E NATURAL
        nodes = new ArrayList<>();
        key = "e";
        color = "teal";

        nodes.add(new IdKeyNote(this.incrementId(), key, "e", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "b", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d#", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // F NATURAL
        nodes = new ArrayList<>();
        key = "f";
        color = "brown";

        nodes.add(new IdKeyNote(this.incrementId(), key, "f", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "e", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // F SHARP
        nodes = new ArrayList<>();
        key = "f#";
        color = "purple";

        nodes.add(new IdKeyNote(this.incrementId(), key, "f#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "b", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c#", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "e#", "dim", color));

        this.keyNoteMap.put(key, nodes);

        // G NATURAL
        nodes = new ArrayList<>();
        key = "g";
        color = "lime";

        nodes.add(new IdKeyNote(this.incrementId(), key, "g", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "b", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "e", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f#", "dim", color));

        this.keyNoteMap.put(key, nodes);


        // G SHARP
        nodes = new ArrayList<>();
        key = "g#";
        color = "blue";

        nodes.add(new IdKeyNote(this.incrementId(), key, "g#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "a#", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "c#", "maj7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "d#", "dom7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "f", "min7", color));
        nodes.add(new IdKeyNote(this.incrementId(), key, "g", "dim", color));

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
