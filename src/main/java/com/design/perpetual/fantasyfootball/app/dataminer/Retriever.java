/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.dataminer;

import java.io.IOException;

/**
 *
 * @author MacDerson
 */
public interface Retriever<R> {
    
    public R retrieve() throws IOException;
}
