/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.comm.dto;

import java.util.List;

/**
 *
 * @author gcatanese
 */
public class HeroCard {

    private String type = "application/vnd.microsoft.card.hero";

    private String title;
    private String text;
    private List<HeroCardButton> buttons;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<HeroCardButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<HeroCardButton> buttons) {
        this.buttons = buttons;
    }

}
