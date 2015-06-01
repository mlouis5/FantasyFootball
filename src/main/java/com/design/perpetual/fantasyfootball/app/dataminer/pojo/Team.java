/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer.pojo;

import com.design.perpetual.fantasyfootball.app.utils.parsers.Aggregatable;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mac
 */
public class Team implements Aggregatable<Team>{
    
    private static final String ABBR_KEY = "abbr";
    private static final String STAT_KEY = "stats";

    private String abbr;
    private Stats stats;
    
    public Team(){
        abbr = "";
        stats = null;
    }
    
    public Team(JSONObject team) throws JSONException, 
            IllegalArgumentException, IllegalAccessException, 
            ClassNotFoundException, InstantiationException{
        abbr = team.getString(ABBR_KEY);
        stats = new Stats(team.getJSONObject(STAT_KEY));
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.abbr);
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
        final Team other = (Team) obj;
        return Objects.equals(abbr, other.abbr);
    }

    @Override
    public void aggregate(Team t) {
        if(this.equals(t)){
            this.stats.aggregate(t.getStats());
        }
    }
}
