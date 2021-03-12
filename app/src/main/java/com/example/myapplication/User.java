package com.example.myapplication;

public class User {

    public String Username;
    public String Last_Workout;
    public String age;
    public String height;
    public String weight;
    public String Sex;

    public User(){

    }

    public User(String Username, String Last_Workout, String age, String height, String weight, String Sex){
        this.Username = Username;
        this.Last_Workout = Last_Workout;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.Sex = Sex;
    }

    public String getUsername() {
        return Username;
    }

    public String getLast_Workout() {
        return Last_Workout;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getSex() {
        return Sex;
    }
}
