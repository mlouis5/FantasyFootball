/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer.concrete;

import com.design.perpetual.fantasyfootball.app.dataminer.AbstractRetriever;
import com.design.perpetual.fantasyfootball.app.dataminer.pojo.JsonPlayerList;
import com.design.perpetual.fantasyfootball.app.entities.Player;
import com.design.perpetual.fantasyfootball.app.services.PlayerService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class PlayerRetriever extends AbstractRetriever<JsonPlayerList, Void> {

    private static final String base = "http://www.fantasyfootballnerd.com/service";
    private static final String resource = "players";
    private static final String format = "json";
    private static final String KEY = "guxtr5ihdj7t";

    @Autowired
    private PlayerService plyrSrv;

    public PlayerRetriever() {
        super(JsonPlayerList.class, base, resource, format, KEY);
    }

    @Override
    public Void retrieve() throws IOException {
        LocalDate now = LocalDate.now();
        int currYear = LocalDate.now().getYear();
        int retYear = plyrSrv.getPlayerRetrievalYear();
        if (retYear < currYear) {
            JsonPlayerList jpl = getObject();
            if (Objects.nonNull(jpl)) {
                List<Player> allPlayers = jpl.getPlayers();
                if (Objects.nonNull(allPlayers)) {
                    allPlayers.stream().filter(p -> Objects.nonNull(p))
                            .forEach(p -> {
                                p.setRetrievalYear(currYear);

                                plyrSrv.addNewPlayer(p);
                            });
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        PlayerRetriever pr = new PlayerRetriever();
        pr.retrieve();
    }
}
