package com.example.homies;

import android.net.Uri;

public class Person
{
    private String name;
    private String gender;
    private int age;
    private String profilePicture;
    private String personalInfo;
    boolean isDataSet = false;
    private String mail;
    private boolean isFavorite = false;
    private boolean isCollapsed = true;
    private Uri uri;

    public String getMail() {
        return mail;
    }

    public Person setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public Person setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
        return this;
    }

    public boolean isDataSet() {
        return isDataSet;
    }

    public Person setDataSet(boolean dataSet) {
        isDataSet = dataSet;
        return this;
    }

    public Uri getUri() {
        return uri;
    }

    public Person setUri(Uri uri) {
        this.uri = uri;
        return this;
    }

    public String getName() {
        return name;
    }


    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Person setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Person setFavorite(boolean favorite) {
        isFavorite = favorite;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public Person setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public String getOverview() {
        return personalInfo;
    }

    public Person setOverview(String personalInfo) {
        this.personalInfo = personalInfo;
        return this;
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public Person setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
        return this;
    }
}
