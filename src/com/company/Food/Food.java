package com.company.Food;

import java.io.Serializable;

public abstract class Food implements Serializable {
    protected String name;
    protected float calorie_Value;
    protected float weight;

    Food(float _calorie_Value, float _weight, String _name) {
        this.calorie_Value = _calorie_Value;
        this.weight = _weight;
        this.name = _name;

    }

    public abstract void eat();

    public abstract String toString();

    public float getCalorie_Value() {
        return calorie_Value;
    }

    public float getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}