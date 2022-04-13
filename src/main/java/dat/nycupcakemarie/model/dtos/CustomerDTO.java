package dat.nycupcakemarie.model.dtos;

public class CustomerDTO {
    private int user_id;
    private int role_id;
    private String email;
    private String firstname;
    private String lastname;
    private int phone_no;
    private int balance;

    public CustomerDTO(int user_id, int role_id, String firstname, String lastname, int balance, int phone_no, String email) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone_no = phone_no;
        this.balance = balance;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(int phone_no) {
        this.phone_no = phone_no;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "CustomerDTO{" +
                "user_id=" + user_id +
                ", role_id=" + role_id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone_no=" + phone_no +
                ", balance=" + balance +
                '}';
    }
}
