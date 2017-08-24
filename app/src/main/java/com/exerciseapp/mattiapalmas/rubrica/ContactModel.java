package com.exerciseapp.mattiapalmas.rubrica;


/**This class is the model of a list item inside the main activity.
 * Created by mattia palmas on 2017-08-11.
 */

public class ContactModel {
    private String name;
    private String phoneNumber;
    private String lastName;


    public ContactModel(String name, String lastName, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
    }

    public ContactModel(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }
}
