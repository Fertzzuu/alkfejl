package com.alkfejl.mindenkepp.laugh.web;


import com.alkfejl.mindenkepp.laugh.exception.NotEnoughPlayersException;
import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;
import com.alkfejl.mindenkepp.laugh.game.models.Field;
import com.alkfejl.mindenkepp.laugh.game.models.Piece;
import com.alkfejl.mindenkepp.laugh.game.models.Player;
import com.alkfejl.mindenkepp.laugh.web.model.JoinRequestBody;
import com.alkfejl.mindenkepp.laugh.web.model.PieceDetailsResponse;
import com.alkfejl.mindenkepp.laugh.web.service.GameService;
import com.alkfejl.mindenkepp.laugh.web.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MainController {
    @Autowired
    private GameService gameService;

    @Autowired
    private LoggerService loggerService;

    @PostMapping("/join/{access_code}")
    public ResponseEntity<Object> joinLobby(@PathVariable("access_code") String accessCode, @RequestBody JoinRequestBody requestBody) {
        String id = gameService.joinLobby(accessCode, requestBody.getName(), requestBody.getColor());
        if (id != null) {
            loggerService.d(this, "joined: " + id);
            return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
        } else {
            loggerService.e(this, "Wrong access code");
            return new ResponseEntity<>("Wrong access code", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("pieces/{player_id}")
    public ResponseEntity<Object> getPiecesByPlayerId(@PathVariable("player_id") String playerId) {
        List<String> pieces = gameService.getPiecesByPlayerId(playerId);
        loggerService.d(this, "got pieces with playerid: " + playerId);
        return new ResponseEntity<>(pieces, HttpStatus.OK);

    }

    @PostMapping("step/{piece_id}")
    public ResponseEntity<Object> step(@PathVariable("piece_id") String pieceId) {
        StepFinishedState state = gameService.step(pieceId);
        loggerService.d(this, "stepped with: " + pieceId);
        return new ResponseEntity<>(state, HttpStatus.ACCEPTED);
    }

    @GetMapping("/card")
    public ResponseEntity<Object> getCard() {
        loggerService.d(this, "GET card");
        return new ResponseEntity<>(gameService.getCard(), HttpStatus.OK);
    }

    @PostMapping("/startgame")
    public ResponseEntity<Object> startGame() {
        try {
            gameService.startGame();
            loggerService.d(this, "game started");
            return new ResponseEntity<>("1", HttpStatus.OK);
        } catch (NotEnoughPlayersException e) {
            e.printStackTrace();
            loggerService.e(this, "not enough players");
            return new ResponseEntity<>("-1", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getboard")
    public ResponseEntity<Object> getBoard() {
        List<String> board = gameService.getBoard();
        if (board != null) {
            loggerService.d(this, "got board");
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            loggerService.e(this, "game not started");
            return new ResponseEntity<>("-1", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getpiecelocation")
    public ResponseEntity<Object> getPieceLocation() {
        List<PieceDetailsResponse> response = new ArrayList<>();
        List<Player> players = gameService.getPlayers();
        for (Player player :
                players) {
            PieceDetailsResponse details = new PieceDetailsResponse();
            details.setColor(player.getColor());
            List<String> pieceIds = new ArrayList<>();
            List<String> fieldIds = new ArrayList<>();
            for (Piece piece : player.getPieces()) {
                fieldIds.add(piece.getPath().get(0).getId());
                pieceIds.add(piece.getId());
            }
            details.setFieldIDs(fieldIds);
            details.setPieceIDs(pieceIds);
            response.add(details);
        }
        loggerService.d(this, "piece location updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
