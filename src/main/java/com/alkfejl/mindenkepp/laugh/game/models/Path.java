package com.alkfejl.mindenkepp.laugh.game.models;

import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;

import java.io.Serializable;
import java.util.List;

public class Path implements Serializable {
    private Piece owner;
    private List<Field> fields;

    public Path(List<Field> fields){
        this.fields = fields;
    }

    public StepFinishedState step(int amount){
        Field goal = fields.get(amount);
        int idx = fields.indexOf(goal);
        fields = fields.subList(idx+1, fields.size());
        return goal.accept(owner);
    }

    public int length() {
        return fields.size();
    }
}
