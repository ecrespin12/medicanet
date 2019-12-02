package com.example.medicanet.data.rest;

import com.example.medicanet.data.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RepoRepository {

    private final com.example.medicanet.data.rest.RepoService repoService;

    @Inject
    public RepoRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<Repo>> getRepositories() {
        return repoService.getRepositories();
    }

    public Single<Repo> getRepo(String owner, String name) {
        return repoService.getRepo(owner, name);
    }
}
