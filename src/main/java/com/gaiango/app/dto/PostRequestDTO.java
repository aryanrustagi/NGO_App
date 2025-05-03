package com.gaiango.app.dto;

public class PostRequestDTO {
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    
    @NotEmpty(message = "Content cannot be empty")
    private String content;

    private String peopleNo;
    private String tags;
    private String category;
    private String imageName;
    private String imageType;
    
    @NotEmpty(message = "Image data cannot be empty")
    private String imageDate; // Base64-encoded image string

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getPeopleNo() { return peopleNo; }
    public void setPeopleNo(String peopleNo) { this.peopleNo = peopleNo; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public String getImageType() { return imageType; }
    public void setImageType(String imageType) { this.imageType = imageType; }

    public String getImageDate() { return imageDate; }
    public void setImageDate(String imageDate) { this.imageDate = imageDate; }
}
