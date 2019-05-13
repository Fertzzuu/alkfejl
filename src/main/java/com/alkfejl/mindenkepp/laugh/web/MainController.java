package com.alkfejl.mindenkepp.laugh.web;


import com.alkfejl.mindenkepp.laugh.game.enums.StepFinishedState;
import com.alkfejl.mindenkepp.laugh.web.model.JoinRequestBody;
import com.alkfejl.mindenkepp.laugh.web.service.GameService;
import com.sun.xml.internal.xsom.impl.scd.Step;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String joinLobby(@PathVariable("access_code") String accessCode, @RequestBody JoinRequestBody requestBody) {
        return gameService.joinLobby(accessCode, requestBody.getName(), requestBody.getColor());
    }

    @GetMapping("pieces/{player_id}")
    public List<String> getPiecesByPlayerId(@PathVariable("player_id") String playerId) {
        return gameService.getPiecesByPlayerId(playerId);
    }

    @PostMapping("step/{piece_id}")
    public StepFinishedState step(@PathVariable("piece_id") String pieceId) {
        return gameService.step(pieceId);
    }

    @GetMapping("/card")
    public String getCard() {
        return gameService.getCard();
    }




}
