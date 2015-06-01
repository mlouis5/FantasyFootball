/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.utils.parsers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;

/**
 *
 * @author MacDerson
 * @param <T>
 */
public abstract class AbstractStat<T extends AbstractStat> implements Aggregatable<T> {

    private String pid;
    private String name;
    @JsonIgnore
    private String team;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

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
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.pid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        System.out.println("getClass(): " + getClass());
        System.out.println("obj.getClass(): " + obj.getClass());
        if (getClass() != obj.getClass()) {
            System.out.println("returning false, objects not same");
            return false;
        }
        final AbstractStat<T> other = (AbstractStat<T>) obj;
        return Objects.equals(pid, other.pid);
    }

    @Override
    public abstract void aggregate(T t);
}
