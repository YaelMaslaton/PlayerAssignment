package com.intuit.players;

import com.intuit.players.model.Player;
import com.intuit.players.repository.PlayerRepository;
import com.intuit.players.service.impl.PlayerServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private final Player player1 = Player.builder().playerID("sddfc3s").birthYear("1981").birthMonth("10").build();
    private final Player player2 = Player.builder().playerID("aardsda01").birthYear("1983").build();

    @Test
    public void getAllPlayers() {
        List<Player> expectedPlayers = List.of(player1, player2);
        Mockito.when(playerRepository.findAll()).thenReturn(expectedPlayers);
        List<Player> actualPlayers = playerService.getAllPlayers();
        assertEquals(expectedPlayers, actualPlayers);
    }
    @Test
    public void getAllPlayersException() throws IOException {
        Mockito.when(playerRepository.findAll()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> playerService.getAllPlayers());
    }

    @Test
    public void getPlayer() {
        Mockito.when(playerRepository.findById(player1.getPlayerID())).thenReturn(Optional.of(player1));
        Player actualPlayer = playerService.getPlayer(player1.getPlayerID());
        assertEquals(player1, actualPlayer);
    }
}
