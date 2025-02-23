import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mainClass {
    public static void main(String[] args) {

        ArrayList<PoliceOfficer> officer = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        int option = 0;

        System.out.println("Menu:\n1.Add Officer By File\n2.Add Officer Manually\n3.Delete Officer\n4.Update Officer Info\n5.Display All Officers\n6.Can Officer Retire?\n7.Quit");
        option = sc.nextInt();
        sc.nextLine();

        while (option != 7){

            switch (option){
                case 1:
                    addByFile(officer);
                break;
                case 2:
                    addManually(officer);
                break;
                case 3:
                    deleteOfficer(officer);
                break;
                case 4:
                    updateInfo(officer);
                break;
                case 5:
                    displayOfficer(officer);
                break;
                case 6:
                    retireOfficer(officer);
                break;
            }

            System.out.println("Menu:\n1.Add Officer By File\n2.Add Officer Manually\n3.Delete Officer\n4.Update Officer Info\n5.Display All Officers\n6.Can Officer Retire?\n7.Quit");
            option = sc.nextInt();
            sc.nextLine();
        }
    }

    public static void addByFile(ArrayList<PoliceOfficer> police){
        Scanner sc = new Scanner(System.in);

        boolean repeat = true;
        while(repeat){
            System.out.println("Enter your file:");
            String fileName = sc.nextLine();

            File file = new File(fileName);

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] words = line.split("-");
                    police.add(new PoliceOfficer(words[0], Integer.parseInt(words[1]), words[2], Integer.parseInt(words[3]), words[4], Integer.parseInt(words[5])));
                }
                repeat = false;
            } catch (IOException e) {
                System.out.println("That is not a correct file path. Try again");
                sc.nextLine();
            }
        }
        repeat = true;
    }

    public static void addManually(ArrayList<PoliceOfficer> police){
        String name;
        int badge = 0;
        String rank;
        int serviceYears = 0;
        String location;
        int arrests = 0;
        boolean repeat = true;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Officer Name:");
        name = sc.nextLine();

        System.out.println("Enter the badge number:");
        while(repeat){
            try{
                badge = sc.nextInt();
                repeat = false;
            }catch (InputMismatchException e){
                System.out.println("This is not a badge number. Please try again");
                sc.nextLine();
            }
        }
        sc.nextLine();
        repeat = true;

        System.out.println("Enter the rank:");
        rank = sc.nextLine();

        System.out.println("Enter the service years:");
        while(repeat){
            try{
                serviceYears = sc.nextInt();
                repeat = false;
            }catch (InputMismatchException e){
                System.out.println("This is not a number. Please try again");
                sc.nextLine();
            }
        }
        sc.nextLine();
        repeat = true;

        System.out.println("Enter the location:");
        location = sc.nextLine();

        System.out.println("Enter the number of arrests:");
        while(repeat){
            try{
                arrests = sc.nextInt();
                repeat = false;
            }catch (InputMismatchException e){
                System.out.println("This is not a number. Please try again");
                sc.nextLine();
            }
        }
        police.add(new PoliceOfficer(name, badge, rank, serviceYears, location, arrests));
    }

    public static void deleteOfficer(ArrayList<PoliceOfficer> police){
        Scanner sc = new Scanner(System.in);
        String userInput;
        int answer;
        System.out.println("Enter the Police Officer Badge Number you want to delete:");
        userInput = sc.next();
        boolean repeat = true;
        while(repeat){
            for (int i = 0; i < police.size(); i++){
                if (police.get(i).getBadgeNumber() == Integer.parseInt(userInput)){
                    System.out.println("Is this the Officer you want to delete? Enter 1 for YES or 2 for NO\n" + police.get(i).toString());
                    answer = sc.nextInt();
                    repeat = false;
                    if(answer == 1){
                        police.remove(i);
                    }
                }
            }
        }
    }

    public static void updateInfo(ArrayList<PoliceOfficer> police){
        Scanner sc = new Scanner(System.in);
        String userInput;
        int answer;
        System.out.println("Enter the Police Officer Badge Number you want to update:");
        userInput = sc.next();
        boolean repeat = true;
        String name;
        String rank;
        int service;
        String location;
        int arrests;
        while(repeat){
            for (int i = 0; i < police.size(); i++){
                if (police.get(i).getBadgeNumber() == Integer.parseInt(userInput)){
                    System.out.println("What would you like to update?\n1.name\n2.rank\n3.service years\n4.location\n5.arrests number");
                    answer = sc.nextInt();
                    repeat = false;
                    if(answer == 1){
                        System.out.println("Enter new name:");
                        name = sc.next();
                        police.get(i).setName(name);
                    }else if(answer == 2){
                        System.out.println("Enter new rank:");
                        rank = sc.next();
                        police.get(i).setRank(rank);
                    }else if(answer == 3){
                        System.out.println("Enter new service years:");
                        service = sc.nextInt();
                        police.get(i).setServiceYears(service);
                    }else if(answer == 4){
                        System.out.println("Enter new location:");
                        location = sc.next();
                        police.get(i).setLocation(location);
                    }else if(answer == 5){
                        System.out.println("Enter new arrests number:");
                        arrests = sc.nextInt();
                        police.get(i).setNumberOfArrests(arrests);
                    }
                }
            }
        }
    }

    public static void displayOfficer(ArrayList<PoliceOfficer> police){
        for (int i = 0; i < police.size(); i++) {
            System.out.println("\n" + police.get(i) + "\n");
        }
    }


    public static void retireOfficer(ArrayList<PoliceOfficer> police){
        Scanner sc = new Scanner(System.in);
        String userInput;
        int answer;
        System.out.println("Enter the Police Officer Badge Number:");
        userInput = sc.next();
        boolean repeat = true;
        int retire;
        while(repeat){
            for (int i = 0; i < police.size(); i++){
                if (police.get(i).getBadgeNumber() == Integer.parseInt(userInput)){
                    retire = 25 - police.get(i).getServiceYears();
                    if(retire <= 0){
                        System.out.println("Officer can retire");
                    }else if(retire >= 1){
                        System.out.println("Officer can not retire and has " + retire + " years left");
                    }
                    repeat = false;
                }
            }
        }
    }
}
