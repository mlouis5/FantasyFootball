/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.utils.parsers;

import com.design.perpetual.fantasyfootball.app.utils.parsers.pojo.Passing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;

/**
 *
 * @author MacDerson
 */
public abstract class AbstractStat<T> implements Aggregatable<T> {
    private String name;
    @JsonIgnore
    private String team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.team);
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
        final AbstractStat other = (AbstractStat) obj;
        return Objects.equals(name, other.name) 
                && Objects.equals(team, other.team);
    }
        
    public abstract void aggregate(T t);
}
