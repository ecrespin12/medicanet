package com.example.medicanet.data.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    public final long id;
    public final String name;
    public final String description;
    public final me.ibrahimsn.viewmodel.data.model.User owner;
    @SerializedName("stargazers_count")
    public final long stars;
    @SerializedName("forks_count")
    public final long forks;

    public Repo(long id, String name, String description, me.ibrahimsn.viewmodel.data.model.User owner, long stars, long forks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.stars = stars;
        this.forks = forks;
    }
}
