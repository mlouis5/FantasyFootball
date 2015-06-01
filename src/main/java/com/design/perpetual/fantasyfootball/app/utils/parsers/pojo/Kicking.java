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
public class Kicking extends AbstractStat<Kicking> implements Jsonable{

    private int fgm;
    private int fga;
    private int fgyds;
    private int totpfg;
    private int xpmade;
    private int xpmissed;
    private int xpa;
    private int xpb;
    private int xptot;

    public Kicking() {
        fgm = 0;
        fga = 0;
        fgyds = 0;
        totpfg = 0;
        xpmade = 0;
        xpmissed = 0;
        xpa = 0;
        xpb = 0;
        xptot = 0;
    }

    public Kicking(JSONObject json) {
        this();
        initJson(json);
    }
    
    public int getFgm() {
        return fgm;
    }

    public void setFgm(int fgm) {
        this.fgm = fgm;
    }

    public int getFga() {
        return fga;
    }

    public void setFga(int fga) {
        this.fga = fga;
    }

    public int getFgyds() {
        return fgyds;
    }

    public void setFgyds(int fgyds) {
        this.fgyds = fgyds;
    }

    public int getTotpfg() {
        return totpfg;
    }

    public void setTotpfg(int totpfg) {
        this.totpfg = totpfg;
    }

    public int getXpmade() {
        return xpmade;
    }

    public void setXpmade(int xpmade) {
        this.xpmade = xpmade;
    }

    public int getXpmissed() {
        return xpmissed;
    }

    public void setXpmissed(int xpmissed) {
        this.xpmissed = xpmissed;
    }

    public int getXpa() {
        return xpa;
    }

    public void setXpa(int xpa) {
        this.xpa = xpa;
    }

    public int getXpb() {
        return xpb;
    }

    public void setXpb(int xpb) {
        this.xpb = xpb;
    }

    public int getXptot() {
        return xptot;
    }

    public void setXptot(int xptot) {
        this.xptot = xptot;
    }    
    
    @Override
    public void aggregate(Kicking t) {
        if(Objects.isNull(t)){
            return;
        }
        if(this.equals(t)){
            this.fga += t.getFga();
            this.fgm += t.getFgm();
            this.fgyds += t.getFgyds();
            this.totpfg += t.getTotpfg();
            this.xpa += t.getXpa();
            this.xpb += t.getXpb();
            this.xpmade += t.getXpmade();
            this.xpmissed += t.getXpmissed();
            this.xptot += t.getXptot();
        }
    }
    
}
