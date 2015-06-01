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
public class Receiving extends AbstractStat<Receiving> implements Jsonable{

    private int rec;
    private int yds;
    private int tds;
    private int lng;
    private int lngtd;
    private int twopta;
    private int twoptm;
    
    public Receiving() {
        rec = 0;
        yds = 0;
        tds = 0;
        lng = 0;
        lngtd = 0;
        twopta = 0;
        twoptm = 0;
    }

    public Receiving(JSONObject json) {
        this();
        initJson(json);
    }

    public int getRec() {
        return rec;
    }

    public void setRec(int rec) {
        this.rec = rec;
    }

    public int getYds() {
        return yds;
    }

    public void setYds(int yds) {
        this.yds = yds;
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

    public int getTwopta() {
        return twopta;
    }

    public void setTwopta(int twopta) {
        this.twopta = twopta;
    }

    public int getTwoptm() {
        return twoptm;
    }

    public void setTwoptm(int twoptm) {
        this.twoptm = twoptm;
    }
    
    @Override
    public void aggregate(Receiving t) {
        if(Objects.isNull(t)){
            return;
        }
        if(this.equals(t)){
            this.lng = t.getLng() > lng ? t.getLng() : lng;
            this.lngtd += t.getLngtd() > lngtd ? t.getLngtd() : lngtd;
            this.rec += t.getRec();
            this.tds += t.getTds();
            this.twopta += t.getTwopta();
            this.twoptm += t.getTwoptm();
            this.yds += t.getYds();
        }
    }
    
}
