package data_structures;

import data_structures.implementation.CoarseGrainedList;
import data_structures.implementation.FineGrainedList;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainTest {

    public static void main(String[] args) {
        FineGrainedList<Integer> list = new FineGrainedList<>();

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
