/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.services;

import com.design.perpetual.fantasyfootball.app.db.concrete.PlayerRepository;
import com.design.perpetual.fantasyfootball.app.entities.Player;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class PlayerService {
    
    @Autowired
    @Qualifier(value = "playerRepository")
    private PlayerRepository repo;
    
    public int getPlayerRetrievalYear(){
        LocalDate now = LocalDate.now();
        CriteriaBuilder cb = repo.getCriteriaBuilder();
        CriteriaQuery<Player> cq = repo.getCriteriaQuery();
        Root<Player> root = cq.from(repo.getEntityType());
        cq.select(root).orderBy(cb.desc(root.get("retrievalYear")));
        List<Player> players = repo.getCriteriaList(cq);
        if(Objects.isNull(players) || players.isEmpty()){
            return -1;
        }        
        return players.get(0).getRetrievalYear();
    }
    
    public void addNewPlayer(Player p){
        if(Objects.isNull(p)){
            return;
        }
        repo.create(p);
    }
}
