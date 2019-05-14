package com.alkfejl.mindenkepp.laugh.game;


import com.alkfejl.mindenkepp.laugh.exception.NotEnoughPlayersException;
import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;
import com.alkfejl.mindenkepp.laugh.game.models.Player;

import java.util.ArrayList;
import java.util.List;


public class GameApplication {
    private Lobby lobby; // TODO: 5/13/19 v2:  hashmap lobbies -header id
    private static final GameApplication instance = new GameApplication();

    private GameApplication() {
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static GameApplication getInstance() {
        return instance;
    }

    public void createLobby() {
        this.lobby = new Lobby();
        System.out.println("Lobby: " + lobby.getAccessCode());
    }

    public String joinLobby(String accessCode, String name, int color) {
        if (getLobbyAccessCode().equals(accessCode)) {
            Player player = new Player(name, color);
            lobby.join(player);
            return player.getId();
        } else {
            return null;
        }
    }

    public String getLobbyAccessCode() {
        return lobby.getAccessCode();
    }

    public void listPlayersInLobby() {
        for (Player player :
                lobby.getPlayers()) {
            System.out.println(player.getName());
        }
    }

    public StepFinishedState step(String id, int amount) {
        return lobby.isGameRunning() ? lobby.step(id, amount) : StepFinishedState.NONE;
    }

    public List<String> getPiecesByPlayerId(String playerId) {
        List<String> pieces = new ArrayList<>();
        lobby.getPlayers()
                .stream()
                .filter(player -> player.getId().equals(playerId))
                .findAny()
                .get()
                .getPieces()
                .forEach(piece -> {
                    pieces.add(piece.getId());
                });
        return pieces;
    }

    public String getCardText() {
        return lobby.getCardText();
    }

    public void startGame() throws NotEnoughPlayersException {
        lobby.startGame();
    }

    public List<String> getBoard() {
        return lobby.isGameRunning() ? lobby.getBoard() : null;
    }
}
