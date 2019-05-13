package com.alkfejl.mindenkepp.laugh.game.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Piece implements Serializable {

    private String id;
    private Path path;

    public Piece() {
        id = UUID.randomUUID().toString();
    }

    public void generatePath(List<Field> board, int offset) {
        List<Field> fieldsOfPath = new ArrayList<>();
        for (int i = offset; i < board.size(); i++)
            fieldsOfPath.add(board.get(i % board.size()));
        path = new Path(fieldsOfPath);
    }

    public boolean isIn() {
        return path.length() == 0;
    }

    public String getId() {
        return id;
    }


    public Path getPath() {
        return path;
    }
}
