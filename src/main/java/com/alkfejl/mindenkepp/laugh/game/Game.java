package com.alkfejl.mindenkepp.laugh.game;


import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;
import com.alkfejl.mindenkepp.laugh.game.models.*;

import java.io.*;
import java.util.*;

public class Game {

    private static List<String> cards;
    private boolean isWon;

    static {
        try {
            File file = new File("/home/qwuaks/IdeaProjects/laugh/src/main/java/com/alkfejl/mindenkepp/laugh/game/resources/cards.data");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String cardText;
            cards = new ArrayList<>();
            while ((cardText = br.readLine()) != null) {
                cards.add(cardText);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Player> players;
    private List<Field> board;
    private int currentPlayerIdx;
    private String gameId;
    private Player winner;

    public Game(List<Player> players) {
        this.players = players;
        gameId = UUID.randomUUID().toString();
    }

    public void nextPlayer() {
        if (currentPlayerIdx + 1 != players.size())
            currentPlayerIdx++;
        else
            currentPlayerIdx = 0;
    }


    public StepFinishedState step(String id, int amount) {
        if (!isWon) {
            StepFinishedState finishedState = players.get(currentPlayerIdx).step(id, amount);
            if (finishedState == StepFinishedState.IN)
                if (checkPlayers())
                    return StepFinishedState.WON;
            nextPlayer();
            return finishedState;
        }
        return StepFinishedState.NONE;
    }

    private boolean checkPlayers() {
        for (Player pl :
                players) {
            if (pl.won()){
                isWon = true;
                win(pl);
                return true;
            }
        }
        return false;
    }

    public void win(Player player) {
        this.winner = player;
    }

    public void generateBoard() {
        int numOfPlayers = players.size();
        board = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            for (int j = 0; j < 15; j++) {
                if (j % 3 == 2) {
                    board.add(new CardField());
                    continue;
                }
                board.add(new SimpleField());
            }
        }
        for (int i = 0; i < numOfPlayers; i++) {
            List<Piece> pieces = players.get(i).getPieces();
            for (Piece piece : pieces)
                piece.generatePath(board, i * 15);
        }
    } // TODO: 5/13/19 player generáláskor path kijelölése a bábunak


    public String getCardText() {
        Collections.shuffle(cards);
        String text = cards.get(0);
        cards.remove(0);
        return text;
    }

    public List<String> getBoard() {
        List<String> ids = new ArrayList<>();
        for (Field f:
             board) {
            ids.add(f.getId());
        }
        return ids;
    }
}
