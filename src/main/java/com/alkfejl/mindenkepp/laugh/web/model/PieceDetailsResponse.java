package com.alkfejl.mindenkepp.laugh.web.model;

import java.util.ArrayList;
import java.util.List;

public class PieceDetailsResponse {
    private int color;
    private List<String> pieceIDs;
    private List<String> fieldIDs;

    public PieceDetailsResponse() {
        pieceIDs = new ArrayList<>();
        fieldIDs = new ArrayList<>();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public List<String> getPieceIDs() {
        return pieceIDs;
    }

    public void setPieceIDs(List<String> pieceIDs) {
        this.pieceIDs = pieceIDs;
    }

    public List<String> getFieldIDs() {
        return fieldIDs;
    }

    public void setFieldIDs(List<String> fieldIDs) {
        this.fieldIDs = fieldIDs;
    }
}
