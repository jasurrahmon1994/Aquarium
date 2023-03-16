import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Aquarium {

    static int size;
    static int number_of_fishes;
    static int number_of_moves;

    static final LinkedList<Fish> fishList = new LinkedList<>();
    static List<Fish> childFishList = new ArrayList<>();

    static
    {
        size = (int) (Math.random() * 10) + 5;
        number_of_fishes = (int) (Math.random() * Math.pow(size, 2));
        number_of_moves = (int) (Math.random() * 5) + 5;
    }
    public static void main(String[] args) {

        System.out.println("Initialized the following static variables: ");
        System.out.println("Aquarium size: " + size);
        System.out.println("The number of fishes in aquarium: " + number_of_fishes);
        System.out.println("The lifespan of each fish is " + number_of_moves);

        Fish[] fishes = new Fish[number_of_fishes];

        Thread[] thr = new Thread[number_of_fishes];

        for (int i = 0; i < number_of_fishes; i++) {
            Gender gender = Gender.getRandomGender((int)(Math.random() * size + i));

            fishes[i] = new Fish(size, number_of_moves, "Fish " + (i + 1), gender);

            thr[i] = new Thread(fishes[i]);

            thr[i].start();
        }

        try {
            for (int i = 0; i < number_of_fishes; i++)
                thr[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final Parent Fish Location list goes like : ");

        for (Fish fish : fishList) {
            System.out.println(fish.getFishName() + " is staying at " + fish.getLocation());
        }
        System.out.println("Child Fish Location list goes like : ");

        for (Fish child : childFishList) {
            System.out.println(child.getFishName() + " is staying at " + child.getLocation());
        }
    }
}