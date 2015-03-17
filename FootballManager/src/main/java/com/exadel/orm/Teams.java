package com.exadel.orm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Teams implements Serializable {
    private static final long serialVersionUID = 1L;
    /*-----private key-----*/
    @Id
    @Column(name = "teamID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    // auto_increment
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
    private int teamID;
    /*------------*/
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    /*------------*/

    @Column(name = "logo")
    private byte[] teamlogo;
    /*------------*/
      

    /* foreign key, join to Leagues */
    @ManyToOne(targetEntity = Leagues.class)
    @JoinColumn(name = "leagueID")
    @JsonManagedReference
    private Leagues lleague; // mapped

    /* foreign key, join to users */
    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "userID")
    @JsonManagedReference
    private Users uuser;

    //one team - many players
    @OneToMany(mappedBy = "pteam", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Lplayers> lplayers;

    
    /* setters */
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public void setLleague(Leagues lleague) {
        this.lleague = lleague;
    }

    public void setUser(Users user) {
        this.uuser = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamlogo(byte[] logo) {
        this.teamlogo = logo;
    }

    public void setLplayers(List<Lplayers> lplayers) {
        this.lplayers = lplayers;
    }

    /* getters */
    public List<Lplayers> getLplayers() {
        return lplayers;
    }

    public int getTeamID() {
        return teamID;
    }

    public Leagues getLleague() {
        return lleague;
    }

    public Users getUuser() {
        return uuser;
    }

    public String getName() {
        return name;
    }

    public byte[] getTeamlogo() {
        
      
        return teamlogo;
    }
    
    
    @SuppressWarnings("restriction")
    public String getByteArrayString()
    {
        String s="";
        if (teamlogo !=null) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
         s=encoder.encode(teamlogo);
        }
       return s;
    }





}
