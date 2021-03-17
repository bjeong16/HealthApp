package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import kotlinx.android.parcel.Parcelize;


public class User implements Parcelable {

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

    protected User(Parcel in) {
        Username = in.readString();
        Last_Workout = in.readString();
        age = in.readString();
        height = in.readString();
        weight = in.readString();
        Sex = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Username);
        dest.writeString(Last_Workout);
        dest.writeString(age);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(Sex);
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
