/**
 @Author Louis G. Binwag III (200747)
 @Date December 5, 2022

 Activity.java handles the nature of the Activity and how many calories
 that activity takes. How many calories it would be when doing (x) hours of
 this activity, which would be calculated for the user.

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

public class Activity {

    private String activityName;
    private double calorieCount;

    public Activity(String n, double c){
        activityName = n;
        calorieCount = c;

    }

    public String getActivityName(){
        return activityName;
    }

    public double getCalorieCount(){
        return calorieCount;
    }

    public void updateCalories(double c){
        calorieCount = c;
    }

}
