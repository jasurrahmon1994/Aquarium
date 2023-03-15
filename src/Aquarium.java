import java.util.LinkedList;
import java.util.Scanner;

public class Aquarium {
    static int size= 0;
    static int number_of_fishes = 1000;
    static int number_of_moves = 0;

    static LinkedList<Location> location_list = new LinkedList<Location>();

    public static void main(String[] args) {

        Scanner scn= new Scanner(System.in);

        System.out.println("Please enter the size of the aquarium :");

        size=scn.nextInt();

        while(number_of_fishes >= Math.pow(size,2)){
            System.out.println("Please enter the correct number of fishes in the aquarium , MAx allowed fishes are 2 to the power size of Aquarium: ");
            number_of_fishes=scn.nextInt();
        }

        System.out.println("Please enter the Number of Minimum Moves for each of the fishes :");

        number_of_moves=scn.nextInt();

        Fish[] fishes = new Fish[number_of_fishes];

        Thread[] thr = new Thread[number_of_fishes];

        for (int i=0;i<number_of_fishes;i++){

            fishes[i]= new Fish(size, number_of_moves ,i);

            thr[i]= new Thread(fishes[i]);

            thr[i].start();
        }

        try {
            for (int i=0;i<number_of_fishes;i++)
                thr[i].join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Final Location list goes like : ");

        for(Location loc : location_list){
            System.out.println(loc.x + " , " + loc.y);
        }
    }

}
