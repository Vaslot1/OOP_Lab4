package com.company.Food;

import com.company.Food.Food;

public class Fruits extends Food {
    private static int count_Fruits;
    public Fruits(float _calorie_Value, float _weight, String _name) {
        super(_calorie_Value, _weight, _name);
        count_Fruits++;
    }

    @Override
    public void eat() {
        System.out.println("�� ����� - "+ this.toString());
        System.out.println("� �������� "+calorie_Value*10*weight+" ����");
        count_Fruits--;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        result.append(name + " ");
        result.append(weight + " - �� ");
        result.append(calorie_Value + " - ���� ");
        return result.toString();
    }
    public static int getCount(){
        return count_Fruits;
    }
    public static void updateCount(int _Count){
        count_Fruits=_Count;
    }
}
