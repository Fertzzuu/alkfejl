package com.alkfejl.mindenkepp.laugh.web.service;

import com.alkfejl.mindenkepp.laugh.exception.NotEnoughPlayersException;
import com.alkfejl.mindenkepp.laugh.game.GameApplication;
import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private GameApplication application;

    public GameService(){
        application = GameApplication.getInstance();
        application.createLobby();
    }

    public String joinLobby(String accessCode, String name, int color){
        return application.joinLobby(accessCode, name, color);
    }

    public List<String> getPiecesByPlayerId(String playerId)  {
        return application.getPiecesByPlayerId(playerId);
    }

    public StepFinishedState step(String pieceId) {
        Random rnd = new Random();
        int amount = Math.abs(rnd.nextInt(6) + 1);
        return application.step(pieceId, amount);
    }

    public String getCard() {
        return application.getCardText();
    }


    public void startGame() throws NotEnoughPlayersException {
        application.startGame();
    }

    public List<String> getBoard() {
        return application.getBoard();
    }
}
