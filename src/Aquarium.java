import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Aquarium {

    static int size;
    static int numberOfFishes;
    static int numberOfMoves;

    static final LinkedList<Fish> fishList = new LinkedList<>();
    static List<Fish> childFishList = new ArrayList<>();

    static
    {
        size = (int) (Math.random() * 10) + 10;
        numberOfFishes = (int) (Math.random() * size);
        numberOfMoves = (int) (Math.random() * 5) + 5;
    }
    public static void main(String[] args) {

        System.out.println("Initialized the following static variables: ");
        System.out.println("Aquarium size: " + size);
        System.out.println("The number of fishes in aquarium: " + numberOfFishes);
        System.out.println("The lifespan of each fish is " + numberOfMoves);

        Fish[] fishes = new Fish[numberOfFishes];

        Thread[] thr = new Thread[numberOfFishes];

        for (int i = 0; i < numberOfFishes; i++) {
            Gender gender = Gender.getRandomGender((int)(Math.random() * size + i));

            fishes[i] = new Fish(size, numberOfMoves, "Fish " + (i + 1), gender);

            thr[i] = new Thread(fishes[i]);

            thr[i].start();
        }

        try {
            for (int i = 0; i < numberOfFishes; i++)
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