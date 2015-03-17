package com.exadel.orm;

/**
 * @author st02
 * 
 */
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Players implements Serializable {

    private static final long serialVersionUID = 1L;
    /* private key */
    @Id
    @Column(name = "playerID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
    private int playerID;
    /*--------------*/
    @Column(name = "firstname", nullable = false, length = 30)
    private String firstname;
    /*--------------*/
    @Column(name = "lastname", nullable = false, length = 30)
    private String lastname;
    /*--------------*/
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;
    /*--------------*/
    @Column(name = "height", nullable = false)
    private double height;
    /*--------------*/
    @Column(name = "weight", nullable = false)
    private double weight;
    /*--------------*/
    @Column(name = "photo", length = 100)
    private String photo;
    /*--------------*/
    @Column(name = "default_position", nullable = false, length = 10)
    private String defaultPosition;
    /*--------------*/
    @Column(name = "ovr")
    private int ovr;
    /*--------------*/
    @Column(name = "speed")
    private int speed;
    /*--------------*/
    @Column(name = "agility")
    private int agility;
    /*--------------*/
    @Column(name = "awareness")
    private int awareness;
    /*--------------*/
    @Column(name = "catching")
    private int catching;
    /*--------------*/
    @Column(name = "carrying")
    private int carrying;
    /*--------------*/
    @Column(name = "tackling")
    private int tackling;
    /*--------------*/
    @Column(name = "break_tackle")
    private int breakTackle;
    /*--------------*/
    @Column(name = "jumpimg")
    private int jumping;
    /*--------------*/
    @Column(name = "throw_power")
    private int throwPower;
    /*--------------*/
    @Column(name = "throw_accuracy")
    private int throwAccuracy;
    /*--------------*/
    @Column(name = "kicking_power")
    private int kickPower;
    /*--------------*/
    @Column(name = "kicking_accuracy")
    private int kickAccuracy;
    /*--------------*/
    @Column(name = "strength")
    private int strength;
    /*--------------*/

    // one player - many players in league
    @OneToMany(mappedBy = "pplayer", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Lplayers> lplayers;

    /* setters */

    public void setPlayerID(int id) {
        this.playerID = id;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDefaultPosition(String position) {
        this.defaultPosition = position;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setAwareness(int awareness) {
        this.awareness = awareness;
    }

    public void setCatching(int catching) {
        this.catching = catching;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    public void setTackling(int tackling) {
        this.tackling = tackling;
    }

    public void setBreakTackle(int breakTackle) {
        this.breakTackle = breakTackle;
    }

    public void setJumping(int jumping) {
        this.jumping = jumping;
    }

    public void setThrowPower(int throwPower) {
        this.throwPower = throwPower;
    }

    public void setThrowAccuracy(int throwAccuracy) {
        this.throwAccuracy = throwAccuracy;
    }

    public void setKickPower(int kickPower) {
        this.kickPower = kickPower;
    }

    public void setKickAccuracy(int kickAccuracy) {
        this.kickAccuracy = kickAccuracy;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    /* getters */
    public int getPlayerID() {
        return playerID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDefaultPosition() {
        return defaultPosition;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAgility() {
        return agility;
    }

    public int getAwareness() {
        return awareness;
    }

    public int getCatching() {
        return catching;
    }

    public int getCarrying() {
        return carrying;
    }

    public int getTackling() {
        return tackling;
    }

    public int getBreakTackle() {
        return breakTackle;
    }

    public int getJumping() {
        return jumping;
    }

    public int getThrowPower() {
        return throwPower;
    }

    public int getThrowAccuracy() {
        return throwAccuracy;
    }

    public int getKickPower() {
        return kickPower;
    }

    public int getKickAccuracy() {
        return kickAccuracy;
    }

    public int getStrength() {
        return strength;
    }

    public List<Lplayers> getLplayers() {
        return lplayers;
    }

    public void setLplayers(List<Lplayers> lplayers) {
        this.lplayers = lplayers;
    }

	public int getOvr() {
		return ovr;
	}

	public void setOvr(int ovr) {
		this.ovr = ovr;
	}

}
