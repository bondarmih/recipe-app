package ru.bondarmih.recipeparser.data.domain;

/**
 * Created by bondarm on 20.04.18.
 */
public class Instruction {

    private String text;
    private String imgPath;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
