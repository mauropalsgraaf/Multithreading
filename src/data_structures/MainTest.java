package data_structures;

import data_structures.implementation.CoarseGrainedList;

import java.util.ArrayList;

public class MainTest {

    public static void main(String[] args) {
        CoarseGrainedList<Integer> list = new CoarseGrainedList<>();

        ArrayList<Integer> list1 = list.toArrayList();

        list.add(4);

        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.add(1);

        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.add(2);

        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.add(5);

        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.add(3);

        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.add(3);

        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.add(3);

        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.remove(3);
        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.remove(3);
        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.remove(3);
        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.remove(3);
        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);

        list.remove(4);
        list1 = list.toArrayList();
        System.out.println("list1 = " + list1);
    }
}
