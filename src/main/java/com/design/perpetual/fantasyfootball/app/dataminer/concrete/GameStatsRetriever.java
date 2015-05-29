/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer.concrete;

import com.design.perpetual.fantasyfootball.app.dataminer.AbstractRetriever;
import com.design.perpetual.fantasyfootball.app.dataminer.pojo.JsonPlayerList;
import com.design.perpetual.fantasyfootball.app.entities.Player;
import com.design.perpetual.fantasyfootball.app.services.PlayerService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MacDerson
 */
public class GameStatsRetriever  extends AbstractRetriever<JSONObject, Void> {

    private static final String BASE = "http://www.nfl.com/liveupdate/game-center";
    private static final String resource = "2014122106";
    private static final String format = "2014122106_gtd.json";
//    private static final String KEY = "guxtr5ihdj7t";

    @Autowired
//    private PlayerService plyrSrv;

    public GameStatsRetriever() {
        super(JSONObject.class, BASE, resource, format);
    }

    @Override
    public Void retrieve() throws IOException {
        LocalDate sepFirst = LocalDate.of(LocalDate.now().getYear(), Month.SEPTEMBER, 1);
        
        JSONObject json = getJSONObject(BASE);
        return null;
    }
//    private void getTarget(){
//        Client client = ctx.getBean(Client.class);
//        WebTarget target = client.target("http://www.nfl.com").path("liveupdate/game-center")
//                .path("2014122106").path("2014122106_gtd.json" + FORMAT);
//
//        return target.request(MediaType.APPLICATION_JSON_TYPE).get();
//    }
}
