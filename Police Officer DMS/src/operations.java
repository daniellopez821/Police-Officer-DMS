import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class operations {

    // Created the operations class to hold all the methods used for my DMS.

    //AddByFile method. This is the method used to add officers by a text file. This method takes the police officer arraylist as the argument. Return type of arraylist
    public static ArrayList<PoliceOfficer> addByFile(ArrayList<PoliceOfficer> police, File fileName){
        boolean repeat = true;

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] words = line.split("-");
                    police.add(new PoliceOfficer(words[0], Integer.parseInt(words[1]), words[2], Integer.parseInt(words[3]), words[4], Integer.parseInt(words[5])));
                }
                repeat = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        repeat = true;
        return police;
    }

    //addManually method. This is the method used to add an officer manually. The argument for this method is the police officer arraylist. return value of arraylist
    public static ArrayList<PoliceOfficer> addManually(ArrayList<PoliceOfficer> police, String uName, int badgeNum, String myRank, int serviceYears, String myLocation, int numOfArrests){
        police.add(new PoliceOfficer(uName, badgeNum, myRank, serviceYears, myLocation, numOfArrests));
        return police;
    }

    //The deleteOfficer Method. This method will delete the officer for the user based on their badge number. The argument is the officer arraylist. return type arraylist

    public static ArrayList<PoliceOfficer> deleteOfficer(ArrayList<PoliceOfficer> police,int userInput, int answer){
        if(answer == 1){
            for(int i = 0; i < police.size(); i++){
                if(police.get(i).getBadgeNumber() == userInput){
                    police.remove(i);
                }
            }
        }
        return police;
    }

    // The updateInfo method is seperated for each element that could be updated to assist in unit testing.
    // This method ask the user to update info by searching the police officer by badge number. return value arraylist
    public static ArrayList<PoliceOfficer> updateNameInfo(ArrayList<PoliceOfficer> police, int input,String uName){
            for (int i = 0; i < police.size(); i++){
                if(police.get(i).getBadgeNumber() == input){
                    police.get(i).setName(uName);
                }
            }
        return police;
    }

    public static ArrayList<PoliceOfficer> updateRankInfo(ArrayList<PoliceOfficer> police, int input,String myRank){
        for (int i = 0; i < police.size(); i++){
            if(police.get(i).getBadgeNumber() == input){
                police.get(i).setRank(myRank);
            }
        }
        return police;
    }

    public static ArrayList<PoliceOfficer> updateServiceInfo(ArrayList<PoliceOfficer> police, int input,int serviceYears){
        for (int i = 0; i < police.size(); i++){
            if(police.get(i).getBadgeNumber() == input){
                police.get(i).setServiceYears(serviceYears);
            }
        }
        return police;
    }

    public static ArrayList<PoliceOfficer> updateLocationInfo(ArrayList<PoliceOfficer> police, int input,String myLocation){
        for (int i = 0; i < police.size(); i++){
            if(police.get(i).getBadgeNumber() == input){
                police.get(i).setLocation(myLocation);
            }
        }
        return police;
    }

    public static ArrayList<PoliceOfficer> updateArrestInfo(ArrayList<PoliceOfficer> police, int input,int numOfArrests){
        for (int i = 0; i < police.size(); i++){
            if(police.get(i).getBadgeNumber() == input){
                police.get(i).setNumberOfArrests(numOfArrests);
            }
        }
        return police;
    }


    //The Display Officers method. This method displays all officers in the arraylist. This takes officer arraylist as argument. No return type.
    public static void displayOfficer(ArrayList<PoliceOfficer> police){
        for (int i = 0; i < police.size(); i++) {
            System.out.println("\n" + police.get(i) + "\n");
        }
    }

    //This is the custom action Retire Officer Method. This method determines if an officer can retire or not. Takes officer arraylist as argument. Has a string return type
    public static String retireOfficer(ArrayList<PoliceOfficer> police, int input){
        boolean repeat = true;
        int retire;
        String statement = "";
        while(repeat){
            for (int i = 0; i < police.size(); i++){
                if (police.get(i).getBadgeNumber() == input){
                    retire = 25 - police.get(i).getServiceYears();
                    if(retire <= 0){
                        statement = "Officer can retire";
                    }else if(retire >= 1){
                        statement = "Officer can not retire and has " + retire + " years left";
                    }
                    repeat = false;
                }
            }
        }
        return statement;
    }

}
