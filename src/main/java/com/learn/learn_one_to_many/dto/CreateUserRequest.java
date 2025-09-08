package com.learn.learn_one_to_many.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class CreateUserRequest {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    private String password;

    private List<AddressInput> addresses;

    // getters / setters

    public static class AddressInput {
        private String city;
        private String state;
        private String address;
        // getters & setters
        public String getCity(){return city;}
        public void setCity(String city){this.city = city;}
        public String getState(){return state;}
        public void setState(String state){this.state = state;}
        public String getAddress(){return address;}
        public void setAddress(String address){this.address = address;}
    }

    // getters & setters for outer class
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<AddressInput> getAddresses() { return addresses; }
    public void setAddresses(List<AddressInput> addresses) { this.addresses = addresses; }
}