/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.utils.parsers;

/**
 *
 * @author MacDerson
 */
public interface Aggregatable<T> {
    
    public void aggregate(T t);
}
