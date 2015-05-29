/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer.pojo;

import com.design.perpetual.fantasyfootball.app.utils.parsers.Aggregatable;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Defense;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Fumbles;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Kicking;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Kickret;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Passing;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Punting;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Puntret;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Receiving;
import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Rushing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

/**
 *
 * @author MacDerson
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

    private Team home;
    private Team away;

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

    public static class Team {

        private String abbr;
        private Stats stats;

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
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Stats implements Aggregatable<Stats> {

        private Passing passing;
        private Rushing rushing;
        private Receiving receiving;
        private Fumbles fumbles;
        private Kicking kicking;
        private Punting punting;
        private Kickret kickret;
        private Puntret puntret;
        private Defense defense;

        private void doAggregate(Aggregatable main, Aggregatable sub) {
            if (main.getClass() != sub.getClass()) {
                return;
            }
            if (Objects.nonNull(main)) {
                main.aggregate(sub);
            } else {
                main = sub;
            }
        }

        public Passing getPassing() {
            return passing;
        }

        public void setPassing(Passing passing) {
            this.passing = passing;
        }

        public Rushing getRushing() {
            return rushing;
        }

        public void setRushing(Rushing rushing) {
            this.rushing = rushing;
        }

        public Receiving getReceiving() {
            return receiving;
        }

        public void setReceiving(Receiving receiving) {
            this.receiving = receiving;
        }

        public Fumbles getFumbles() {
            return fumbles;
        }

        public void setFumbles(Fumbles fumbles) {
            this.fumbles = fumbles;
        }

        public Kicking getKicking() {
            return kicking;
        }

        public void setKicking(Kicking kicking) {
            this.kicking = kicking;
        }

        public Punting getPunting() {
            return punting;
        }

        public void setPunting(Punting punting) {
            this.punting = punting;
        }

        public Kickret getKickret() {
            return kickret;
        }

        public void setKickret(Kickret kickret) {
            this.kickret = kickret;
        }

        public Puntret getPuntret() {
            return puntret;
        }

        public void setPuntret(Puntret puntret) {
            this.puntret = puntret;
        }

        public Defense getDefense() {
            return defense;
        }

        public void setDefense(Defense defense) {
            this.defense = defense;
        }

        @Override
        public void aggregate(Stats t) {
            if (Objects.isNull(t)) {
                return;
            }
            doAggregate(passing, t.getPassing());
            doAggregate(rushing, t.getRushing());
            doAggregate(receiving, t.getReceiving());
            doAggregate(fumbles, t.getFumbles());
            doAggregate(kicking, t.getKicking());
            doAggregate(punting, t.getPunting());
            doAggregate(kickret, t.getKickret());
            doAggregate(puntret, t.getPuntret());
            doAggregate(defense, t.getDefense());
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 83 * hash + Objects.hashCode(this.passing);
            hash = 83 * hash + Objects.hashCode(this.rushing);
            hash = 83 * hash + Objects.hashCode(this.receiving);
            hash = 83 * hash + Objects.hashCode(this.fumbles);
            hash = 83 * hash + Objects.hashCode(this.kicking);
            hash = 83 * hash + Objects.hashCode(this.punting);
            hash = 83 * hash + Objects.hashCode(this.kickret);
            hash = 83 * hash + Objects.hashCode(this.puntret);
            hash = 83 * hash + Objects.hashCode(this.defense);
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
            return Objects.equals(this.passing, other.passing)
                    && Objects.equals(this.rushing, other.rushing)
                    && Objects.equals(this.receiving, other.receiving)
                    && Objects.equals(this.fumbles, other.fumbles)
                    && Objects.equals(this.kicking, other.kicking)
                    && Objects.equals(this.punting, other.punting)
                    && Objects.equals(this.kickret, other.kickret)
                    && Objects.equals(this.puntret, other.puntret)
                    && Objects.equals(this.defense, other.defense);
        }

    }
}
