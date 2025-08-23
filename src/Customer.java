public class Customer {
    private int id;
    private String name;
    private String email;
    private int phone;

    public Customer(int id, String name, String email, int phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public int getPhone() {
        return phone;
    }

    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }
}
