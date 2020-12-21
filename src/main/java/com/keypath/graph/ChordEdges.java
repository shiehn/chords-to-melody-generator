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
            edges = this.createEdgesToSelf(entry.getValue(), edges);

            //collect all keys in one list
            idKeyNotes.addAll(entry.getValue());
        }

        //edges to other
        edges = this.createEdgesToOther(idKeyNotes, edges);

        return edges;
    }

    public List<Link> createEdgesToSelf(List<IdKeyNote> idKeyNotes, List<Link> edges) {
        for (int i = 0; i < idKeyNotes.size(); i++) {
            for (int j = i; j < idKeyNotes.size(); j++) {
                if(idKeyNotes.get(i).Id == idKeyNotes.get(j).Id) {
                    continue;
                }

                edges.add(new Link(idKeyNotes.get(i).Id, idKeyNotes.get(j).Id));
            }
        }

        return edges;
    }

    public List<Link> createEdgesToOther(List<IdKeyNote> idKeyNotes, List<Link> edges) {
        Map<String, String> tempTable = new HashMap<>();

        for (int i = 0; i < idKeyNotes.size(); i++) {
            for (int j = 0; j < idKeyNotes.size(); j++) {
                IdKeyNote keyNoteA = idKeyNotes.get(i);
                IdKeyNote keyNoteB = idKeyNotes.get(j);

                if (tempTable.get(keyNoteA.KeyNoteChord + ":" + keyNoteB.KeyNoteChord) == null &&
                        tempTable.get(keyNoteB.KeyNoteChord + ":" + keyNoteA.KeyNoteChord) == null) {

                    //compare the chord
                    if ((keyNoteA.Note + keyNoteA.Chord).equals(keyNoteB.Note + keyNoteB.Chord)) {
                        //cannot be the same id
                        if(keyNoteA.Id == keyNoteB.Id) {
                            continue;
                        }

                        //cannot exist in reverse order
                        Boolean edgeExists = false;
                        for (Link l : edges) {
                            if(!edgeExists && l.Target == keyNoteA.Id && l.Source == keyNoteB.Id) {
                                edgeExists = true;
                            }

                            if(!edgeExists && l.Source == keyNoteA.Id && l.Target == keyNoteB.Id) {
                                edgeExists = true;
                            }
                        }

                        if(edgeExists) {
                            continue;
                        }

                        edges.add(new Link(keyNoteA.Id, keyNoteB.Id));
                        tempTable.put(keyNoteA.KeyNoteChord + ":" + keyNoteB.KeyNoteChord, "complete");
                    }
                }
            }
        }

        return edges;
    }
}
