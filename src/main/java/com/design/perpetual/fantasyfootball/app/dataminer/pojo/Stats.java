/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer.pojo;

import com.design.perpetual.fantasyfootball.app.utils.parsers.AbstractStat;
import com.design.perpetual.fantasyfootball.app.utils.parsers.Aggregatable;
import com.design.perpetual.fantasyfootball.app.utils.parsers.Jsonable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats implements Aggregatable<Stats> {

    private String[] keys = {
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Passing",
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Rushing",
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Receiving",
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Fumbles",
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Kicking",
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Punting",
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Kickret",
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Puntret",
        "com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Defense"
    };

    private Set<AbstractStat> playerStats;

    public Stats() {
        playerStats = new HashSet();
    }

    public Stats(JSONObject stats) throws JSONException,
            IllegalArgumentException, IllegalAccessException,
            ClassNotFoundException, InstantiationException {
        this();

        for (String key : keys) {
            String[] splits = key.toLowerCase().split("\\.");
            JSONObject statType;
            try{
                statType = stats.getJSONObject(splits[splits.length - 1]);
            }catch(JSONException ex){
                continue;
            }
            Iterator<String> playerKeys = statType.keys();
            Class<?> toInstance = Class.forName(key);

            if (Objects.nonNull(toInstance)) {
                while (playerKeys.hasNext()) {
                    String playerKey = playerKeys.next();

                    AbstractStat abstractStat = (AbstractStat) toInstance.newInstance();
                    JSONObject player = statType.getJSONObject(playerKey);
                    if (Objects.nonNull(abstractStat) && Objects.nonNull(player)) {
                        abstractStat.setPid(playerKey);
                        ((Jsonable) abstractStat).initJson(player);

                        this.aggregate(abstractStat);
                    }
                }
            }
        }
    }

    private void aggregate(AbstractStat playerStat) {
        if (Objects.isNull(playerStat)) {
            return;
        }
        if (playerStats.isEmpty()) {
            playerStats.add(playerStat);
        } else {
            try {
                playerStats.stream().filter(s -> {
                    return Objects.nonNull(s) && s.equals(playerStat);
                }).findFirst().get().aggregate(playerStat);
            } catch (NoSuchElementException ex) {
                playerStats.add(playerStat);
            }
        }
    }

    @Override
    public void aggregate(Stats playerStats) {
        if (Objects.nonNull(playerStats)) {
            Set<AbstractStat> pStats = playerStats.playerStats;
            if (this.playerStats.isEmpty()) {
                this.playerStats = pStats;
            } else {
                for (AbstractStat localAs : pStats) {
                    if (!this.playerStats.contains(localAs)) {
                        this.playerStats.add(localAs);
                    } else {
                        Iterator<AbstractStat> stat = this.playerStats.iterator();
                        while (stat.hasNext()) {
                            AbstractStat as = stat.next();
                            if (as.equals(localAs)) {
                                as.aggregate(localAs);
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("playerStats size: " + this.playerStats.size());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.playerStats);
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
        final Stats other = (Stats) obj;
        return Objects.equals(playerStats, other.playerStats);
    }

}
