/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author MacDerson
 */
public abstract class AbstractRetriever<T, R> implements Retriever<R> {

    private final String[] paths;
    private final Class<T> CLASS_TYPE;

    public AbstractRetriever(Class<T> type, String... paths) {
        if (Objects.isNull(paths) || Objects.isNull(type)) {
            throw new NullPointerException("Parameters can't be null");
        }
        this.paths = paths;
        this.CLASS_TYPE = type;
    }

    public T getObject() throws IOException {
        T t = null;
        JSONObject json = getJsonObject();

        if (Objects.nonNull(json)) {
            ObjectMapper om = new ObjectMapper();
            t = om.readValue(json.toString(), CLASS_TYPE);
        }
        return t;
    }
    
    public JSONObject getJSONObject(String... paths) throws IOException{
        JSONObject json = getJsonObject(getTarget(paths));
        return json;
    }

    public JSONObject getJsonObject() {
        JSONObject json = null;
        WebTarget target = getTarget();

        Response res = target.request(MediaType.APPLICATION_JSON_TYPE).get();
//        System.out.println(res.getStatus());
        if (res.getStatus() == Response.Status.OK.getStatusCode()) {
            String consumable = Optional.ofNullable(res.readEntity(String.class)).orElse("");
            if (!consumable.isEmpty()) {
                try {
                    json = new JSONObject(consumable);
                    return json;
                } catch (JSONException ex) {
//                    Logger.getLogger(ScheduleRetrieverTask.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return json;
    }
    
    public JSONObject getJsonObject(WebTarget target){
        JSONObject json = null;

        Response res = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (res.getStatus() == Response.Status.OK.getStatusCode()) {
            String consumable = Optional.ofNullable(res.readEntity(String.class)).orElse("");
            if (!consumable.isEmpty()) {
                try {
                    json = new JSONObject(consumable);
                    return json;
                } catch (JSONException ex) {
//                    Logger.getLogger(ScheduleRetrieverTask.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return json;
    }

    protected WebTarget getTarget() {
        ClientConfig cfg = new ClientConfig();
        cfg.register(JacksonJsonProvider.class);
        Client client = ClientBuilder.newClient(cfg);
        WebTarget target = null;
        boolean firstRun = true;
        for (String path : paths) {
            if (firstRun) {
                target = client.target(path);
                firstRun = false;
            } else {
                target = target.path(path);
            }
        }
        return target;
    }
    
    protected WebTarget getTarget(String... paths) {
        ClientConfig cfg = new ClientConfig();
        cfg.register(JacksonJsonProvider.class);
        Client client = ClientBuilder.newClient(cfg);
        WebTarget target = null;
        boolean firstRun = true;
        for (String path : paths) {
            if (firstRun) {
                target = client.target(path);
                firstRun = false;
            } else {
                target = target.path(path);
            }
        }
        return target;
    }
}
