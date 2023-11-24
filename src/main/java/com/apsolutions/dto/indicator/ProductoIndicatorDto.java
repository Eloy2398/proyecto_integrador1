package com.apsolutions.dto.indicator;

public class ProductoIndicatorDto {

    private String name;
    private int y;
    private int initial;
    private Long income;
    private Long output;
    private boolean sliced;
    private boolean selected;

    public ProductoIndicatorDto(String name, int y, Long income, Long output) {
        this.name = name;
        this.y = y;
        this.income = income;
        this.output = output;
        this.sliced = false;
        this.selected = false;
    }

    public String getName() {
        return name;
    }

    public int getY() {
        return y;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public Long getIncome() {
        return income;
    }

    public Long getOutput() {
        return output;
    }

    public boolean isSliced() {
        return sliced;
    }

    public void setSliced(boolean sliced) {
        this.sliced = sliced;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
