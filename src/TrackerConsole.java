/**
 @Author Louis G. Binwag III (200747)
 @Date December 5, 2022

 TrackerConsole.Java handles input of the user. This program features
 a Lifestyle tracker that handles what you eat or do in a day or as long as
 the program is used.

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

import java.util.*;

public class TrackerConsole {
    public static void main(String[] args) {

        String TCName = args[0];

        LifeStyleTracker newConsole = new LifeStyleTracker();
        System.out.printf("Welcome to %s's LifeStyle Tracker!\n",TCName);

        while(true){
            Scanner trackerInputs = new Scanner(System.in);

            String command = trackerInputs.next();

            if(command.equals("Report")){
                System.out.println(newConsole.report());
            }

            /*
            ADD ONS ADD ONS ADD ONS #######################
             */

            else if(command.equals("Edit")){
                System.out.println("Do you want to edit your [Food] or [Action]?");
                String edit = trackerInputs.next();

                if(edit.equals("Food")){

                    newConsole.viewTracker("A");

                    System.out.println("Kindly put what number and new servings.");
                    System.out.println("<IndexNumber> <ChangeServings>");

                    int indexNum = trackerInputs.nextInt();
                    double newServing = trackerInputs.nextDouble();
                    newConsole.editFood(indexNum,newServing);

                    newConsole.viewTracker("A");
                }
                else if(edit.equals("Activity")){

                    newConsole.viewTracker("B");

                    System.out.println("Kindly put what number and new hours.");
                    System.out.println("<IndexNumber> <NewHours>");

                    int IndexNum = trackerInputs.nextInt();
                    double newHours = trackerInputs.nextDouble();
                    newConsole.editActivity(IndexNum,newHours);

                    newConsole.viewTracker("B");
                }
            }

            /*
            ADD ONS ADD ONS ADD ONS #######################
             */

            else if(command.equals("Food")){

                String name = trackerInputs.next();
                double numericValue = trackerInputs.nextDouble();

                if(numericValue <= 0){
                    System.out.println("Number of calories cannot be negative or zero.");
                }
                else {
                    System.out.println(newConsole.addFood(name, numericValue));
                }
            }

            else if(command.equals("Activity")){

                String name = trackerInputs.next();
                double numericValue = trackerInputs.nextDouble();

                if(numericValue <= 0){
                    System.out.println("Number of calories cannot be negative or zero.");
                }
                else {
                    System.out.println(newConsole.addActivity(name, numericValue));
                }
            }

            else if(command.equals("Eat")){

                String name = trackerInputs.next();
                double numericValue = trackerInputs.nextDouble();

                if(numericValue <= 0){
                    System.out.println("Number of servings cannot be negative.");
                }
                else {
                    System.out.println(newConsole.eat(name, numericValue));
                }
            }

            else if(command.equals("Perform")){

                String name = trackerInputs.next();
                double numericValue = trackerInputs.nextDouble();

                if(numericValue <= 0){
                    System.out.println("Number of hours cannot be negative.");
                }
                else {
                    System.out.println(newConsole.perform(name, numericValue));
                }
            }

            else if(command.equals("GUI")){
                TrackerApp appLaunch = new TrackerApp(TCName);

            }

            else{
                System.out.println("");
            }
        }
    }
}