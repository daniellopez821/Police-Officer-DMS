import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
Daniel Lopez-Morales. Software Development 1. 2/22/2025
This is the police officer class. The purpose of this class is to create all the information on police
officers. Each element of a police officer is listed along with constructors and setters and getters to
call them as needed.
 */
public class PoliceOfficer {

    private String name;
    private int badgeNumber;
    private String rank;
    private int serviceYears;
    private String location;
    private int numberOfArrests;

    public PoliceOfficer(){};

    public PoliceOfficer(String name, int badgeNumber, String rank, int serviceYears, String location, int numberOfArrests) {
        this.name = name;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.serviceYears = serviceYears;
        this.location = location;
        this.numberOfArrests = numberOfArrests;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }
    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getServiceYears() {
        return serviceYears;
    }
    public void setServiceYears(int serviceYears) {
        this.serviceYears = serviceYears;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfArrests() {
        return numberOfArrests;
    }
    public void setNumberOfArrests(int numberOfArrests) {
        this.numberOfArrests = numberOfArrests;
    }


    //To String method. This is the method used when I need to print the layout for a police officers information. This takes no arguements
    // and returns type string.
    public String toString(){
        String results;
        results = "Name: " + name + "\nBadge #: " + badgeNumber + "\nRank: " + rank + "\nYears of Service: " + serviceYears + "\nLocation: " + location + "\nNumber of Arrests: " + numberOfArrests;
        return results;
    }

}


