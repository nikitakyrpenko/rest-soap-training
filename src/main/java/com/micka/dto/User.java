package com.micka.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private Integer id;

    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid firstName")
    private String firstName;

    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid lastName")
    private String lastName;

    private String email;

    @Size(min = 2)
    private String password;

    private User(Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
    }

    public User(){}

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static Builder builder(){
        return new Builder();
   }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class Builder{
        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public Builder withId(Integer id){
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withPassword(String password){
            this.password = password;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
