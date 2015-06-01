/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer.concrete;

import com.design.perpetual.fantasyfootball.app.dataminer.AbstractRetriever;
import com.design.perpetual.fantasyfootball.app.dataminer.pojo.Game;
import com.design.perpetual.fantasyfootball.app.dataminer.pojo.Team;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MacDerson
 */
public class GameStatsRetriever extends AbstractRetriever<JSONObject, Void> {

    private static final String BASE = "http://www.nfl.com/liveupdate/game-center";
    private static final String resource = "2014122106";
    private static final String format = "2014122106_gtd.json";
//    private static final String KEY = "guxtr5ihdj7t";

    @Autowired
//    private PlayerService plyrSrv;

    private Set<Game> games;
    private Set<Team> teams;

    public GameStatsRetriever() {
        super(JSONObject.class, BASE, resource, format);
        games = new HashSet();
        teams = new HashSet();
    }

    @Override
    public Void retrieve() throws IOException {
        LocalDate sepFirst = LocalDate.of(LocalDate.now().getYear() - 1, Month.SEPTEMBER, 7);
        LocalDate janFirst = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
        
        GameRetrievalTask grt = new GameRetrievalTask(sepFirst, janFirst);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(grt, 0L, 10);
        
        while(!grt.isDone()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameStatsRetriever.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        timer.cancel();
        return null;
    }

    private Game processGame(JSONObject game) throws JSONException,
            IllegalArgumentException, IllegalAccessException,
            ClassNotFoundException, InstantiationException {
        return new Game(game);
    }

    private void aggregateGames(Set<Game> games) {
        games.stream().filter(game -> {
            return Objects.nonNull(game);
        }).forEach(game -> {
            if (!teams.contains(game.getHome())) {
                teams.add(game.getHome());
            }
            if (!teams.contains(game.getAway())) {
                teams.add(game.getAway());
            }
        });

        games.stream().filter(game -> {
            return Objects.nonNull(game);
        }).forEach(game -> {
            teams.parallelStream().filter(team -> Objects.nonNull(team))
                    .forEach(team -> {
                        if (game.containsTeam(team)) {
                            Team t = game.getEquivalentTeam(team);
                            if (Objects.nonNull(t)) {
                                team.aggregate(t);
                            }
                        }
                    });
        });
    }

    public static void main(String[] args) throws IOException {
        GameStatsRetriever gsr = new GameStatsRetriever();

        gsr.retrieve();
    }

    private class GameRetrievalTask extends TimerTask {

        private LocalDate start;
        private final LocalDate end;
        private boolean done;

        public GameRetrievalTask(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
            this.done = false;
        }
        
        private boolean isDone(){
            return done;
        }

        @Override
        public synchronized void run() {
            if (start.isBefore(end)) {
                String dateText = start.format(DateTimeFormatter.ISO_DATE);
                for (int i = 0; i < 16; i++) {
                    try {
                        String gameId = (dateText + String.format("%02d", i)).replace("-", "");
                        System.out.println(gameId);

                        String responseformat = gameId + "_gtd.json";
                        JSONObject json = getJSONObject(BASE, gameId, responseformat);

                        if (Objects.nonNull(json)) {
                            System.out.println(json.toString());

                            JSONObject game = json.getJSONObject(gameId);
                            Game newGame = processGame(game);
                            newGame.setGameId(gameId);
                            games.add(newGame);
                        }
                    } catch (JSONException | IOException 
                            | IllegalArgumentException | IllegalAccessException 
                            | ClassNotFoundException | InstantiationException ex) {
                        Logger.getLogger(GameStatsRetriever.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }  
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameStatsRetriever.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                start = start.plusDays(1);
            }
            if(start.isEqual(end) || start.isAfter(end)){            
                aggregateGames(games);
                this.done = true;
            }
        }

    }
}
