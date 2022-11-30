/**
 @Author Louis G. Binwag III (200747)
 @Date December 5, 2022

 TrackerGUI.java creates a new instance in LifeStyleTracker.java
 It handles the GUI-add on for TrackerConsole.Java, it does not need
 to utilize the Terminal unlike the TrackerConsole.java
 Instead, it uses JavaSwing to access the methods in LifeStyleTracker.java.
 **/
/*
I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.
I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.
*/

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class TrackerGUI {
    public TrackerGUI(String a) {

        /**
         * when GUI is entered in the console, it creates a new instance
         * but utilizes JavaSwing 'GUI'.

         * A new instance 'trackerConsole' is created in LifeStyleTracker
         * which also has the 'isGui' condition set as true because some
         * methods cannot be called on the GUI such as Scanner.

         * Many of the methods in LifeStyleTracker has 'if(gui)' statements
         * checks if the instance utilizes 'GUI', if not, it can use the initial
         * code that simply requires the Terminal.
         */

        LifeStyleTracker trackerConsole = new LifeStyleTracker();
        trackerConsole.isGui(true);

        String[] cmdActions = {"Add Food", "Add Activity", "Eat Food", "Perform Activity"};

        /** Creates the GUI, and its formats */

        JFrame ui = new JFrame();
        JButton cmd = new JButton();
        JButton report = new JButton();
        JButton edit = new JButton();
        JTextField name = new JTextField();
        JTextField calories = new JTextField();
        JLabel ann = new JLabel("---", SwingConstants.CENTER);
        JLabel text1 = new JLabel("", SwingConstants.CENTER);
        JLabel text2 = new JLabel("", SwingConstants.CENTER);
        JComboBox cmdList = new JComboBox(cmdActions);
        JPanel mainLayout = new JPanel();
        JPanel buttonLayout = new JPanel();
        Border margins = BorderFactory.createEmptyBorder(10, 5, 10, 5);

        ui.setSize(600, 200);
        ui.setTitle("Welcome to " + a + "'s LifeStyle Tracker");
        ui.setLayout(new FlowLayout());
        ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cmd.setText("Record");
        cmd.setSize(100, 100);

        report.setText("Report");

        edit.setText("Edit Contents");

        name.setText("Name");
        calories.setText("000");
        text1.setText("Add Food");
        text2.setText("Calories Involved");

        mainLayout.setLayout(new GridLayout(5, 2));

        mainLayout.add(text1);
        mainLayout.add(name);
        mainLayout.add(text2);
        mainLayout.add(calories);
        mainLayout.add(ann);

        buttonLayout.setLayout(new GridLayout(1, 1));
        buttonLayout.add(cmd);
        buttonLayout.add(cmdList);

        mainLayout.add(report);
        mainLayout.add(buttonLayout);
        mainLayout.add(edit);

        mainLayout.setBorder(margins);

        ui.add(mainLayout);
        ui.setVisible(true);
        ui.setLocationRelativeTo(null);

        /**
         * This 'cmd' Action Listener is the main button that
         * executes most of the program. It enables the user to execute
         * what the user chooses from the 'cmdList' combo box.

         * The 'cmdList' combo box is a drop-box-list of 4 choices
         * (listed in String[] cmdAction : Line 26) from which
         * the user chooses what the program would do.
         */

        //RECORD BUTTON
        cmd.addActionListener(new ActionListener() { //RECORD BUTTON
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean neg = false;

                /**
                 * A do-while loop is created so that the program
                 * would check if the user inputs a number less than 0
                 * because calorie count/hours/servings cannot be equal
                 * or less than 0.

                 * Once the condition is satisfied, that the user
                 * inputs an appropriate number, it proceeds to check
                 * what the user chose to do for the program;
                 * 'Add Food' | 'Add Activity ' | 'Eat Food' | or 'Perform Activity'

                 * Then it calls for its respective methods in the LifeStyleTracker.Java
                 */

                do {
                    double x = Double.parseDouble(calories.getText());
                    if (x <= 0) {
                        JOptionPane.showMessageDialog(null, "Number cannot be less than or equal to 0.");
                    } else {
                        neg = true;
                        if (cmdList.getSelectedItem().equals(cmdActions[0])) { //COMBO BOX, Add Food Selected

                            String foodName = name.getText();
                            double foodCals = Double.parseDouble(calories.getText());

                            if (foodCals <= 0) {
                                JOptionPane.showMessageDialog(null, "Number cannot be less than or equal to 0.");
                            } else {
                                ann.setText(trackerConsole.addFood(foodName, foodCals));
                            }

                        } else if (cmdList.getSelectedItem().equals(cmdActions[1])) { //COMBO BOX, Add Activity Selected

                            String actName = name.getText();
                            double actCals = Double.parseDouble(calories.getText());

                            if (actCals <= 0) {
                                JOptionPane.showMessageDialog(null, "Number cannot be less than or equal to 0.");
                            } else {
                                ann.setText(trackerConsole.addActivity(actName, actCals));
                            }
                        }

                        //Eat[2] AND Perform[3]

                        else if (cmdList.getSelectedItem().equals(cmdActions[2])) { //COMBO BOX, Eat Food Selected

                            String foodName = name.getText();
                            double servings = Double.parseDouble(calories.getText());

                            if (!trackerConsole.eat(foodName, servings).equals("The specified food does not exist.")) {
                                ann.setText("Ate " + servings + " serving(s) of " + foodName);
                            } else {
                                int userChoice = JOptionPane.showConfirmDialog(null, "Do you want to add the specified food?", "Food Not Found", JOptionPane.YES_NO_OPTION);

                                if (userChoice == JOptionPane.YES_OPTION) {
                                    String userInput = JOptionPane.showInputDialog(null, "Enter Calories of: " + foodName, JOptionPane.YES_OPTION);
                                    double newFoodCals = Double.parseDouble(userInput);

                                    if (newFoodCals <= 0) {
                                        JOptionPane.showMessageDialog(null, "Number cannot be less than or equal to 0.");
                                    } else {
                                        ann.setText(trackerConsole.addFood(foodName, newFoodCals));
                                    }
                                }
                            }
                        } else if (cmdList.getSelectedItem().equals(cmdActions[3])) { //COMBO BOX, Perform Activity Selected

                            String actName = name.getText();
                            double hours = Double.parseDouble(calories.getText());

                            if (!trackerConsole.perform(actName, hours).equals("The specified activity does not exist.")) {
                                ann.setText("Performed " + hours + " hours(s) of " + actName);
                            } else {
                                int userChoice = JOptionPane.showConfirmDialog(null, "Do you want to add the specified Activity?", "Activity Not Found", JOptionPane.YES_NO_OPTION);

                                if (userChoice == JOptionPane.YES_OPTION) {
                                    String userInput = JOptionPane.showInputDialog(null, "Enter Calories of: " + actName, JOptionPane.YES_OPTION);
                                    double newFoodCals = Double.parseDouble(userInput);

                                    if (newFoodCals <= 0) {
                                        JOptionPane.showMessageDialog(null, "Number cannot be less than or equal to 0.");
                                    } else {
                                        ann.setText(trackerConsole.addActivity(actName, newFoodCals));
                                    }
                                }
                            }
                        }
                    }
                } while (neg = false);
            }
        });

        /**
         * This 'edit' Action Listener calls for a new 'edit' JFrame
         * that has new user functions such as two text-fields to take in
         * user inputs, which is passed to its respective called methods.

         * It has two conditions, "Edit Food Eaten" and "Edit Activity Performed"
         * Once either button is clicked, it shows the respective current track
         * record of the user. If "Edit Food Eaten" is clicked, it shows the
         * food eaten record of the user, and gives them the ability to
         * edit the food intake (servings)
         */

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] options = {"Edit Food Eaten", "Edit Activity Performed"};

                if(JOptionPane.showOptionDialog( null, "What do you want to edit?", "Confirmation",
                        JOptionPane.WARNING_MESSAGE, 0,null, options, options[1]) == 0){

                    /** creates the new 'edit' JFrame */

                    JFrame edit = new JFrame();
                    JLabel tracker = new JLabel();
                    JTextField editComp = new JTextField(10);
                    JTextField newValue = new JTextField(10);
                    JButton confirm = new JButton("Confirm");

                    JPanel label = new JPanel(new GridLayout(1,1));
                    JPanel input = new JPanel(new GridLayout(3,1));

                    edit.setTitle("Edit Content");
                    editComp.setText("Index Number");
                    newValue.setText("Change Servings");

                    editComp.setSize(150,20);
                    newValue.setSize(150,20);

                    label.add(tracker);
                    input.add(editComp);
                    input.add(newValue);
                    input.add(confirm);

                    input.setBorder(margins);

                    tracker.setFont(new Font("Arial", Font.PLAIN,14 ));
                    tracker.setText(trackerConsole.viewTracker("a"));

                    edit.setSize(550,600);
                    edit.setLayout(new FlowLayout());
                    edit.setLocationRelativeTo(null);

                    edit.add(label);
                    edit.add(input);

                    edit.setVisible(true);

                    /**
                     * This 'confirm' Action Listener is the button that activates
                     * the edit feature of TrackerGUI.java

                     * It makes sure the user does not input apart from the conditions.
                     * (index cannot be less than 0, and new servings cannot be less than or equal to 0.)
                     */

                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            boolean neg = false;

                            do{
                               int x = Integer.parseInt(editComp.getText());
                               double y = Double.parseDouble(newValue.getText());

                               if(x < 0){
                                   JOptionPane.showMessageDialog(null, "Index cannot be less than 0.");
                               }
                               else if(y <= 0){
                                   JOptionPane.showMessageDialog(null, "Servings cannot be less than 0.");
                               }
                               else{
                                   neg = true;
                                   edit.dispose();

                                   int index = Integer.parseInt(editComp.getText());
                                   double change = Double.parseDouble(newValue.getText());
                                   ann.setText(trackerConsole.editFood(index,change));

                                   ui.setVisible(true);
                               }
                            }
                            while(neg = false);
                        }
                    });

                    edit.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowOpened(WindowEvent e) {
                            ui.setVisible(false);
                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                            ui.setVisible(true);
                            JOptionPane.showMessageDialog(null,"Transaction Cancelled");
                        }
                    });
                }
                else{
                    JFrame edit = new JFrame();
                    JLabel tracker = new JLabel();
                    JTextField editComp = new JTextField(10);
                    JTextField newValue = new JTextField(10);

                    JPanel label = new JPanel();
                    JPanel input = new JPanel(new GridLayout(3,1));
                    JButton confirm = new JButton("Confirm");

                    edit.setTitle("Edit Content");
                    editComp.setText("Index Number");
                    newValue.setText("Change Hours");

                    editComp.setSize(150,20);
                    newValue.setSize(150,20);

                    /**
                     * TODO:
                     * CENTER THIS LABEL
                     */


                    label.add(tracker);
                    input.add(editComp);
                    input.add(newValue);
                    input.add(confirm);

                    input.setBorder(margins);

                    tracker.setFont(new Font("Arial", Font.PLAIN,14 ));
                    tracker.setText(trackerConsole.viewTracker("b"));

                    edit.setSize(550,500);
                    edit.setLocationRelativeTo(null);
                    edit.setLayout(new FlowLayout(5));

                    edit.add(label);
                    edit.add(input);

                    edit.setVisible(true);

                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            boolean neg = false;

                            do{
                                int x = Integer.parseInt(editComp.getText());
                                double y = Double.parseDouble(newValue.getText());

                                if(x < 0){
                                    JOptionPane.showMessageDialog(null, "Index cannot be less than 0.");
                                }
                                else if(y <= 0){
                                    JOptionPane.showMessageDialog(null, "Hours cannot be less than 0.");
                                }
                                else {
                                    neg = true;

                                    edit.dispose();

                                    int index = Integer.parseInt(editComp.getText());
                                    double change = Double.parseDouble(newValue.getText());
                                    ann.setText(trackerConsole.editActivity(index, change));

                                    ui.setVisible(true);
                                }
                            }
                            while(neg = false);
                        }
                    });

                    edit.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowOpened(WindowEvent e) {
                            ui.setVisible(false);
                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                            JOptionPane.showMessageDialog(null,"Transaction Cancelled");
                            ui.setVisible(true);
                        }
                    });
                }
            }
        });

        /**
         * This 'report' Action Listener
         * Is activated when 'report' button is clicked.
         * It calls for a new 'report' JFrame that shows
         * the user the current intake of food or
         * activities in that instance.
         */

        report.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame report = new JFrame();
                JLabel rep = new JLabel();

                report.setSize(500, 800);
                report.setTitle("LifeStyle Report");
                report.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                report.setLocationRelativeTo(null);

                rep.setFont(new Font("Arial", Font.PLAIN, 14));

                report.add(rep);

                rep.setText(trackerConsole.report());

                report.setVisible(true);

                report.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowOpened(WindowEvent e) {
                        ui.setVisible(false);
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                        ui.setVisible(true);

                    }
                });
            }
        });

        /**
         * This 'cmdList' Action Listener
         * checks what the user is utilizing
         * and appropriately changes the JLabel 'ann'
         * for the user to see what they should input
         * inside the text-fields.
         */

        cmdList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cmdList.getSelectedItem().equals(cmdActions[0])) {
                    text1.setText("Add Food");
                    text2.setText("Calories Involved");
                    name.setText("");
                    calories.setText("");
                } else if (cmdList.getSelectedItem().equals(cmdActions[1])) {
                    text1.setText("Add Activity");
                    text2.setText("Calories Involved");
                    name.setText("");
                    calories.setText("");
                } else if (cmdList.getSelectedItem().equals(cmdActions[2])) {
                    text1.setText("Food Name");
                    text2.setText("Servings");
                    name.setText("");
                    calories.setText("");
                } else if (cmdList.getSelectedItem().equals(cmdActions[3])) {
                    text1.setText("Activity Name");
                    text2.setText("Hours");
                    name.setText("");
                    calories.setText("");
                }
            }
        });

        /**
         * The following mouse listeners clears the text-field of the JFrame 'ui'
         * It removes the static text for the user to type-in the prompted
         * values for the program to utilize.
         */

        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                name.setText("");

            }
        });
        calories.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                calories.setText("");
            }
        });
    }
}

    /*
        Curricular Linux Environment at Rice (n.d) Setting the Behavior When Closing a JFrame.
        Retrieved From https://www.clear.rice.edu/comp310/JavaResources/frame_close.html

        Pankaj (August 4,2022) Java String to Double.
        Retrieved From https://www.digitalocean.com/community/tutorials/java-convert-string-to-double. Line 240-271

        Pankaj (August 4,2022) Java Double to String.
        Retrieved From https://www.digitalocean.com/community/tutorials/java-convert-double-to-string.Line 87-308

        Javadoc (n.d) Using Text Components.
        Retrieved From, https://docs.oracle.com/javase/tutorial/uiswing/components/text.html

        Java Code Junkie (May 11, 2021) Action Listener | JavaSwing Tutorial for Beginners.
        Retrieved From, https://www.youtube.com/watch?v=ObVnyA8ar6Q

        Javadoc (n.d) Action Listener.
        Retrieved From https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html

        Javadoc (n.d) Grid Layouts.
        Retrieved From https://docs.oracle.com/javase/8/docs/api/java/awt/GridLayout.html

        Javadoc (n.d) Java Swing Layouts.
        Retrieved From https://docs.oracle.com/javase/7/docs/api/javax/swing/JLabel.html

        Javadoc (n.d) Layout Managers.
        Retrieved From, https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html

        Javadoc (n.d) How to Use HTML in Swing Components.
        Retrieved From https://docs.oracle.com/javase/tutorial/uiswing/components/html.html

        Javadoc (n.d) JOptionPanels.
        Retrieved From https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html

        TutorialsPoint (n.d) AWT MouseAdapter Class.
        Retrieved From https://www.tutorialspoint.com/awt/awt_mouseadapter.htm

        TutorialsPoint (n.d) How to change JLabel font in Java.
        Retrieved From https://www.tutorialspoint.com/how-to-change-jlabel-font-in-java
     */