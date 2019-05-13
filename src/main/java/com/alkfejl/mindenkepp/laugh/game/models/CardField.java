package com.alkfejl.mindenkepp.laugh.game.models;

import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;

public class CardField extends Field {
    public CardField() {
        super();
    }

    @Override
    public StepFinishedState accept(Piece piece) {
        super.accept(piece);
        return StepFinishedState.CARD;
    }
}
