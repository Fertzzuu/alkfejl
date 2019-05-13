package com.alkfejl.mindenkepp.laugh.game.models;

public class Card {
    private int id;
    private String text;

    public Card(String text, int id) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
