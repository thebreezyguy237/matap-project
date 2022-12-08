/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.image.Image;

/**
 *
 * @author HP
 */
public class dashboardThumb {
    
    private int thumbStat;
    private String thumbImgSrc;
    private String thumbText;   
    private String thumbQuestion;
    private String thumbButtonText;

    public dashboardThumb() {
    }

    public void setThumbStat(int thumbStat) {
        this.thumbStat = thumbStat;
    }

    public void setThumbImgSrc(String thumbImgSrc) {
        this.thumbImgSrc = thumbImgSrc;
    }

    public void setThumbText(String thumbText) {
        this.thumbText = thumbText;
    }

    public void setThumbQuestion(String thumbQuestion) {
        this.thumbQuestion = thumbQuestion;
    }

    public void setThumbButtonText(String thumbButtonText) {
        this.thumbButtonText = thumbButtonText;
    }
    
    
    
    public int getThumbStat() {
        return thumbStat;
    }

    public String getThumbImgSrc() {
        return thumbImgSrc;
    }

    public String getThumbText() {
        return thumbText;
    }

    public String getThumbQuestion() {
        return thumbQuestion;
    }

    public String getThumbButtonText() {
        return thumbButtonText;
    }
    
    
    
}
