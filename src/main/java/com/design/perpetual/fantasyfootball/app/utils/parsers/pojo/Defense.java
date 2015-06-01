/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.utils.parsers.pojo;

import com.design.perpetual.fantasyfootball.app.utils.parsers.AbstractStat;
import com.design.perpetual.fantasyfootball.app.utils.parsers.Jsonable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import org.json.JSONObject;

/**
 *
 * @author MacDerson
 */
public class Defense extends AbstractStat<Defense> implements Jsonable {

    private int tkl;
    private int ast;
    private int sk;
    @JsonProperty(value = "int")
    private int intc;
    private int ffum;

    public Defense() {
        tkl = 0;
        ast = 0;
        sk = 0;
        intc = 0;
        ffum = 0;
    }

    public Defense(JSONObject json) {
        this();
        initJson(json);
    }

    public int getTkl() {
        return tkl;
    }

    public void setTkl(int tkl) {
        this.tkl = tkl;
    }

    public int getAst() {
        return ast;
    }

    public void setAst(int ast) {
        this.ast = ast;
    }

    public int getSk() {
        return sk;
    }

    public void setSk(int sk) {
        this.sk = sk;
    }

    public int getIntc() {
        return intc;
    }

    public void setIntc(int intc) {
        this.intc = intc;
    }

    public int getFfum() {
        return ffum;
    }

    public void setFfum(int ffum) {
        this.ffum = ffum;
    }

    @Override
    public void aggregate(Defense t) {
        if (Objects.isNull(t)) {
            return;
        }
        if (this.equals(t)) {
            this.ast += t.getAst();
            this.ffum += t.getFfum();
            this.intc += t.getIntc();
            this.sk += t.getSk();
            this.tkl += t.getTkl();
        }
    }

}
