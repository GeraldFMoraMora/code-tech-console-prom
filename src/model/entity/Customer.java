package model.entity;

import java.util.Date;

public class Customer {

    private String id;
    private String firstname;
    private String lastname;
    private String phone;
    private Date birthdate;

    public Customer(String id, String firstname, String lastname, String phone, Date birthdate){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.birthdate = birthdate;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getPhone() {
        return phone;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    
}
