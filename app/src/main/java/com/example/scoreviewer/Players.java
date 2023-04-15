package com.example.scoreviewer;

import org.json.JSONObject;

public class Players {
    private Integer id;
    private String firstname;
    private String surname;
    private String mobile;
    private Integer matches;
    private JSONObject matchdata;
    private Integer winpercent;

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getMatches() {
        return matches;
    }

    public JSONObject getMatchdata() {
        return matchdata;
    }

    public Integer getWinpercent() {
        return winpercent;
    }

    public Players(Integer id, String firstname, String surname, String mobile, Integer matches, JSONObject matchdata, Integer winpercent) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.mobile = mobile;
        this.matches = matches;
        this.matchdata = matchdata;
        this.winpercent = winpercent;
    }

    public Players(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }
}
