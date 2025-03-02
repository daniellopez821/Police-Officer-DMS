import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
Daniel Lopez-Morales. Software Development 1. 2/22/2025
This is the main class. The purpose of this class is to house the main method and all the methods needed to run the DMS.
The main method will create a loop that will keep cycling until the user enters the number 7 to quit the program.
*/

public class mainClass {
    public static void main(String[] args) {
        //Arraylist to store police officers
        ArrayList<PoliceOfficer> officer = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String name;
        int badge = 0;
        String rank;
        int serviceYears = 0;
        String location;
        int arrests = 0;

        String fileName = "";
        int option = 0;
        boolean repeat = true;

        System.out.println("Menu:\n1.Add Officer By File\n2.Add Officer Manually\n3.Delete Officer\n4.Update Officer Info\n5.Display All Officers\n6.Can Officer Retire?\n7.Quit");

        // Beginning of the menu
        while(repeat){
            try{
                option = sc.nextInt();
                sc.nextLine();
                repeat = false;
            }catch(InputMismatchException e){
                System.out.println("Invalid option. Try again.");
                sc.nextLine();
            }
        }

        repeat = true;

        // Loop that will continue until the user hits 7
        while (option != 7){

            switch (option) {
                case 1:
                    System.out.println("Enter your file:");
                    while (repeat) {
                        try {
                            fileName = sc.nextLine();
                            repeat = false;
                        } catch (Exception e) {
                            System.out.println("Invalid option. Try again.");
                        }
                    }
                    operations.addByFile(officer, fileName);
                    break;
                case 2:
                    System.out.println("Enter the Officer Name:");
                    name = sc.nextLine();

                    System.out.println("Enter the badge number:");
                    while (repeat) {
                        try {
                            badge = sc.nextInt();
                            repeat = false;
                        } catch (InputMismatchException e) {
                            System.out.println("This is not a badge number. Please try again");
                            sc.nextLine();
                        }
                    }
                    sc.nextLine();
                    repeat = true;

                    System.out.println("Enter the rank:");
                    rank = sc.nextLine();

                    System.out.println("Enter the service years:");
                    while (repeat) {
                        try {
                            serviceYears = sc.nextInt();
                            repeat = false;
                        } catch (InputMismatchException e) {
                            System.out.println("This is not a number. Please try again");
                            sc.nextLine();
                        }
                    }
                    sc.nextLine();
                    repeat = true;

                    System.out.println("Enter the location:");
                    location = sc.nextLine();

                    System.out.println("Enter the number of arrests:");
                    while (repeat) {
                        try {
                            arrests = sc.nextInt();
                            sc.nextLine();
                            repeat = false;
                        } catch (InputMismatchException e) {
                            System.out.println("This is not a number. Please try again");
                            sc.nextLine();
                        }
                    }

                        operations.addManually(officer, name, badge, rank, serviceYears, location, arrests);
                        break;
                        case 3:
                            int userInput = 0;
                            int answer = 0;
                            repeat = true;
                            System.out.println("Enter the Police Officer Badge Number you want to delete:");
                            while(repeat){
                                try{
                                    userInput = sc.nextInt();
                                    sc.nextLine();
                                    repeat = false;
                                }catch (InputMismatchException e){
                                    System.out.println("This is not a badge number. Please try again");
                                    sc.nextLine();
                                }

                            }
                            repeat = true;
                            for (int i = 0; i < officer.size(); i++){
                                if (officer.get(i).getBadgeNumber() == userInput){
                                    System.out.println("Is this the Officer you want to delete? Enter 1 for YES or 2 for NO\n" + officer.get(i).toString());
                                    while(repeat){
                                        try{
                                            answer = sc.nextInt();
                                            sc.nextLine();
                                            repeat = false;
                                        }catch (InputMismatchException e){
                                            System.out.println("This is not an answer. Please try again");
                                            sc.nextLine();
                                        }

                                    }
                                }
                            }
                            repeat = true;
                            operations.deleteOfficer(officer, userInput, answer);
                            break;
                        case 4:
                            userInput = 0;
                            answer = 0;
                            repeat = true;
                            System.out.println("Enter the Police Officer Badge Number you want to Update:");
                            while(repeat){
                                try{
                                    userInput = sc.nextInt();
                                    sc.nextLine();
                                    repeat = false;
                                }catch (InputMismatchException e){
                                    System.out.println("This is not a badge number. Please try again");
                                    sc.nextLine();
                                }

                            }
                            repeat = true;

                            for (int i = 0; i < officer.size(); i++){
                                if (officer.get(i).getBadgeNumber() == userInput){
                                    System.out.println("What would you like to update?\n1.name\n2.rank\n3.service years\n4.location\n5.arrests number");
                                    while(repeat){
                                        try{
                                            answer = sc.nextInt();
                                            sc.nextLine();
                                            repeat = false;
                                        }catch (InputMismatchException e){
                                            System.out.println("This is not a answer. Please try again");
                                            sc.nextLine();
                                        }
                                    }
                                    repeat = true;
                                    if(answer == 1){
                                        System.out.println("Enter new name:");
                                        name = sc.nextLine();
                                        operations.updateNameInfo(officer, userInput, name);

                                    }else if(answer == 2){
                                        System.out.println("Enter new rank:");
                                        rank = sc.nextLine();
                                        operations.updateRankInfo(officer, userInput, rank);

                                    }else if(answer == 3){
                                        System.out.println("Enter new service years:");
                                        while (repeat){
                                            try{
                                                serviceYears = sc.nextInt();
                                                sc.nextLine();
                                                repeat = false;
                                            }catch (InputMismatchException e){
                                                System.out.println("This is not a number. Please try again");
                                                sc.nextLine();
                                            }
                                        }
                                        repeat = true;
                                        operations.updateServiceInfo(officer, userInput, serviceYears);

                                    }else if(answer == 4){
                                        System.out.println("Enter new location:");
                                        location = sc.nextLine();
                                        operations.updateLocationInfo(officer, userInput, location);

                                    }else if(answer == 5){
                                        System.out.println("Enter new arrests number:");
                                        while (repeat){
                                            try{
                                                arrests = sc.nextInt();
                                                sc.nextLine();
                                                repeat = false;
                                            }catch (InputMismatchException e){
                                                System.out.println("This is not a number. Please try again");
                                                sc.nextLine();
                                            }
                                        }
                                        repeat = true;
                                        operations.updateArrestInfo(officer, userInput, arrests);

                                    }
                                }
                            }
                            break;
                        case 5:
                            operations.displayOfficer(officer);
                            break;
                        case 6:
                            userInput = 0;
                            answer = 0;
                            repeat = true;
                            System.out.println("Enter the Police Officer Badge Number you want to delete:");
                            while(repeat){
                                try{
                                    userInput = sc.nextInt();
                                    sc.nextLine();
                                    repeat = false;
                                }catch (InputMismatchException e){
                                    System.out.println("This is not a badge number. Please try again");
                                    sc.nextLine();
                                }

                            }
                            System.out.println(operations.retireOfficer(officer, userInput));
                            break;
                    }

            repeat = true;
            System.out.println("Menu:\n1.Add Officer By File\n2.Add Officer Manually\n3.Delete Officer\n4.Update Officer Info\n5.Display All Officers\n6.Can Officer Retire?\n7.Quit");

            while(repeat){
                try{
                    option = sc.nextInt();
                    sc.nextLine();
                    repeat = false;
                }catch(InputMismatchException e){
                    System.out.println("Invalid option. Try again.");
                    sc.nextLine();
                }
            }
        }
    }
}
