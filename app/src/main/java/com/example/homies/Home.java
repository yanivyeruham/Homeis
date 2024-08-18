package com.example.homies;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.InputStream;


public class Home
{
    private String city;
    private String street;
    private int numberOfRooms;
    private int apartmentSize;
    private int price;
    private int postalCode;
    private String profilePicture;
    private String mail;
    private boolean isFavorite = false;
    private boolean isCollapsed = true;
    boolean isDataSet = false;

    public String getMail() {
        return mail;
    }

    public Home setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public Home setPostalCode(int postalCode) {
        this.postalCode = postalCode;
        return this;
    }



    public boolean isDataSet() {
        return isDataSet;
    }

    public Home setDataSet(boolean dataSet) {
        isDataSet = dataSet;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public Home setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Home setFavorite(boolean favorite) {
        isFavorite = favorite;
        return this;
    }

    public Home()
    {

    }

    public String getCity() {
        return city;
    }

    public Home setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Home setStreet(String street) {
        this.street = street;
        return this;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public Home setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    public int getApartmentSize() {
        return apartmentSize;
    }

    public Home setApartmentSize(int apartmentSize) {
        this.apartmentSize = apartmentSize;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Home setPrice(int price) {
        this.price = price;
        return this;
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public Home setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
        return this;
    }
}
