package com.intuit.players.service.impl;

import com.intuit.players.model.Player;
import com.intuit.players.repository.PlayerRepository;
import com.intuit.players.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {


    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayer(String playerID) {
        Optional<Player> byId = playerRepository.findById(playerID);
        return byId.orElse(null);
    }
}
