/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.utils.parsers.pojo;

import com.design.perpetual.fantasyfootball.app.utils.parsers.AbstractStat;
import com.design.perpetual.fantasyfootball.app.utils.parsers.Jsonable;
import java.util.Objects;
import org.json.JSONObject;

/**
 *
 * @author MacDerson
 */
public class Fumbles extends AbstractStat<Fumbles> implements Jsonable {

    private int tot;
    private int rcv;
    private int trcv;
    private int yds;
    private int lost;
    
    public Fumbles() {
    }

    public Fumbles(JSONObject json) {
        initJson(json);
    }

    public int getTot() {
        return tot;
    }

    public void setTot(int tot) {
        this.tot = tot;
    }

    public int getRcv() {
        return rcv;
    }

    public void setRcv(int rcv) {
        this.rcv = rcv;
    }

    public int getTrcv() {
        return trcv;
    }

    public void setTrcv(int trcv) {
        this.trcv = trcv;
    }

    public int getYds() {
        return yds;
    }

    public void setYds(int yds) {
        this.yds = yds;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }
    
    @Override
    public void aggregate(Fumbles t) {
        if(Objects.isNull(t)){
            return;
        }
        if(this.equals(t)){
            this.lost += t.getLost();
            this.rcv += t.getRcv();
            this.tot += t.getTot();
            this.trcv += t.getTrcv();
            this.yds += t.getYds();
        }
    }
    
}
