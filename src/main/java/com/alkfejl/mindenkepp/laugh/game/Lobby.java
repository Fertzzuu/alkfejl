package com.alkfejl.mindenkepp.laugh.game;


import com.alkfejl.mindenkepp.laugh.exception.NotEnoughPlayersException;
import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;
import com.alkfejl.mindenkepp.laugh.game.models.Player;

import java.util.ArrayList;
import java.util.List;


public class Lobby {
    private Game game;
    private List<Player> players;
    private String accessCode;

    public Lobby() {
        players = new ArrayList<>();
        accessCode = generateAccessCode();
    }

    public String getAccessCode() {
        return accessCode;
    }

    private String generateAccessCode() {
        String currString = String.valueOf(System.currentTimeMillis());
        String hexString = Integer.toHexString(currString.hashCode());
        return hexString.substring(hexString.length() - 4).toUpperCase();
    }

    public void join(Player p) {
        players.add(p);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void startGame() throws NotEnoughPlayersException {
        if (players.size() < 4)
            throw new NotEnoughPlayersException();

        this.game = new Game(players);
        game.generateBoard();
    }

    public StepFinishedState step(String id, int amount) {
        return game.step(id, amount);
    }

    public boolean isGameRunning() {
        return game != null;
    }

    public String getCardText() {
        return game.getCardText();

    }

    public void win(String playerid) {
        Player winner = players.stream().filter(player -> player.getId().equals(playerid)).findAny().get();
        game.win(winner);
    }
}
