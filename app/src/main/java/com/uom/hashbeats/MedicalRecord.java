package com.uom.hashbeats;

public class MedicalRecord {
    private String title;
    private String descr;
    private int type;//pres=0,docs note=1,report=2
    private int date;
    private String dateFull;
    private Integer image;

    public MedicalRecord(String title, String descr, int type, int date, String dateFull, Integer image) {
        this.title = title;
        this.descr = descr;
        this.type = type;
        this.date = date;
        this.dateFull = dateFull;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDateFull() {
        return dateFull;
    }

    public void setDateFull(String dateFull) {
        this.dateFull = dateFull;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
