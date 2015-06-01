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
public class Passing extends AbstractStat<Passing> implements Jsonable{
    
    private int att;
    private int cmp;
    private int yds;
    private int tds;
    private int ints;
    private int twopta;
    private int twoptm;
    
    public Passing() {
        att = 0;
        cmp = 0;
        yds = 0;
        tds = 0;
        ints = 0;
        twopta = 0;
        twoptm = 0;
    }

    public Passing(JSONObject json) {
        this();
        initJson(json);
    }
    
    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public int getCmp() {
        return cmp;
    }

    public void setCmp(int cmp) {
        this.cmp = cmp;
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

    public int getInts() {
        return ints;
    }

    public void setInts(int ints) {
        this.ints = ints;
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
    public void aggregate(Passing t) {
        if(Objects.isNull(t)){
            return;
        }
        if(this.equals(t)){
            this.att += t.getAtt();
            this.cmp += t.getCmp();
            this.ints += t.getInts();
            this.tds += t.getTds();
            this.twopta += t.getTwopta();
            this.twoptm += t.getTwoptm();
            this.yds += t.getYds();
        }
    }
    
}
