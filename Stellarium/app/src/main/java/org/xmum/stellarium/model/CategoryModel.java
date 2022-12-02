package org.xmum.stellarium.model;

public class CategoryModel {
    private String cid;
    private String name;
    private String url;
    private int time;
    private int noOfQuestions;

    private int bestScore;
    // TODO: how to get best score???


    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public CategoryModel(){}

    public CategoryModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
