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

    public LifeStyleTracker(){
        foods = new ArrayList<Food>();
        activities = new ArrayList<Activity>();

        trackerFood = new ArrayList<String[]>();
        trackerActivities = new ArrayList<String[]>();
        totalCalConsumed = 0;
        gui = false;
    }

    public String addFood(String n, double c){

        String message = "";

        boolean foundFood = false;

        for(int i = 0; i < foods.size(); i++){
            if(foods.get(i).getFoodName().equals(n)){

                totalCalConsumed -= foods.get(i).getFoodCalories();//REMOVES PREVIOUSLY CONSUMED CALORIES

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

    public String addActivity(String n, double c){

        String message = "";

        boolean foundActivity = false;

        for(int i = 0; i < activities.size();i++){
            if(activities.get(i).getActivityName().equals(n)){

                totalBurned -= activities.get(i).getCalorieCount(); //REMOVES PREVIOUSLY CONSUMED CALORIES

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
    public boolean isGui(boolean a){
        return gui = a;
    }


}