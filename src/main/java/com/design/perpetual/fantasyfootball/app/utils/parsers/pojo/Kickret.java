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
public class Kickret extends AbstractStat<Kickret> implements Jsonable{

    private int ret;
    private double avg;
    private int tds;
    private int lng;
    private int lngtd;
    
    public Kickret() {
    }

    public Kickret(JSONObject json) {
        initJson(json);
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getTds() {
        return tds;
    }

    public void setTds(int tds) {
        this.tds = tds;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public int getLngtd() {
        return lngtd;
    }

    public void setLngtd(int lngtd) {
        this.lngtd = lngtd;
    }
    
    
    @Override
    public void aggregate(Kickret t) {
        if(Objects.isNull(t)){
            return;
        }
        if(this.equals(t)){
            this.avg = t.getAvg() > avg ? t.getAvg() : avg;
            this.lng = t.getLng() > lng ? t.getLng() : lng;
            this.lngtd = t.getLngtd() > lngtd ? t.getLngtd() : lngtd;
            this.ret += t.getRet();
            this.tds += t.getTds();
        }
    }
    
}
