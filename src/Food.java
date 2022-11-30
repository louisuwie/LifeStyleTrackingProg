/**
 @Author Louis G. Binwag III (200747)
 @Date December 5, 2022

 Food.java handles the instances for adding Food into the system.
 It holds the food type and its caloric count,
 which would be used throughout the program.

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

public class Food {

    private String foodName;
    private double calorieCount;


    public Food(String f, double c){
        foodName = f;
        calorieCount = c;
    }

    public String getFoodName(){
        return foodName;
    }

    public double getFoodCalories(){
        return calorieCount;
    }

    public void updateCalories(double c){
        calorieCount = c;
    }

}
