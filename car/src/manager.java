public class manager {
    String name;
    String surname;
    static int pincode;
    int id;


    public manager(String name, String surname, int pincode, int id) {
        this.name = name;
        this.surname = surname;
        this.pincode = pincode;
        this.id = id;
    }
    public int getPincode(){
        return pincode;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return name;
    }
    public int getId(){
        return id;
    }



}
