package com.intuit.players.service.db;

import com.intuit.players.model.Player;
import com.intuit.players.repository.PlayerRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerLoaderService {

    private final PlayerRepository playerRepository;

    @Value("classpath:player.csv")
    private Resource resource;

    @PostConstruct
    public void importPlayers() {
        List<Player> players = buildCsvPlayerByColumnName();
        if (!players.isEmpty()) {
            playerRepository.saveAll(players);
            log.info("INFO:import CSV file success [ players count = {}]", players.size());
        }
        else {
            log.info("INFO:Empty CSV file");
        }
    }

    public List<Player> buildCsvPlayerByColumnName() {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            CsvToBean<Player> csvToBean = new CsvToBeanBuilder<Player>(reader).withType(Player.class).build();
            List<Player> parse = csvToBean.parse();
            reader.close();
            return parse;
        } catch (Exception e) {
            log.error("ERROR: building players from CSV file" ,e);
            return Collections.emptyList();
        }
    }
}
