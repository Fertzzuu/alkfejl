package com.alkfejl.mindenkepp.laugh.game.models;


import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class Player {


    private String name;
    private int color;
    private String id;
    private List<Piece> pieces;

    public Player(String name, int color) {
        this.name = name;
        this.color = color;
        id = UUID.randomUUID().toString();
        generatePieces();
    }

    private void generatePieces() {
        pieces = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece());
        }
    }


    public StepFinishedState step(String choiceID, int amount) {
        Piece selected = pieces.stream().filter(piece -> piece.getId().equals(choiceID)).findFirst().get();
        return selected.isIn() ? StepFinishedState.IN : selected.getPath().step(amount);

    }

    public boolean won() {
        List<Piece> done = pieces.stream().filter(Piece::isIn).collect(Collectors.toList());
        return done.size() == 4;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
}
