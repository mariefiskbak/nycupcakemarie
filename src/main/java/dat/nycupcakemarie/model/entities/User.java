package dat.nycupcakemarie.model.entities;

import java.util.Objects;

public class User
{
    private String email;
    private String password;
    private int role_id;
    private String firstname;
    private String lastname;
    private int balance;
    private int phone_no;
    private int user_id;

    public User(int user_id, String password, int role_id, String firstname, String lastname, int balance, int phone_no, String email) {
        this.user_id = user_id;
        this.password = password;
        this.role_id = role_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.balance = balance;
        this.phone_no = phone_no;
        this.email = email;
    }



    public int getRole_id() {
        return role_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPhone_no() {
        return phone_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBalance() {
        return balance;


    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", password='" + password + '\'' +
                ", role_id=" + role_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", balance=" + balance +
                ", phone_no=" + phone_no +
                ", email=" + email +
                '}';
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "brugerNavn='" + email + '\'' +
//                ", kodeord='" + password + '\'' +
//                ", rolle='" + roleId + '\'' +
//                '}';
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) &&
                getRoleId() == user.getRoleId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getRoleId());
    }
}
