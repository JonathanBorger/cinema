/* Small Cinema programm which lets u create a room and sell tickets
 *
 */

import java.util.Scanner;
public class Main {

    public static void buyTicket(int max, int rowP, int seatsP, char[][] arr) {
        boolean isSmall = (max < 60);
        int price;

        if (isSmall) {
            price = 10;
        } else {
            if (rowP <= (arr.length) / 2 ) {
                price = 10;
            } else {
                price = 8;
            }
        }
        System.out.println("Ticket price : $" + price);
        arr[rowP-1][seatsP-1] = 'B';
    }

    public static void showCinema(char[][] arr) {
        System.out.println("Cinema:");
        for (int i = 0; i <= arr[0].length; i++) {
            if (i == 0) System.out.print("  ");
            else System.out.print(i + " ");
        }
        System.out.println();
        int cnt = 1;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(cnt + " ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
            cnt++;
        }
        System.out.println();
    }

    public static char[][] createCinema(int a, int b) {
        char[][] cinema = new char[a][b];
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }
        return cinema;
    }

    public static void statistic(char[][] arr) {
        int total;
        int current;
        float percentage;
        int num = 0;
        int lowPricetickets = 0;
        int hightPricetickets = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'B') num++;        // counting the Tickets
                if (arr[i][j] == 'B' && i <= ((arr.length) / 2)) {
                    hightPricetickets++;
                } else if (arr[i][j] == 'B' && i > ((arr.length) / 2)) {
                    lowPricetickets++;
                }
            }
        }

        if ((arr[0].length) * (arr.length)< 60) {
            current = num * 10;
            total = (arr[0].length) * (arr.length) * 10;
        } else {
            current = lowPricetickets * 8 + hightPricetickets * 10;
            int firstHalf = (arr.length) / 2 * (arr[0].length)  * 10;
            int secondHalf = ((arr.length) - (arr.length) / 2) * (arr[0].length) * 8;
            total = firstHalf + secondHalf;
        }


        // percentage = total != 0 ? ((float)current / (float)total) * 100 : 0; percentage current income
        percentage = arr == null ? 0 : ((float)num / (float)((arr.length) * (arr[0].length))) * 100;

        System.out.printf("Number of purchased tickets: %d%n", num);
        System.out.printf("Percentage: %.2f%% %n", percentage);
        System.out.printf("current income: $%d%n", current);
        System.out.printf("Total income: $%d%n", total);
        System.out.println();
    }

    public static void main(String[] args) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Lets create a simple Cinema!");
        System.out.println("At first we need to create the room");
        System.out.println("Enter the number of rows:");
        int row = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        char[][] cinema = createCinema(row, seats);
        int allSeats= row * seats;
        boolean seatAvaiable = false;
        showCinema(cinema);

        while (!exit) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int selected = sc.nextInt();
            switch (selected) {
                case 1:
                    showCinema(cinema);
                    break;
                case 2:
                    seatAvaiable = false;
                    int cnt = 0;
                    while (!seatAvaiable && cnt < 5) {
                        System.out.println("Enter a row number:");
                        int rowPrice = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seatsPrice = sc.nextInt();
                        if (rowPrice > row || seatsPrice > seats) {
                            System.out.println("Wrong Input!");
                            cnt++;
                        } else if (cinema[rowPrice - 1][seatsPrice - 1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                            cnt++;
                        }
                        else {
                            seatAvaiable = true;
                            buyTicket(allSeats, rowPrice, seatsPrice, cinema);
                        }
                    } if (cnt >= 5) System.out.println("to many wrong Inputs, back to Menu");
                    break;
                case 3:
                    statistic(cinema);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a number between 0 and 3(inclusively)!");
                    break;
            }
        }
    }
}
