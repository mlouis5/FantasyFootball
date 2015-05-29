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
public class Punting extends AbstractStat<Punting> implements Jsonable{

    private int pts;
    private int yds;
    private double avg;
    private int i20;
    private int lng;
    
    public Punting() {
    }

    public Punting(JSONObject json) {
        initJson(json);
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getYds() {
        return yds;
    }

    public void setYds(int yds) {
        this.yds = yds;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getI20() {
        return i20;
    }

    public void setI20(int i20) {
        this.i20 = i20;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }
    
    
    @Override
    public void aggregate(Punting t) {
        if(Objects.isNull(t)){
            return;
        }
        if(this.equals(t)){
            this.avg = t.getAvg() > avg ? t.getAvg() : avg;
            this.i20 += t.getI20();
            this.lng = t.getLng() > lng ? t.getLng() : lng;
            this.pts += t.getPts();
            this.yds += t.getYds();
        }
    }
    
}
