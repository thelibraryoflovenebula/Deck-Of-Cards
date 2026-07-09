import java.util.Arrays;
import java.util.Scanner;


public class EXAMPLE {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] foods;
        int size;



        System.out.print("How much food do you want: ");
        size  = scan.nextInt();
        scan.nextLine();

        foods = new String[size];

        for (int i = 0; i < foods.length; i++) {
            int show = i + 1;
            System.out.printf("Enter food please for %d's food: ", show);
            foods[i] = scan.nextLine();
        }


        for(String food : foods) {
            System.out.println(food);
        }



    }
}
