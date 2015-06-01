/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer.pojo;

import com.design.perpetual.fantasyfootball.app.utils.parsers.Aggregatable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author MacDerson
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game implements Aggregatable<Game>{
    
    private static final String HOME_KEY = "home";
    private static final String AWAY_KEY = "away";

    private String gameId;
    private Team home;
    private Team away;
    
    public Game(){
        home = null;
        away = null;
    }
    
    public Game(JSONObject game) throws JSONException, 
            IllegalArgumentException, IllegalAccessException, 
            ClassNotFoundException, InstantiationException{
        home = new Team(game.getJSONObject(HOME_KEY));
        away = new Team(game.getJSONObject(AWAY_KEY));
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }
    
    public boolean containsTeam(Team team){
        return Objects.nonNull(team) 
                && (team.equals(home) || team.equals(away));
    }
    
    public Team getEquivalentTeam(Team t){
        if(Objects.isNull(t)){
            return null;
        }
        return t.equals(home) ? home : t.equals(away) ? away : null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.gameId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        return Objects.equals(gameId, other.gameId);
    }

    @Override
    public void aggregate(Game t) {
        if(Objects.isNull(home) || Objects.isNull(away)){
            return;
        }
        if(home.equals(t.getHome())){
            home.aggregate(t.getHome());
        }else if(home.equals(t.getAway())){
            home.aggregate(t.getAway());
        }
        
        if(away.equals(t.getHome())){
            away.aggregate(t.getHome());
        }else if(away.equals(t.getAway())){
            away.aggregate(t.getAway());
        }
    }
}
