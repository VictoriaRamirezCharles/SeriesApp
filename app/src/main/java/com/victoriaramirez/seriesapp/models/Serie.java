package com.victoriaramirez.seriesapp.models;

public class Serie {

    private String Id;
    private String Name;
    private Image Image;
    private String Status;

    public Serie(String name, String status) {
        this.Name = name;
        this.Status = status;
    }
    public Serie() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
