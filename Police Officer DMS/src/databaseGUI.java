import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class databaseGUI extends JFrame{
    private JTabbedPane optionPanel;
    private JPanel titlePanel;
    private JPanel databasePanel;
    private JPanel mainMenuPanel;
    private JPanel menuPanel;
    private JButton addByFileButton;
    private JButton addManuallyButton;
    private JButton updateInfoButton;
    private JButton deleteOfficerButton;
    private JButton displayOfficersButton;
    private JButton canOfficerRetireButton;
    private JButton quitButton;
    private JTabbedPane tabbedPanel;
    private JPanel addFile;
    private JTextField fileEntry;
    private JButton submitbutton1;
    private JPanel manualEntry;
    private JTextField manualRank;
    private JTextField manualLoc;
    private JTextField manuelName;
    private JFormattedTextField manuelBadge;
    private JFormattedTextField manualArrest;
    private JFormattedTextField manualYears;
    private JLabel manualSubmit;
    private JButton submitbutton2;
    private JPanel updatePanel;
    private JPanel showUpdate;
    private JTextField updateName;
    private JTextField updateRank;
    private JFormattedTextField updateYears;
    private JTextField updateLoc;
    private JFormattedTextField updateArrests;
    private JButton submitbutton3;
    private JFormattedTextField updateBadge;
    private JButton Enter1;
    private JPanel deletePanel;
    private JFormattedTextField deleteBadge;
    private JPanel showDelete;
    private JTextArea deleteText;
    private JButton yesButton;
    private JButton noButton;
    private JButton Enter2;
    private JPanel displayPanel;
    private JButton ExitButton;
    private JTextArea displayOfficers;
    private JPanel retirePanel;
    private JFormattedTextField retireBadge;
    private JPanel showRetire;
    private JTextArea retireInfo;
    private JButton exitbutton2;
    private JButton Enter3;
    private JTextField userText;
    private JTextField passText;
    private JTextField dbNameText;
    private JTextField hostNameText;
    private JTextField portText;
    private JButton submitButton4;


    //Variables
    ArrayList<PoliceOfficer> officers = new ArrayList<PoliceOfficer>();

    String name;
    int badge = 0;
    String rank;
    int serveYears = 0;
    String officerLocation;
    int arrests = 0;

    File file = null;
    String fileName = "";
    boolean found = false;
    int answer = 1;
    String textvalue;

    String username;
    String password;
    String hostName;
    String dbName;
    String port;
    String url;

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public databaseGUI() {}

    public void createPanel(){
        setContentPane(titlePanel);
        tabbedPanel.setVisible(false);
        tabbedPanel.setEnabledAt(0, false);
        tabbedPanel.setEnabledAt(1, false);
        tabbedPanel.setEnabledAt(2, false);
        tabbedPanel.setEnabledAt(3, false);
        tabbedPanel.setEnabledAt(4, false);
        tabbedPanel.setEnabledAt(5, false);
        optionPanel.setEnabledAt(1,false);


        this.setSize(600,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Police Officer DMS");
        this.setVisible(true);

        submitButton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                username = userText.getText();
                password = passText.getText();
                hostName = hostNameText.getText();
                dbName = dbNameText.getText();
                port = portText.getText();

                url = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName;

                optionPanel.setEnabledAt(1,true);

                try {
                    connection = DriverManager.getConnection(url,username,password);

                    statement = connection.createStatement();

                    System.out.println("Database Connection Successful!");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addByFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPanel.setVisible(true);
                tabbedPanel.setEnabledAt(0, true);
                tabbedPanel.setSelectedIndex(0);
                submitbutton1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fileName = fileEntry.getText();
                        if(fileName.startsWith("\"") && fileName.endsWith("\"")){
                            fileName = fileName.substring(1, fileName.length()-1);
                        }

                        file = new File(fileName);
                        if (!file.exists()) {
                            JOptionPane.showMessageDialog(null, "File does not exist");
                        }else {
                            operations.addByFile(officers, file);
                            JOptionPane.showMessageDialog(null, "Added by file");
                            tabbedPanel.setVisible(false);
                            tabbedPanel.setEnabledAt(0, false);
                        }
                    }
                });
            }
        });

        addManuallyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPanel.setVisible(true);
                tabbedPanel.setEnabledAt(1, true);
                tabbedPanel.setSelectedIndex(1);

                submitbutton2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        name = manuelName.getText();

                        textvalue = manuelBadge.getText();
                        try {
                            badge = Integer.parseInt(textvalue);
                        }catch (NumberFormatException e1){
                            JOptionPane.showMessageDialog(null, "Badge number must be an integer");
                            return;
                        }


                        rank = manualRank.getText();

                        textvalue = manualYears.getText();

                        try {
                            serveYears = Integer.parseInt(textvalue);
                        }catch (NumberFormatException e2){
                            JOptionPane.showMessageDialog(null, "Years number must be an integer");
                            return;
                        }

                        officerLocation = manualLoc.getText();

                        textvalue = manualArrest.getText();
                        try {
                            arrests = Integer.parseInt(textvalue);
                        }catch (NumberFormatException e3){
                            JOptionPane.showMessageDialog(null, "arrests must be an integer");
                            return;
                        }

                        //operations.addManually(officers, name, badge, rank, serveYears, officerLocation, arrests);
                        try {
                            statement.executeUpdate("INSERT INTO officers (officerName, BadgeNum, officerRank, serviceYears, location, arrestNum) VALUES ('"
                            + name + "', " + badge + ", '" + rank + "', " + serveYears + ", '" + officerLocation + "', " + arrests + ")");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null, "Manually added");
                        tabbedPanel.setVisible(false);
                        tabbedPanel.setEnabledAt(1, false);
                    }
                });

            }
        });
        updateInfoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPanel.setVisible(true);
                tabbedPanel.setEnabledAt(2, true);
                tabbedPanel.setSelectedIndex(2);
                showUpdate.setVisible(false);

                Enter1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textvalue = updateBadge.getText();
                        try {
                            badge = Integer.parseInt(textvalue);
                        }catch(NumberFormatException e1){
                            JOptionPane.showMessageDialog(null, "Badge number must be an integer");
                            return;
                        }

                        /*
                        for(int i = 0; i <= officers.size(); i++){
                            if(officers.get(i).getBadgeNumber() == badge){
                                updateName.setText(officers.get(i).getName());
                                updateArrests.setText(String.valueOf(officers.get(i).getNumberOfArrests()));
                                updateYears.setText(String.valueOf(officers.get(i).getServiceYears()));
                                updateLoc.setText(officers.get(i).getLocation());
                                updateRank.setText(String.valueOf(officers.get(i).getRank()));
                                showUpdate.setVisible(true);
                                found = true;
                            }
                        }

                         */

                        try {
                            resultSet = statement.executeQuery("select * from officers where BadgeNum = " + badge);
                            if(resultSet.next()){
                                updateName.setText(resultSet.getString("officerName"));
                                updateArrests.setText(String.valueOf(resultSet.getInt("arrestNum")));
                                updateYears.setText(String.valueOf(resultSet.getInt("ServiceYears")));
                                updateLoc.setText(resultSet.getString("location"));
                                updateRank.setText(resultSet.getString("officerRank"));
                                showUpdate.setVisible(true);
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                submitbutton3.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        name = updateName.getText();
                        rank = updateRank.getText();

                        textvalue = updateYears.getText();
                        try {
                            serveYears = Integer.parseInt(textvalue);
                        }catch (NumberFormatException e2){
                            JOptionPane.showMessageDialog(null, "Years number must be an integer");
                            return;
                        }

                        officerLocation = updateLoc.getText();

                        textvalue = updateArrests.getText();
                        try {
                            arrests = Integer.parseInt(textvalue);
                        } catch (NumberFormatException e1) {
                            JOptionPane.showMessageDialog(null, "arrests number must be an integer");
                            return;
                        }

                        /*
                        operations.updateRankInfo(officers, badge, rank);
                        operations.updateServiceInfo(officers, badge, serveYears);
                        operations.updateLocationInfo(officers, badge, location);
                        operations.updateNameInfo(officers, badge, name);
                        operations.updateArrestInfo(officers, badge, arrests);

                         */
                        try {
                            statement.executeUpdate("UPDATE officers SET officerName = '" + name
                                    + "', serviceYears = " + serveYears
                                    + ", location = '" + officerLocation
                                    + "', arrestNum = " + arrests
                                    + ", officerRank = '" + rank
                                    + "' WHERE BadgeNum = " + badge);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null, "Updated");
                        System.out.println("Officer Info Updated");
                        tabbedPanel.setVisible(false);
                        tabbedPanel.setEnabledAt(2, false);
                    }
                });

            }
        });
        deleteOfficerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPanel.setVisible(true);
                tabbedPanel.setEnabledAt(3, true);
                tabbedPanel.setSelectedIndex(3);
                showDelete.setVisible(false);

                Enter2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textvalue = deleteBadge.getText();
                        try {
                            badge = Integer.parseInt(textvalue);
                        } catch (NumberFormatException e1){
                            JOptionPane.showMessageDialog(null, "Badge number must be an integer");
                            return;
                        }

                        /*
                        for(int i = 0; i <= officers.size(); i++){
                            if(officers.get(i).getBadgeNumber() == badge){
                                deleteText.setText(officers.get(i).toString());
                                found = true;
                                showDelete.setVisible(true);
                            }
                        }

                         */
                        try {
                            resultSet = statement.executeQuery("select * from officers where BadgeNum = " + badge);
                            if(resultSet.next()){
                                deleteText.setText("Name: " + resultSet.getString(1)
                                        + "\nBadge Number: " + resultSet.getInt(2)
                                        + "\nRank: " + resultSet.getString(3)
                                        + "\nServe Years: " + resultSet.getString(4)
                                        + "\nLocation: " + resultSet.getString(5)
                                        + "\nArrests: " + resultSet.getString(6)
                                );
                                showDelete.setVisible(true);
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                yesButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //operations.deleteOfficer(officers, badge, answer);
                        try {
                            statement.executeUpdate("delete from officers where BadgeNum = " + badge);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null, "Officer Deleted");
                        System.out.println("Officer Deleted");
                        tabbedPanel.setVisible(false);
                        tabbedPanel.setEnabledAt(4, false);
                    }
                });
                noButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Officer Not Deleted");
                        System.out.println("Officer Not Deleted");
                        tabbedPanel.setVisible(false);
                        tabbedPanel.setEnabledAt(4, false);
                    }
                });

            }
        });
        displayOfficersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPanel.setVisible(true);
                tabbedPanel.setEnabledAt(4, true);
                tabbedPanel.setSelectedIndex(4);

                /*
                for (int i = 0; i < officers.size(); i++){
                    displayOfficers.append(officers.get(i).toString() + "\n\n");
                }
                */

                try {
                    resultSet = statement.executeQuery("select * from officers");

                    while(resultSet.next()){
                        displayOfficers.append("Name: " + resultSet.getString(1)
                                + "\nBadge Number: " + resultSet.getInt(2)
                                + "\nRank: " + resultSet.getString(3)
                                + "\nYears of Service: " + resultSet.getInt(4)
                                + "\nLocation: " + resultSet.getString(5)
                                + "\nNumber of Arrests " + resultSet.getInt(6) + "\n\n");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                ExitButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tabbedPanel.setVisible(false);
                        tabbedPanel.setEnabledAt(4, false);
                    }
                });
            }
        });
        canOfficerRetireButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPanel.setVisible(true);
                tabbedPanel.setEnabledAt(5, true);
                tabbedPanel.setSelectedIndex(5);
                showRetire.setVisible(false);

                Enter3.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textvalue = retireBadge.getText();
                        try {
                            badge = Integer.parseInt(textvalue);
                        } catch (NumberFormatException e1){
                            JOptionPane.showMessageDialog(null, "Badge number must be an integer");
                            return;
                        }

                        /*
                        for(int i = 0; i <= officers.size(); i++){
                            if(officers.get(i).getBadgeNumber() == badge){
                                retireInfo.setText(operations.retireOfficer(officers, badge));
                                showRetire.setVisible(true);
                            }
                         */

                        try {
                            resultSet = statement.executeQuery("select serviceYears from officers where BadgeNum = " + badge);
                            if(resultSet.next()){
                                serveYears = resultSet.getInt("serviceYears");
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        if(serveYears >= 25){
                            retireInfo.setText("Officer can Retire");
                        } else if (serveYears < 25) {
                            int yearsLeft = 25 - serveYears;
                            retireInfo.setText("Officer can not Retire and has " + yearsLeft + " years left.");
                        }
                        showRetire.setVisible(true);
                    }
                });

                exitbutton2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tabbedPanel.setVisible(false);
                        tabbedPanel.setEnabledAt(5, false);
                    }
                });
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


}


