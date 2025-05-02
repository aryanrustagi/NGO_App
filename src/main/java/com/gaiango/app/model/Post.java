package com.gaiango.app.model;

public class Post {
    private String title;
    private String username;
    private String content;
    private String image;
    private String peopleNo;
    private String tags;
    private String category;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getPeopleNo() { return peopleNo; }
    public void setPeopleNo(String peopleNo) { this.peopleNo = peopleNo; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
