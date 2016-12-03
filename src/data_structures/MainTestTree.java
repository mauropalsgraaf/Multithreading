package data_structures;

import data_structures.implementation.FineGrainedTree;

import java.util.ArrayList;

public class MainTestTree {

    public static void main(String[] args) {
        FineGrainedTree<Integer> list = new FineGrainedTree<>();

        ArrayList<Integer> list1 = list.toArrayList();

        list.add(2);

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
    }
}
