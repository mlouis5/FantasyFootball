/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.utils.parsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Field;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author MacDerson
 */
public interface Jsonable {

    public default void initJson(JSONObject json) {
        if (Objects.isNull(json)) {
            return;
        }

        Field[] fields = getClass().getDeclaredFields();
        Field[] inheritedFields = getClass().getSuperclass().getDeclaredFields();

        Field[] allFields = ArrayUtils.addAll(fields, inheritedFields);
        for (Field f : allFields) {
            if (Objects.nonNull(f)) {
                f.setAccessible(true);
                try {
                    JsonProperty jp = f.getAnnotation(JsonProperty.class);
                    String fieldName;
                    if (Objects.nonNull(jp)) {
                        fieldName = jp.value();
                    } else {
                        fieldName = f.getName();
                    }
                    Object jsonValue = json.get(fieldName);
                    System.out.println(jsonValue);
                    f.set(this, jsonValue);
                } catch (JSONException | IllegalArgumentException | IllegalAccessException ex) {

                }
                f.setAccessible(false);
            }
        }
    }
}
