package com.company.Food;

import com.company.Food.Food;

public class Vegetables extends Food {
    private static int count_Vegs;
    private int stage = 0;


    public Vegetables(float _calorie_Value, float _weight, String _name,int _stage) {
        super(_calorie_Value, _weight, _name);
        this.stage=_stage;
        count_Vegs++;
    }

    @Override
    public void eat() {
        String stageVeg="";
        if (stage == 0)
            stageVeg = "Сырая";
        else if (stage == 1)
            stageVeg = "Жаренная";
        else if (stage == 2)
            stageVeg = "Варённая";
        else if(stage==3)
            stageVeg = "Жаренная и варенная";

        System.out.println("Вы съели - " + this.toString());
        System.out.println("И получили "+calorie_Value*10*weight+" Ккал");
        count_Vegs--;
    }
    @Override
    public String toString() {
        String stageVeg="";
        if (stage == 0)
            stageVeg = "Сырая";
        else if (stage == 1)
            stageVeg = "Жаренная";
        else if (stage == 2)
            stageVeg = "Варённая";
        else if(stage==3)
            stageVeg = "Жаренная и варенная";

        StringBuilder result = new StringBuilder("");
        result.append(stageVeg+" "+name+" ");
        result.append(weight+" Кг ");
        result.append(calorie_Value + " Ккал");
        return result.toString();
    }



    public void fry() {
        if (stage != 1 && stage != 3) {
            if (stage == 2) {
                this.calorie_Value *= 2f;
                this.stage = 3;
            } else {
                this.calorie_Value *= 2f;
                this.stage = 1;
            }

        } else
            System.out.println("Этот овощь уже пожарен");
    }

    public void boil() {
        if (stage != 2 && stage != 3) {
            if (stage == 1) {
                this.calorie_Value *= 1.25f;
                this.stage = 3;
            } else {
                this.calorie_Value *= 1.25f;
                this.stage = 2;
            }
        } else System.out.println("Этот овощь уже сварен");
    }
    public void setStage(int _stage){
        this.stage=_stage;
    }
    public int getStage(){
        return stage;
    }
    public static int getCount(){
        return count_Vegs;
    }
    public static void updateCount(int _Count){
        count_Vegs=_Count;
    }
}
