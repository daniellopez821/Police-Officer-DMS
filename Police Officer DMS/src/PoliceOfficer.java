import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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


    public String toString(){
        String results;
        results = "Name: " + name + "\nBadge #: " + badgeNumber + "\nRank: " + rank + "\nYears of Service: " + serviceYears + "\nLocation: " + location + "\nNumber of Arrests: " + numberOfArrests;
        return results;
    }

}


