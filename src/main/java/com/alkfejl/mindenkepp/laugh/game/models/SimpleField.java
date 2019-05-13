package com.alkfejl.mindenkepp.laugh.game.models;

import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;

public class SimpleField extends Field {

    public SimpleField() {
        super();
    }

    @Override
    public StepFinishedState accept(Piece piece) {
        super.accept(piece);
        return StepFinishedState.SIMPLE;
    }
}
