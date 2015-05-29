/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.db.concrete;

import com.design.perpetual.fantasyfootball.app.db.AbstractRepository;
import com.design.perpetual.fantasyfootball.app.entities.Player;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class PlayerRepository extends AbstractRepository<Player> {

    public PlayerRepository() {
        super(Player.class);
    }
    
}
