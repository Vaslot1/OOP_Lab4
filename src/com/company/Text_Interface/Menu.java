package com.company.Text_Interface;

import com.company.Exceptions.MyException;
import com.company.Food.Food;
import com.company.Food.Fridge;
import com.company.Food.Fruits;
import com.company.Food.Vegetables;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static Fridge fridge = new Fridge();
    private static Scanner in = new Scanner(System.in);
    private static int count_Veg=0;
    private static int count_Fruits=0;

    public static void mainMenu() {
        fridge = getSavedFridge();
        int i = 0;
        String str;
        boolean a = true;
        while (a) {
            System.out.println("1 - �������� ��� � �����������");
            System.out.println("2 - ����� ��� �� ������������");
            System.out.println("3 - ����� � ���������");

            str = in.nextLine();
            i = Menu.checkException(str);
            if (i == 1 || i == 2) {
                if (i == 1) {
                    addMenu();
                }
                if (i == 2) {
                    takeMenu();
                }

            } else if (i == 3) {
                saveFridge();
                a = false;
            } else System.out.println("������ ������ �� ����������");
        }

    }

    private static Fridge getSavedFridge() {
        Fridge tempFridge = new Fridge();
        try {
            FileInputStream fis = new FileInputStream("Fridge.fr");
            ObjectInputStream ois = new ObjectInputStream(fis);
            count_Veg = ois.readInt();
            count_Fruits= ois.readInt();
            tempFridge = (Fridge) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempFridge;
    }

    private static void saveFridge() {
        try {
            FileOutputStream fos = new FileOutputStream("Fridge.fr");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(Vegetables.getCount()+count_Veg);
            oos.writeInt(Fruits.getCount()+count_Fruits);
            oos.writeObject(fridge);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void takeMenu() {
        float _Weight;
        int i = 0;
        String str;
        boolean a = true;
        while (a) {
            if (fridge.size() > 0) {
                System.out.println("���������� ������ - " + (count_Veg+Vegetables.getCount()) + "\t" + "���������� ������� - " + (count_Fruits+Fruits.getCount()));
                for (int j = 0; j < fridge.size(); j++) {
                    System.out.println((j + 1) + " - " + fridge.name(j));
                }
                System.out.println(fridge.size() + 1 + " - ���������� ���������� ��������");
                System.out.println(fridge.size() + 2 + " - ����� � ������� ����");

                str = in.nextLine();
                i = Menu.checkException(str);

                if (i > 0 && i <= fridge.size()) {
                    do {
                        System.out.println("������� �� ������ �����(��)?");
                        str = in.nextLine();
                        _Weight = Menu.checkPositiveFloat(str);
                    } while (_Weight > fridge.get(i - 1).getWeight());
                    if (fridge.get(i - 1) instanceof Vegetables)
                        actionVeg(fridge.get(i - 1), _Weight);


                    else if (fridge.get(i - 1) instanceof Fruits)
                        actionFruit(fridge.get(i - 1), _Weight);


                } else if (i == fridge.size() + 1)
                    fridge.sort();
                else if (i == fridge.size() + 2)
                    a = false;
                else System.out.println("������ ������ �� ����������");
            } else {
                System.out.println("��� ����������� ����!");
                a = false;
            }


        }
    }

    private static void actionFruit(Food food, float _weight)   {
        int i = 0;
        String str;
        boolean a = true;

        if (_weight <= food.getWeight())
            while (a) {

                Fruits tempFood = new Fruits(food.getCalorie_Value(), _weight, food.getName());
                System.out.println(tempFood.toString());

                System.out.println("1 - ������");
                System.out.println("2 - ��������");
                System.out.println("3 - �������� �������");
                str = in.nextLine();
                i = Menu.checkException(str);

                switch (i) {
                    case 1:
                        tempFood.eat();
                        food.setWeight(food.getWeight() - _weight);
                        if (food.getWeight() == 0)
                            fridge.remove(food);
                        a = false;
                        break;
                    case 2:
                        food.setWeight(food.getWeight() - _weight);
                        if (food.getWeight() == 0)
                            fridge.remove(food);
                        a = false;
                        break;
                    case 3:
                        a = false;
                        break;
                    default: {
                        System.out.println("������ ������ �� ����������");
                    }
                }
            }
        else {
            System.out.println("�������� ����� ����� " + food.getWeight() + " ��");
        }
    }

    private static void actionVeg(Food food, float _weight) {
        int i = 0;
        String str;
        boolean a = true;
        int stage = ((Vegetables) food).getStage();

        if (_weight <= food.getWeight()) {
            Vegetables tempFood = new Vegetables(food.getCalorie_Value(), _weight, food.getName(), stage);
            while (a) {


                System.out.println(tempFood.toString());

                System.out.println("1 - ������");
                System.out.println("2 - ��������");
                System.out.println("3 - �������");
                System.out.println("4 - ��������");
                System.out.println("5 - �������� � �����������");
                str = in.nextLine();
                i = Menu.checkException(str);
                switch (i) {
                    case 1:
                        tempFood.eat();
                        food.setWeight(food.getWeight() - _weight);
                        if (food.getWeight() == 0) {
                            fridge.remove(food);
                            Vegetables.updateCount(Vegetables.getCount()-1);
                        }
                        a = false;
                        break;
                    case 2:
                        tempFood.fry();
                        stage = tempFood.getStage();
                        break;
                    case 3:
                        tempFood.boil();
                        stage = tempFood.getStage();
                        break;
                    case 4:
                        food.setWeight(food.getWeight() - _weight);
                        if (food.getWeight() == 0) {
                            fridge.remove(food);
                            Vegetables.updateCount(Vegetables.getCount()-1);

                        }
                        a = false;
                        break;
                    case 5:
                        if (_weight == food.getWeight() || tempFood.getStage() == ((Vegetables) food).getStage()) {
                            ((Vegetables) food).setStage(tempFood.getStage());
                            Vegetables.updateCount(Vegetables.getCount()-1);
                        } else {
                            fridge.add(tempFood);
                            food.setWeight(food.getWeight() - _weight);
                        }
                        a = false;
                        break;
                    default: {
                        System.out.println("������ ������ �� ����������");
                    }
                }
            }
        } else {
            System.out.println("�������� ����� ����� " + food.getWeight() + " ��");
        }
    }

    private static void addMenu() {
        int i = 0;
        String str;
        boolean a = true;
        while (a) {
            System.out.println("1 - �������� ����� � �����������");
            System.out.println("2 - �������� ����� � �����������");
            System.out.println("3 - ����� � ������� ����");

            str = in.nextLine();
            i = Menu.checkException(str);
            if (i == 1 || i == 2) {
                if (i == 1) {
                    System.out.println("������� �������� �����: ");
                    str = in.nextLine();
                    System.out.println("������� ���(��): ");
                    String str1 = in.nextLine();
                    int weight = checkPositive(str1);
                    System.out.println("������� �������������(���� �� 100�): ");
                    String str2 = in.nextLine();
                    int calories = checkPositive(str2);

                    fridge.add(new Vegetables(calories, weight, str, 0));
                }
                if (i == 2) {
                    System.out.println("������� �������� ������: ");
                    str = in.nextLine();
                    System.out.println("������� ���(��): ");
                    String str1 = in.nextLine();
                    int weight = checkPositive(str1);
                    System.out.println("������� �������������(���� �� 100�): ");
                    String str2 = in.nextLine();
                    int calories = checkPositive(str2);


                    fridge.add(new Fruits(calories, weight, str));
                }

            } else if (i == 3)
                a = false;
            else System.out.println("������ ������ �� ����������");
        }

    }

    private static int checkException(String str) {
        boolean turnON = true;
        while (turnON)
            try {
                int i = Integer.parseInt(str.trim());
                turnON = false;
                return i;
            } catch (NumberFormatException | IndexOutOfBoundsException nfe) {
                System.out.println("������ ������� �����");
                str = in.nextLine();
            } catch (NullPointerException nfe) {
                System.out.println("����� ������ ���-������");
                str = in.nextLine();
            } catch (RuntimeException ex) {

            }
        return 0;
    }

    private static int positiveExec(String str) throws MyException {
        int i = Integer.parseInt(str.trim());
        if (i <= 0)
            throw new MyException("��� �������� �� ����� ���� ������ ��� ����� ����");
        else {
            return i;
        }
    }

    private static float positiveExecFloat(String str) throws MyException {
        float i = Float.parseFloat(str.trim());
        if (i <= 0)
            throw new MyException("��� �������� �� ����� ���� ������ ��� ����� ����");
        else {
            return i;
        }
    }

    private static float checkPositiveFloat(String str) {
        boolean turnON = true;
        while (turnON)
            try {
                float i = positiveExecFloat(str);
                turnON = false;
                return i;
            } catch (NumberFormatException nfe) {
                System.out.println("������ ������� �����");
                str = in.nextLine();
            } catch (NullPointerException nfe) {
                System.out.println("������� ���-������");
                str = in.nextLine();
            } catch (MyException e) {
                System.out.println(e.getMessage());
                str = in.nextLine();
            }
        return 0;
    }

    private static int checkPositive(String str) {
        boolean turnON = true;
        while (turnON)
            try {
                int i = positiveExec(str);
                turnON = false;
                return i;
            } catch (NumberFormatException nfe) {
                System.out.println("������ ������� �����");
                str = in.nextLine();
            } catch (NullPointerException nfe) {
                System.out.println("������� ���-������");
                str = in.nextLine();
            } catch (MyException e) {
                System.out.println(e.getMessage());
                str = in.nextLine();
            }
        return 0;
    }
}
