/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MacDerson
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "player", catalog = "fantasy", schema = "fantasy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findByPlayerId", query = "SELECT p FROM Player p WHERE p.playerId = :playerId"),
    @NamedQuery(name = "Player.findByActive", query = "SELECT p FROM Player p WHERE p.active = :active"),
    @NamedQuery(name = "Player.findByJersey", query = "SELECT p FROM Player p WHERE p.jersey = :jersey"),
    @NamedQuery(name = "Player.findByLname", query = "SELECT p FROM Player p WHERE p.lname = :lname"),
    @NamedQuery(name = "Player.findByFname", query = "SELECT p FROM Player p WHERE p.fname = :fname"),
    @NamedQuery(name = "Player.findByDisplayName", query = "SELECT p FROM Player p WHERE p.displayName = :displayName"),
    @NamedQuery(name = "Player.findByTeam", query = "SELECT p FROM Player p WHERE p.team = :team"),
    @NamedQuery(name = "Player.findByPosition", query = "SELECT p FROM Player p WHERE p.position = :position"),
    @NamedQuery(name = "Player.findByHeight", query = "SELECT p FROM Player p WHERE p.height = :height"),
    @NamedQuery(name = "Player.findByWeight", query = "SELECT p FROM Player p WHERE p.weight = :weight"),
    @NamedQuery(name = "Player.findByDob", query = "SELECT p FROM Player p WHERE p.dob = :dob"),
    @NamedQuery(name = "Player.findByCollege", query = "SELECT p FROM Player p WHERE p.college = :college"),
    @NamedQuery(name = "Player.findByRetrievalYear", query = "SELECT p FROM Player p WHERE p.retrievalYear = :retrievalYear")})
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "player_id", nullable = false, length = 2147483647)
    private String playerId;
    @Column(name = "active", length = 1)
    private String active;
    @Column(name = "jersey", length = 3)
    private String jersey;
    @Basic(optional = false)
    @Column(name = "lname", nullable = false, length = 77)
    private String lname;
    @Basic(optional = false)
    @Column(name = "fname", nullable = false, length = 77)
    private String fname;
    @Basic(optional = false)
    @Column(name = "display_name", nullable = false, length = 144)
    private String displayName;
    @Basic(optional = false)
    @Column(name = "team", nullable = false, length = 3)
    private String team;
    @Basic(optional = false)
    @Column(name = "position", nullable = false, length = 3)
    private String position;
    @Column(name = "height", length = 5)
    private String height;
    @Column(name = "weight", length = 3)
    private String weight;
    @Column(name = "dob", length = 10)
    private String dob;
    @Column(name = "college", length = 45)
    private String college;
    @Basic(optional = false)
    @Column(name = "retrieval_year", nullable = false)
    private int retrievalYear;

    public Player() {
    }

    public Player(String playerId) {
        this.playerId = playerId;
    }

    public Player(String playerId, String lname, String fname, String displayName, String team, String position, int retrievalYear) {
        this.playerId = playerId;
        this.lname = lname;
        this.fname = fname;
        this.displayName = displayName;
        this.team = team;
        this.position = position;
        this.retrievalYear = retrievalYear;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getJersey() {
        return jersey;
    }

    public void setJersey(String jersey) {
        this.jersey = jersey;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getRetrievalYear() {
        return retrievalYear;
    }

    public void setRetrievalYear(int retrievalYear) {
        this.retrievalYear = retrievalYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (playerId != null ? playerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.playerId == null && other.playerId != null) || (this.playerId != null && !this.playerId.equals(other.playerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.design.perpetual.fantasyfootball.app.entities.Player[ playerId=" + playerId + " ]";
    }
    
}
