package com.intuit.players.service;

import com.intuit.players.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    Player getPlayer(String playerID);
}
