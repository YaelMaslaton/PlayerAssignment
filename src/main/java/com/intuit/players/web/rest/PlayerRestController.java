package com.intuit.players.web.rest;


import com.intuit.players.model.Player;
import com.intuit.players.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerRestController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getAllPlayers());
    }

    @GetMapping("/{playerID}")
    public ResponseEntity<Player> getPlayer(@PathVariable String playerID) {
        Player player = playerService.getPlayer(playerID);
        if (Objects.nonNull(player)) {
            return ResponseEntity.status(HttpStatus.OK).body(player);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
