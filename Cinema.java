import java.util.Scanner;

public class Cinema {
    public static void statistics(int total, int[][] seats, int x, int y){
        int seatsNumb = 0;
        int currentIn = 0;
        int totalIn = 0;
        
        for (int h = 0; h < y; h++) {
            for (int v = 0; v < x; v++) {
                if(h > (y/2) - 1){
                    totalIn += 8;
                } else {
                    totalIn += 10;
                }
                if(seats[v][h] == 1 && seats[v][h] == 1){
                    seatsNumb++;
                    if(h > (y/2) - 1){
                        currentIn += 8;
                    } else {
                        currentIn += 10;
                    }
                    continue;
                }
            }
        }

        System.out.println("Number of purchased tickets: " + seatsNumb);
        if (seatsNumb > 0) {
            float perc = (float)(seatsNumb * 100f) / total; 
            System.out.printf("Percentage: %.2f%s", perc, "%");
        } else {
            System.out.print("Percentage: 0.00%");
        }
        
        System.out.println("\nCurrent income: $" + currentIn);
        System.out.println("Total income: $" + totalIn);
    }

    public static void showSeats(int x, int y, int[][] seats){
        System.out.println("\nCinema:");
        System.out.print("  1");

        for (int i = 2; i <= x; i++) {
            System.out.print(" " + i);
        }

        System.out.println();

        for (int h = 0; h < y; h++) {
            System.out.print(h+1);
            for (int v = 0; v < x; v++) {
                if(seats[v][h] == 1 && seats[v][h] == 1){
                    System.out.print(" B");
                    continue;
                }

                System.out.print(" S");
            }
            System.out.println();
        }
    }

    public static void buy(int total, int seatY, int x, int y) {
        System.out.println("\nTicket price: $" + (total < 60 ? 10
                                  : seatY > (y/2) ? 8 : 10));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int op;

        System.out.println("Enter the number of rows:");
        int y = scanner.nextInt();
            
        System.out.println("Enter the number of seats in each row:");
        int x = scanner.nextInt();

        int total = x * y;

        int[][] seats = new int[x][y];
        
        do {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            
            op = scanner.nextInt();

            switch (op) {
                case 1: 
                    showSeats(x, y, seats);
                    break;
                case 2:
                    boolean end;
                    do {
                        System.out.println("\nEnter a row number:");
                        int seatY = scanner.nextInt();
                            
                        System.out.println("Enter a seat number in that row:");
                        int seatX = scanner.nextInt();
                        
                        if (seatY <= y && seatX <= x) {
                            if (seats[seatX-1][seatY-1] == 0) {
                                seats[seatX-1][seatY-1] = 1;
                                
                                buy(total, seatY, x, y);
                                end = true;
                            } else {
                                System.out.println("That ticket has already been purchased!");
                                end = false;
                            }
                        } else {
                            System.out.println("Wrong input!");
                            end = false;
                        }
                        
                    } while(end == false);
                    break;
                case 3: 
                    statistics(total, seats, x, y);
                    break;
                default:
                    break;
            }
        } while(op != 0);
    }
}
