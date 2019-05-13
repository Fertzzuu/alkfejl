package com.alkfejl.mindenkepp.laugh.game.models;

import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Field implements Serializable {


    public List<Piece> pieces;

    public Field() {
        pieces = new ArrayList<>();
    }

    public StepFinishedState accept(Piece piece) {
        if (pieces.isEmpty())
            pieces.add(piece);
        else {
            if (pieces.get(0).getId().equals(piece.getId()))
                pieces.add(piece);
            else {
                for (Piece p :
                        pieces) {
                    p.setCurrent(null);
                }
                pieces.clear();
                pieces.add(piece);
            }
        }
        return StepFinishedState.NONE;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

}
