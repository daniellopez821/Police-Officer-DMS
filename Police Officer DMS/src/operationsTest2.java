import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class operationsTest2 {

    ArrayList<PoliceOfficer> police = new ArrayList<>();


    @BeforeEach
    void setUp() {
        //Object to be tested
        String name = "Daniel";
        int badge = 4467;
        String rank = "Captain";
        int service = 27;
        String location = "Orlando";
        int arrests = 60;

        police.add(new PoliceOfficer(name, badge, rank, service, location, arrests));
    }

    @Test
    void addByFile() {
        String fileName = "C:\\Users\\dl032\\OneDrive\\Desktop\\School\\Software Development 1\\policeofficertext.txt";
        File file = new File(fileName);
        operations.addByFile(police,file);
        assertTrue(police.size() > 1);
    }

    @Test
    void addManually() {
        String uName = "nyssa";
        int badgeNum = 34567;
        String rank = "Captain";
        int service = 24;
        String location = "Orlando";
        int arrests = 50;
        operations.addManually(police,uName,badgeNum,rank,service,location,arrests);
        assertTrue(police.size() > 1);
    }

    @Test
    void deleteOfficer() {
        System.out.println("Delete badge number 4467");
        int badgeNum = 4467;
        int answer = 1;
        operations.deleteOfficer(police,badgeNum,answer);
        assertTrue(police.isEmpty());

    }

    @Test
    void updateNameInfo() {
        System.out.println("Badge Number is 4467. Change the name to Pablo");
        String name = "Pablo";
        int badgeNum = 4467;
        operations.updateNameInfo(police,badgeNum,name);
        assertEquals("Pablo", police.getFirst().getName());
    }

    @Test
    void updateRankInfo() {
        System.out.println("Badge Number is 4467. Change the rank to Seargant");
        String rank = "Seargant";
        int badgeNum = 4467;
        operations.updateRankInfo(police,badgeNum,rank);
        assertEquals("Seargant", police.getFirst().getRank());
    }

    @Test
    void updateServiceInfo() {
        System.out.println("Badge Number is 4467. Change the service years to 10");
        int badgeNum = 4467;
        int service = 10;
        operations.updateServiceInfo(police,badgeNum,service);
        assertEquals(10, police.getFirst().getServiceYears());
    }

    @Test
    void updateLocationInfo() {
        System.out.println("Badge Number is 4467. Change the location to Las vegas");
        String location = "Las vegas";
        int badgeNum = 4467;
        operations.updateLocationInfo(police,badgeNum,location);
        assertEquals("Las vegas", police.getFirst().getLocation());
    }

    @Test
    void updateArrestsInfo() {
        System.out.println("Badge Number is 4467. Change the number of arrests to 50");
        int badgeNum = 4467;
        int arrests = 50;
        operations.updateArrestInfo(police,badgeNum,arrests);
        assertEquals(50, police.getFirst().getNumberOfArrests());
    }

    @Test
    void retireOfficer() {
        System.out.println("See if Badge number 4467 can retire");
        int badge = 4467;
        operations.retireOfficer(police, badge);
        assertEquals("Officer can retire", operations.retireOfficer(police, badge) );
    }
}