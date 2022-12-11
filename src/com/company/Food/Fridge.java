package com.company.Food;

import com.company.Food.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Fridge implements Serializable {
    private final List<Food> list = new ArrayList<>();  

    public Fridge() {
    }

    public void add(Food food) {
        list.add(food);
    }

    public Food get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public String name(int index) {
        return list.get(index).toString();
    }

    public void remove(Food food) {
        list.remove(food);
    }

    public void sort() {

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Food food1 = list.get(i);
                Food food2 = list.get(j);
                if (food1.getCalorie_Value() == food2.getCalorie_Value() &&
                        food1.getName().equals(food2.getName()) &&
                        food1 instanceof Vegetables &&
                        food2 instanceof Vegetables &&
                        ((Vegetables) food1).getStage() == ((Vegetables) food2).getStage()) {

                    list.get(i).setWeight(list.get(i).getWeight() + list.get(j).getWeight());
                    list.remove(list.get(j));
                } else if (food1.getCalorie_Value() == food2.getCalorie_Value() &&
                        food1.getName().equals(food2.getName()) &&
                        food1 instanceof Fruits &&
                        food2 instanceof Fruits) {
                    list.get(i).setWeight(list.get(i).getWeight() + list.get(j).getWeight());
                    list.remove(list.get(j));
                }
            }
        }
    }

}
