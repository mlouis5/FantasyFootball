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
public class Rushing extends AbstractStat<Rushing> implements Jsonable{

    private int att;
    private int yds;
    private int tds;
    private int lng;
    private int lngtd;
    private int twopta;
    private int twoptm;

    public Rushing() {
    }

    public Rushing(JSONObject json) {
        initJson(json);
    }
    
    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
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
    public void aggregate(Rushing t) {
        if(Objects.isNull(t)){
            return;
        }
        if(this.equals(t)){
            this.att += t.getAtt();
            this.lng = t.getLng() > lng ? t.getLng() : lng;
            this.lngtd = t.getLngtd() > lngtd ? t.getLngtd() : lngtd;
            this.tds += t.getTds();
            this.twopta += t.getTwopta();
            this.twoptm += t.getTwoptm();
            this.yds += t.getYds();
        }
    }
    
}
