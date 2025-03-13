import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class dmsGUI extends JFrame {


    // This is the title panel
    private JPanel titlePanel;
    private JButton submitbutton2;

    //THis is the menu panel for button
    private JPanel menuPanel;
    private JButton addByFileButton;
    private JButton addManuallyButton;
    private JButton updateInfoButton;
    private JButton deleteOfficerButton;
    private JButton displayOfficersButton;
    private JButton canOfficerRetireButton;
    private JButton quitButton;

    //This is the tabbed pane to use when specific buttons are pressed
    private JTabbedPane tabbedPanel;


    // This is the panel to use when the user hits addfile button
    private JPanel addFile;
    private JTextField fileEntry;

    // Manuel Officer Entry panel when user hits addmanual button
    private JPanel manualEntry;
    private JTextField manuelName;
    private JTextField manualRank;
    private JTextField manualLoc;



    // This is the first panel for user to enter the badge number to update info.
    // If badge number is correct the second panel will show
    private JPanel updatePanel;
    private JFormattedTextField updateBadge;
    private JButton Enter1;

    // This is the second panel
    private JPanel showUpdate;
    private JFormattedTextField updateArrests;
    private JFormattedTextField updateYears;
    private JTextField updateLoc;
    private JTextField updateRank;
    private JTextField updateName;

    // This is delete panel 1. When the user enters correct badge number,
    // delete panel 2 will appear
    private JPanel deletePanel;
    private JFormattedTextField deleteBadge;
    private JButton Enter2;

    //This is delete panel 2
    private JPanel showDelete;
    private JTextArea deleteText;
    private JButton yesButton;
    private JButton noButton;

    // THis is the display panel when display button is hit
    private JPanel displayPanel;
    private JTextArea displayOfficers;

    // This is retired panel 1. When user enter correct badge number,
    // retire panel 2 appears
    private JPanel retirePanel;
    private JFormattedTextField retireBadge;

    // This is retired panel 2
    private JPanel showRetire;
    private JTextArea retireInfo;
    private JButton Enter3;
    private JButton ExitButton;
    private JFormattedTextField manuelBadge;
    private JFormattedTextField manualArrest;
    private JFormattedTextField manualYears;
    private JLabel manualSubmit;
    private JButton exitbutton2;
    private JButton submitbutton1;
    private JButton submitbutton3;

    //Variables
    ArrayList<PoliceOfficer> officers = new ArrayList<PoliceOfficer>();

    String name;
    int badge = 0;
    String rank;
    int serviceYears = 0;
    String location;
    int arrests = 0;

    File file = null;
    String fileName = "";
    boolean found = false;
    int answer = 1;
    String textvalue;

    public dmsGUI() {}

    public void enterPanels(){
        setContentPane(titlePanel);
        tabbedPanel.setVisible(false);
        tabbedPanel.setEnabledAt(0, false);
        tabbedPanel.setEnabledAt(1, false);
        tabbedPanel.setEnabledAt(2, false);
        tabbedPanel.setEnabledAt(3, false);
        tabbedPanel.setEnabledAt(4, false);
        tabbedPanel.setEnabledAt(5, false);


        this.setSize(600,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Police Officer DMS");
        this.setVisible(true);

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
                            serviceYears = Integer.parseInt(textvalue);
                        }catch (NumberFormatException e2){
                            JOptionPane.showMessageDialog(null, "Years number must be an integer");
                            return;
                        }

                        location = manualLoc.getText();

                        textvalue = manualArrest.getText();
                        try {
                            arrests = Integer.parseInt(textvalue);
                        }catch (NumberFormatException e3){
                            JOptionPane.showMessageDialog(null, "arrests must be an integer");
                            return;
                        }

                        operations.addManually(officers, name, badge, rank, serviceYears, location, arrests);
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
                    }
                });
                submitbutton3.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        name = updateName.getText();
                        rank = updateRank.getText();

                        textvalue = updateYears.getText();
                        try {
                            serviceYears = Integer.parseInt(textvalue);
                        }catch (NumberFormatException e2){
                            JOptionPane.showMessageDialog(null, "Years number must be an integer");
                            return;
                        }

                        location = updateLoc.getText();

                        textvalue = updateArrests.getText();
                        try {
                            arrests = Integer.parseInt(textvalue);
                        } catch (NumberFormatException e1) {
                            JOptionPane.showMessageDialog(null, "arrests number must be an integer");
                            return;
                        }

                        operations.updateRankInfo(officers, badge, rank);
                        operations.updateServiceInfo(officers, badge, serviceYears);
                        operations.updateLocationInfo(officers, badge, location);
                        operations.updateNameInfo(officers, badge, name);
                        operations.updateArrestInfo(officers, badge, arrests);
                        JOptionPane.showMessageDialog(null, "Updated");
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

                        for(int i = 0; i <= officers.size(); i++){
                            if(officers.get(i).getBadgeNumber() == badge){
                                deleteText.setText(officers.get(i).toString());
                                found = true;
                                showDelete.setVisible(true);
                            }
                        }
                    }
                });

                yesButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        operations.deleteOfficer(officers, badge, answer);
                        JOptionPane.showMessageDialog(null, "Officer Deleted");
                        tabbedPanel.setVisible(false);
                        tabbedPanel.setEnabledAt(4, false);
                    }
                });
                noButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Officer Not Deleted");
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

                for (int i = 0; i < officers.size(); i++){
                    displayOfficers.append(officers.get(i).toString() + "\n\n");
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


                        for(int i = 0; i <= officers.size(); i++){
                            if(officers.get(i).getBadgeNumber() == badge){
                                retireInfo.setText(operations.retireOfficer(officers, badge));
                                showRetire.setVisible(true);
                            }
                        }
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
