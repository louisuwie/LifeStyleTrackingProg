/**
 @Author Louis G. Binwag III (200747)
 @Date December 5, 2022

 LifeStyleTracker.java is the main handler for the processes behind the TrackerConsole.java
 It handles what command the user inputs, should it be tracking what the user ate, performed,
 or if the user wishes to add a type of food they could eat or an activity they would perform.

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

public class LifeStyleTracker {
    private ArrayList<Food> foods;
    private ArrayList<Activity> activities;

    private ArrayList<String[]> trackerFood;
    private ArrayList<String[]> trackerActivities;
    private double totalCalConsumed;
    private double totalBurned;
    private boolean gui;

    /**
     * Constructor of the LifeStyleTracker.Java,
     * Initializes the required values / variables
     * to be used throughout the program.
     */

    public LifeStyleTracker(){
        foods = new ArrayList<Food>();
        activities = new ArrayList<Activity>();

        trackerFood = new ArrayList<String[]>();
        trackerActivities = new ArrayList<String[]>();
        totalCalConsumed = 0;
        gui = false;
    }

    /**
     * addFood method is used to add food from the user
     * inputs and is added to the array of food that can
     * be eaten, this takes into account the name and
     * calories of the specified food.
     * @param n a String that contains the name of the food passed into the method
     * @param c a double that contains the caloric intake of that specific food
     * @return a message that informs the user what has been added and its required details.
     */
    public String addFood(String n, double c){

        String message = "";

        boolean foundFood = false;

        for(int i = 0; i < foods.size(); i++){
            if(foods.get(i).getFoodName().equals(n)){

                double prev = foods.get(i).getFoodCalories();//REMOVES PREVIOUSLY CONSUMED CALORIES

                foods.get(i).updateCalories(c);
                message = "Updated Food " + foods.get(i).getFoodName() + " with " + foods.get(i).getFoodCalories() + " Kcal.";
                foundFood = true;

                    /*
                        0 = NAME
                        1 = SERVINGS
                        2 = CALORIES OF THE FOOD
                        3 = TOTAL (SERVINGS * CALORIES OF FOOD)
                    */

                for(int j = 0; j < trackerFood.size(); j++){
                    if(trackerFood.get(j)[0].equals(n)){

                        double x = Double.parseDouble(trackerFood.get(j)[1]);

                        trackerFood.get(j)[2] = Double.toString(c);
                        trackerFood.get(j)[3] = Double.toString(x*c);

                        totalCalConsumed -= prev;
                        totalCalConsumed += Double.parseDouble(trackerFood.get(j)[3]);
                    }
                }
            }
        }

        if(!foundFood){
            foods.add(new Food(n,c));
            message = "Added Food " + n + " with " + c + " Kcal.";
        }
        return message;
    }


    /**
     * addActivity method works similarly with addFood, but
     * it focuses on handling activity-related inputs.
     * @param n a String that contains the name of the activity passed into the method
     * @param c a double that contains the calories burned by that specific activity
     * @return a message that informs the user what has been added and its required details.
     */
    public String addActivity(String n, double c){

        String message = "";

        boolean foundActivity = false;

        for(int i = 0; i < activities.size();i++){
            if(activities.get(i).getActivityName().equals(n)){

                double prev = activities.get(i).getCalorieCount(); //REMOVES PREVIOUSLY CONSUMED CALORIES

                //UPDATES ACTIVITY CALORIE TO NEW VALUE.
                activities.get(i).updateCalories(c);
                message = "Updated Activity " + activities.get(i).getActivityName() + " with " + activities.get(i).getCalorieCount() + " Kcal.";
                foundActivity = true;

                    /*
                        0 = NAME
                        1 = SERVINGS
                        2 = CALORIES OF THE FOOD
                        3 = TOTAL (SERVINGS * CALORIES OF FOOD)
                    */

                //UPDATES PREVIOUSLY EATEN FOOD'S CALORIC VALUE.
                for(int j = 0; j < trackerActivities.size(); j++){
                    if(trackerActivities.get(j)[0].equals(n)){

                        double x = Double.parseDouble(trackerActivities.get(j)[1]);

                        trackerActivities.get(j)[2] = Double.toString(c);
                        trackerActivities.get(j)[3] = Double.toString(x*c);

                        totalBurned -= prev;
                        totalBurned += Double.parseDouble(trackerActivities.get(j)[3]);
                    }
                }
            }
        }

        if(!foundActivity){
            activities.add(new Activity(n,c));
            message = "Added Activity " + n + " with " + c + " Kcal.";
        }
        return message;
    }

    /**
     * eat method is used when the user utilizes the
     * 'Food' input or if(gui), the Eat Food from the drop-down list.
     *
     * It automatically checks if the food is not yet in the food list,
     * and provides the user the ability to add the food before recording.
     *
     * @param foodName a String that used to check if the food is available,
     * @param servings a double that is used to calculate the caloric intake. servings * initial caloric value.
     * @return a message that tells the user what has been eaten, its initial caloric value, and its total calorie taken.
     */
    public String eat(String foodName, double servings) {

        String message = "";

        boolean foundFood = false;

        if(gui){
            for (int i = 0; i < foods.size(); i++) {
                if (foods.get(i).getFoodName().equals(foodName)) {

                    String foodAmt = Double.toString(servings); //TOTAL AMOUNT OF SERVINGS
                    String foodCals = Double.toString(foods.get(i).getFoodCalories());//FOOD CALORIES TO BE MULTIPLIED BY SERVINGS
                    String totalConsumed = Double.toString(foods.get(i).getFoodCalories() * servings);//CALCULATES (X) CALORIES * SERVINGS
                    String[] eaten = {foodName, foodAmt, foodCals, totalConsumed}; //MAKES A 2D ARRAY

                    message = "Ate " + foodAmt + " serving(s) of " + foodName + ", " + totalConsumed + "Kcal";
                    trackerFood.add(eaten); // ADDS ARRAY TO THE ARRAYLIST
                    totalCalConsumed += foods.get(i).getFoodCalories() * servings;

                    foundFood = true;
                }
            }
            if (!foundFood) {
                message = "The specified food does not exist.";
            }
        }
        else{ //IF NOT USING GUI
            for (int i = 0; i < foods.size(); i++) {
                if (foods.get(i).getFoodName().equals(foodName)) {

                    String foodAmt = Double.toString(servings); //TOTAL AMOUNT OF SERVINGS
                    String foodCals = Double.toString(foods.get(i).getFoodCalories());//FOOD CALORIES TO BE MULTIPLIED BY SERVINGS
                    String totalConsumed = Double.toString(foods.get(i).getFoodCalories() * servings);//CALCULATES (X) CALORIES * SERVINGS
                    String[] eaten = {foodName, foodAmt, foodCals, totalConsumed}; //MAKES A 2D ARRAY

                    message = "Ate " + foodAmt + " serving(s) of " + foodName + ", " + totalConsumed + "Kcal.";
                    trackerFood.add(eaten); // ADDS ARRAY TO THE ARRAYLIST
                    totalCalConsumed += foods.get(i).getFoodCalories() * servings;

                    foundFood = true;
                }
            }

            //ADD-ON 3, ADDS FOOD IF IT IS NOT SPECIFIED.
            if (!foundFood) {

                System.out.println("The specified food does not exist.");
                System.out.printf("Would you like to add this food? <Yes/No>");

                Scanner addFood = new Scanner(System.in);
                String ans = addFood.next();

                if (ans.equals("Yes")) {

                    System.out.printf("How many calories do %s provide per serving?", foodName);
                    double cals = addFood.nextDouble();

                    if (cals <= 0) {
                        System.out.println("Number of calories cannot be negative or zero.");
                    }
                    foods.add(new Food(foodName, cals));
                    message = "Added Food " + foodName + " with " + cals + " Kcal.";
                } else {
                    System.out.println("--Proceed--");
                }
            }
        }
        return message;
    }

    /**
     * perform method works similarly with the eat method.
     *
     * It checks if the activity the user wants to perform is already added
     * to the list, if not, it allows the user to add it- Then they are
     * able to record it the next time around.
     *
     * If the activity is already listen in the array, it calculates the hours to the
     * initial caloric burn of the specified activity. hours * calorie burn per hour.
     * @param actName
     * @param hours
     * @return
     */
    public String perform (String actName, double hours){

        String message = "";
        boolean foundActivity = false;

        if(gui){ //IF USING GUI
                for(int i = 0; i < activities.size();i++){
                    if(activities.get(i).getActivityName().equals(actName)) {
                        String actHours = Double.toString(hours);
                        String actCals = Double.toString(activities.get(i).getCalorieCount());
                        String totalCals = Double.toString(activities.get(i).getCalorieCount() * hours);
                        String[] acted = {actName, actHours, actCals, totalCals};

                        message = "Performed " + actHours + " hour(s) of " + actName + ", " + totalCals + "Kcal.";
                        trackerActivities.add(acted);
                        totalBurned += activities.get(i).getCalorieCount()*hours;
                        foundActivity = true;

                        System.out.println("PrintPrint");

                    }
                }
                if(!foundActivity){
                    message = "The specified activity does not exist.";
                }
        }
        else { //IF NOT USING GUI
                for (int i = 0; i < activities.size(); i++) {
                    if (activities.get(i).getActivityName().equals(actName)) {
                        String actHours = Double.toString(hours);
                        String actCals = Double.toString(activities.get(i).getCalorieCount());
                        String totalCals = Double.toString(activities.get(i).getCalorieCount() * hours);
                        String[] acted = {actName, actHours, actCals, totalCals};

                        message = "Performed " + actHours + " hour(s) of " + actName + ", " + totalCals + "Kcal.";
                        trackerActivities.add(acted);
                        totalBurned += activities.get(i).getCalorieCount() * hours;
                        foundActivity = true;
                    }
                }

            //ADD-ON 3, ADDS ACTIVITY IF IT IS NOT SPECIFIED.
                if (!foundActivity) {
                    System.out.println("The specified activity does not exist");
                    System.out.println("Would you like to add this activity? <Yes/No>");

                    Scanner addAct = new Scanner(System.in);
                    String ans = addAct.next();

                    if (ans.equals("Yes")) {
                        System.out.printf("How many calories do %s consume per hour?\n", actName);
                        double cals = addAct.nextDouble();

                        if (cals <= 0) {
                            System.out.println("Number of calories cannot be negative or zero.");
                        }

                        activities.add(new Activity(actName, cals));
                        System.out.printf("Added Activity %s with %.2f Kcal\n", actName, cals);
                    } else {
                        System.out.println("--Proceed--");
                    }
                }
        }
        return message;
    }

    /**
     * report method handles the printing of the tracker. It has access to
     * the List for Food eaten, and Activity performed.
     *
     * It also updates every time a food or activity is
     * added. And it is able to calculate for the total caloric intake
     * and total calorie burned, all of these to be displayed
     * for when the user desires to call this method.
     *
     * @return a message that prints all the details above.
     */
    public String report(){

        String message = "";
        double kg = 0.00012959782;
        double netCal = totalCalConsumed - totalBurned;
        String net = "";

        //PRINTS NET CALORIES FOR THE CURRENT RECORD
            System.out.printf("\nNet Calories for the day %.2f\n", netCal);

        //DETERMINES IF NET CALORIES IS GAIN/LOSE.
            if (netCal > 0) {
                net = "gain";
            } else {
                net = "lose";
            }
        //MAKES PROGRAM PRINT NON-NEGATIVE NUMBER.
            if (netCal < 0) {
                netCal = netCal * -1;
            }

            double weekCal = (netCal * 7) * kg;
            double monthCal = (netCal * 30) * kg;
            double threeMonthCal = (netCal * 90) * kg;
            double sixMonthCal = (netCal * 180) * kg;

        /*
        0 = NAME
        1 = SERVINGS
        2 = CALORIES OF THE FOOD
        3 = TOTAL (SERVINGS * CALORIES OF FOOD)
         */

        if(gui){
                message =   "<HTML>-----------------<BR>" +
                            "LIFESTYLE REPORT<BR>" +
                            "-----------------<BR>";
                message += "Food Consumed:<BR>";

                if(trackerFood.size() > 0){
                    for(int i = 0; i < trackerFood.size();i++){
                        message +=  trackerFood.get(i)[1] + " serving(s) of " + trackerFood.get(i)[0] + ", " + trackerFood.get(i)[3] + " Kcal.<BR>";

                    }
                }

                message +=  "-----------------<BR>" +
                            "Total Calories Consumed: " + totalCalConsumed + " Kcal.<BR>" +
                            "-----------------<BR>" +
                            "<BR>";

                message += "Activity Performed:<BR>";

                if(trackerActivities.size() > 0){
                    for(int i = 0; i < trackerActivities.size();i++){
                        message += trackerActivities.get(i)[1] + " hour(s) of " + trackerActivities.get(i)[0] + ", " + trackerActivities.get(i)[3] + " Kcal.<BR>";
                    }
                }
                message +=  "-----------------<BR>" +
                            "Total Calories Burned: " + totalBurned + " Kcal.<BR>" +
                            "-----------------<BR>" +
                            "<BR>" +

                            "If you keep up this lifestyle<BR>" +
                            "In a week, you will " + net + ", " + String.format("%.2f",weekCal) + " kilograms<BR>" +
                            "In a month, you will " + net + ", " + String.format("%.2f",monthCal) + " kilograms<BR>" +
                            "In a 3 months, you will " + net + ", " + String.format("%.2f",threeMonthCal) + " kilograms<BR>" +
                            "In a 6 months, you will " + net + ", " + String.format("%.2f",sixMonthCal) + " kilograms<BR>" +
                            "-----------------</HTML>";

        }

        else {
                System.out.println("-----------------");
                System.out.println("LIFESTYLE REPORT");
                System.out.println("-----------------");

                System.out.println("Food Consumed:");
                if (trackerFood.size() > 0) {
                    for (int i = 0; i < trackerFood.size(); i++) {
                        System.out.printf("%s serving(s) of %s, %s Kcal\n", trackerFood.get(i)[1], trackerFood.get(i)[0], trackerFood.get(i)[3]);
                    }
                    System.out.println("-----------------");
                    System.out.printf("Total Calories Consumed: %.2f kcal\n", totalCalConsumed);
                    System.out.println("-----------------");
                } else {
                    System.out.println("N/A");
                }

                System.out.println("\nActivities Performed:");
                if (trackerActivities.size() > 0) {
                    for (int i = 0; i < trackerActivities.size(); i++) {
                        System.out.printf("%s hour(s) of %s, %s Kcal\n", trackerActivities.get(i)[1], trackerActivities.get(i)[0], trackerActivities.get(i)[3]);
                    }
                    System.out.println("-----------------");
                    System.out.printf("Total Calories Burned: %.2f\n", totalBurned);
                    System.out.println("-----------------");
                } else {
                    System.out.println("N/A");
                }

                message = "\nIf you keep up this lifestyle\n" +
                        "In a week, you will " + net + "," + weekCal + " kilograms\n" +
                        "In a month, you will " + net + "," + monthCal + " kilograms\n" +
                        "In a 3 months, you will " + net + "," + threeMonthCal + " kilograms\n" +
                        "In a 6 months, you will " + net + "," + sixMonthCal + " kilograms\n" +
                        "-----------------";
        }
        return message;
    }

    /*
    ADD ONS ADD ONS ADD ONS #######################
    ADD ONS ADD ONS ADD ONS #######################
     */

    /**
     * viewTracker is a method that is not directly called.
     * It is used for the editing of servings of food, or hours
     * of activities performed. This displays the record food and
     * activity so that the user can choose which index and change.
     *
     * Sample print:
     * "1) 2 serving(s) of Chicken, 202 Kcal."
     *
     * assuming that the user added a Chicken to the list,
     * that has 101 Calories per serving.
     *
     * @param a String that is passed to the method, which dictates whether to view food or view activity.
     * @return a list of food or activity to the user when the method is called.
     */
    public String viewTracker(String a) {

        String message = "";
        //DISPLAYS THE LIST FOR FOOD
        if(!gui){
            if (a.equals("A")) {
                if (trackerFood.size() > 0) {
                    for (int i = 0; i < trackerFood.size(); i++) {
                        System.out.printf("%d) %s serving(s) of %s, %s Kcal\n", i, trackerFood.get(i)[1], trackerFood.get(i)[0], trackerFood.get(i)[3]);
                    }
                } else {
                    System.out.println("No intake for the day yet.");
                }
            } else if (a.equals("B")) {
                if (trackerActivities.size() > 0) {
                    for (int i = 0; i < trackerActivities.size(); i++) {
                        System.out.printf("%d) %s hour(s) of %s, %s Kcal\n", i, trackerActivities.get(i)[1], trackerActivities.get(i)[0], trackerActivities.get(i)[3]);
                    }
                } else {
                    System.out.println("No activites performed for the day yet.");
                }
            }
        }

        else{

            message += "<HTML>";
            if(a.equals("a")){
                message += "Index.)    servings<BR>";
                for(int i = 0; i < trackerFood.size(); i++){
                    message += String.format("%d.) %s servings(s) of %s, %s Kcal.<BR>",i , trackerFood.get(i)[1], trackerFood.get(i)[0], trackerFood.get(i)[3]);

                }
            }
            else if (a.equals("b")) {
                message += "Index.)    hours<BR>";
                for(int i = 0; i < trackerActivities.size(); i++){
                    message += String.format("%d.) %s hour(s) of %s, %s Kcal<BR>", i, trackerActivities.get(i)[1], trackerActivities.get(i)[0], trackerActivities.get(i)[3]);
                }
            }
            message += "</HTML>";
        }
        return message;
    }

    /*
    0 = NAME
    1 = SERVINGS
    2 = CALORIES OF THE FOOD
    3 = TOTAL (SERVINGS * CALORIES OF FOOD)
    */

    /**
     * editFood handles the editing of servings of the food
     * that the user specifies based on the index provided
     * by the viewTracker method.
     *
     * A user the index, and the new serving of the food to
     * initialize the editFood process.
     *
     * @param a an integer that contains the index of the food based on the viewTracker.
     * @param b a double that the method uses to update the servings.
     * @return a message that the servings of food is updated.
     */
    public String editFood(int a, double b){

        int indexNum = a;
        double newServing = b;
        String message = "";

        if(indexNum <= trackerFood.size()){
            //TAKES THE ORIGINAL VALUES OF THE LIST
            double previousServing = Double.parseDouble(trackerFood.get(indexNum)[1]);
            double FoodCalories = Double.parseDouble(trackerFood.get(indexNum)[2]);

            //REMOVES THE OLD CALORIE INTAKE
            totalCalConsumed -= previousServing*FoodCalories;

            //EDITS THE SERVINGS WHICH CHANGES THE TOTAL CALORIES
            trackerFood.get(indexNum)[1] = Double.toString(newServing);
            trackerFood.get(indexNum)[3] = Double.toString(newServing*FoodCalories);

            //ADDS THE NEW CALORIE INTAKE
            totalCalConsumed += newServing*FoodCalories;
            message = String.format("Changed %s's serving(s) to %s, %s new Kcal.", trackerFood.get(indexNum)[0],trackerFood.get(indexNum)[1],trackerFood.get(indexNum)[3]);
        }
        else{
            System.out.println("The specified index does not exist.");
            message = "The specified index does not exist.";
        }
        return message;
    }

    /**
     * editActivity is similar to the editFood, it handles
     * the updating of hours performing that activity should the user
     * have a wrong input or desires to change the time that they performed
     * the activity.
     *
     * The user also inputs an index based on the viewTracker, and
     * a double that changes the hours of performing the specified activity index.
     *
     * @param a an integer that is based from the viewTracker, which identifies the activity.
     * @param b a double that is used to update the previous hours performed on an activity.
     * @return a message that the hours performed of that activity has been updated.
     */
    public String editActivity(int a, double b){

        int indexNum = a;
        double newHours = b;
        String message = "";

        if(indexNum <= trackerActivities.size()) {

            double previousHours = Double.parseDouble(trackerActivities.get(indexNum)[1]);
            double BurnedCalories = Double.parseDouble(trackerActivities.get(indexNum)[2]);

            totalBurned -= previousHours * BurnedCalories;

            trackerActivities.get(indexNum)[1] = Double.toString(newHours);
            trackerActivities.get(indexNum)[3] = Double.toString(newHours * BurnedCalories);

            totalBurned += newHours * BurnedCalories;
            message = String.format("Changed %s's hour(s) to %s, %s new Kcal.", trackerFood.get(indexNum)[0],trackerFood.get(indexNum)[1],trackerFood.get(indexNum)[3]);
        }
        else{
            System.out.println("The specified index does not exist.");
            message = "The specified index does not exist.";
        }
        return message;
    }

    /**
     * Tells the LifeStyleTracker.java that the object instance
     * is using a GUI, not the terminal.
     *
     * @param a if the GUI is used, a boolean true value is passed to that specific instance.
     * @return
     */
    public boolean isGui(boolean a){
        return gui = a;
    }
}