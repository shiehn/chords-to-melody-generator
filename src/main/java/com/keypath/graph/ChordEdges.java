package com.keypath.graph;

import com.keypath.server.model.IdKeyNote;
import com.keypath.server.model.Link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChordEdges {
    public List<Link> createEdges(Map<String, List<IdKeyNote>> keyNoteMap) {
        List<Link> edges = new ArrayList<>();
        List<IdKeyNote> idKeyNotes = new ArrayList<>();

        for (Map.Entry<String, List<IdKeyNote>> entry : keyNoteMap.entrySet()) {
            //edges to self
            List<Link> links = this.createEdgesToSelf(entry.getValue());
            edges.addAll(links);

            //collect all keys in one list
            idKeyNotes.addAll(entry.getValue());
        }

        //edges to other
        edges.addAll(this.createEdgesToOther(idKeyNotes));

        return edges;
    }

    public List<Link> createEdgesToSelf(List<IdKeyNote> idKeyNotes) {
        List<Link> edges = new ArrayList<>();

        for (int i = 0; i < idKeyNotes.size(); i++) {
            for (int j = i; j < idKeyNotes.size(); j++) {
                edges.add(new Link(idKeyNotes.get(i).Id, idKeyNotes.get(j).Id));
            }
        }

        return edges;
    }

    public List<Link> createEdgesToOther(List<IdKeyNote> idKeyNotes) {
        List<Link> edges = new ArrayList<>();
        Map<String, String> tempTable = new HashMap<>();

        for (int i = 0; i < idKeyNotes.size(); i++) {
            for (int j = 0; j < idKeyNotes.size(); j++) {
                IdKeyNote keyNoteA = idKeyNotes.get(i);
                IdKeyNote keyNoteB = idKeyNotes.get(j);

                if (tempTable.get(keyNoteA.KeyNoteChord + ":" + keyNoteB.KeyNoteChord) == null &&
                        tempTable.get(keyNoteB.KeyNoteChord + ":" + keyNoteA.KeyNoteChord) == null) {

                    //compare the chord
                    if ((keyNoteA.Note + keyNoteA.Chord).equals(keyNoteB.Note + keyNoteB.Chord)) {
                        edges.add(new Link(keyNoteA.Id, keyNoteB.Id));
                        tempTable.put(keyNoteA.KeyNoteChord + ":" + keyNoteB.KeyNoteChord, "complete");
                    }
                }
            }
        }

        return edges;
    }
}
