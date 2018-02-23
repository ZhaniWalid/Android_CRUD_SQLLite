package com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.entities;

/**
 * Created by HP on 20/02/2018.
 */

public class Person {

   private int id;
   private String FirstName;
   private String LastName;
   private String Address;
   private String PhoneNumber;

   public Person() {
   }

   public Person(int id, String firstName, String lastName, String address, String phoneNumber) {
      this.id = id;
      FirstName = firstName;
      LastName = lastName;
      Address = address;
      PhoneNumber = phoneNumber;
   }

   public Person(String firstName, String lastName, String address, String phoneNumber) {
      FirstName = firstName;
      LastName = lastName;
      Address = address;
      PhoneNumber = phoneNumber;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFirstName() {
      return FirstName;
   }

   public void setFirstName(String firstName) {
      FirstName = firstName;
   }

   public String getLastName() {
      return LastName;
   }

   public void setLastName(String lastName) {
      LastName = lastName;
   }

   public String getAddress() {
      return Address;
   }

   public void setAddress(String address) {
      Address = address;
   }

   public String getPhoneNumber() {
      return PhoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      PhoneNumber = phoneNumber;
   }
}
