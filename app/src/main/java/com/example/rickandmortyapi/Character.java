package com.example.rickandmortyapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Character implements Parcelable {
    private String name, status, species, type, gender, image;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Character(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //public String getOrigin() {
     //   return origin;
   // }

    //public void setOrigin(String origin) {
    //    this.origin = origin;
    //}

    //public String getLocation() {
    //    return location;
    //}

    //public void setLocation(String location) {
    //    this.location = location;
    //}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.status);
        dest.writeString(this.species);
        dest.writeString(this.type);
        dest.writeString(this.gender);
        dest.writeString(this.image);
        dest.writeInt(this.number);
    }

    protected Character(Parcel in) {
        this.name = in.readString();
        this.status = in.readString();
        this.species = in.readString();
        this.type = in.readString();
        this.gender = in.readString();
        this.image = in.readString();
        this.number = in.readInt();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
