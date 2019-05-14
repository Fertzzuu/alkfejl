package com.alkfejl.mindenkepp.laugh.web;


import com.alkfejl.mindenkepp.laugh.exception.NotEnoughPlayersException;
import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;
import com.alkfejl.mindenkepp.laugh.web.model.JoinRequestBody;
import com.alkfejl.mindenkepp.laugh.web.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private GameService gameService;

    @PostMapping("/join/{access_code}")
    public ResponseEntity<Object> joinLobby(@PathVariable("access_code") String accessCode, @RequestBody JoinRequestBody requestBody) {
        String id = gameService.joinLobby(accessCode, requestBody.getName(), requestBody.getColor());
        if (id != null)
            return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
        else return new ResponseEntity<>("Wrong access code", HttpStatus.FORBIDDEN);
    }

    @GetMapping("pieces/{player_id}")
    public ResponseEntity<Object> getPiecesByPlayerId(@PathVariable("player_id") String playerId) {
        List<String> pieces = gameService.getPiecesByPlayerId(playerId);
        return new ResponseEntity<>(pieces, HttpStatus.OK);

    }

    @PostMapping("step/{piece_id}")
    public ResponseEntity<Object> step(@PathVariable("piece_id") String pieceId)
    {
         StepFinishedState state = gameService.step(pieceId);
         return new ResponseEntity<>(state, HttpStatus.ACCEPTED);
    }

    @GetMapping("/card")
    public ResponseEntity<Object> getCard() {
        return new ResponseEntity<>(gameService.getCard(), HttpStatus.OK);
    }

    @PostMapping("/startgame")
    public ResponseEntity<Object> startGame() {
        try {
            gameService.startGame();
            return new ResponseEntity<>("Game started", HttpStatus.OK);
        } catch (NotEnoughPlayersException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Not enough players", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getboard")
    public ResponseEntity<Object> getBoard() {
        List<String> board = gameService.getBoard();
        if (board != null) return new ResponseEntity<>(board, HttpStatus.OK);
        else return new ResponseEntity<>("Game is not running in current lobby", HttpStatus.BAD_REQUEST);
    }

}
