package Json;

import com.google.gson.annotations.SerializedName;

public class Car {
    @SerializedName("theBrand")
    private String brand;
    private int doors;

    public Car() {
        this.brand = null;
        this.doors = 0;
    }
    public Car(String brand, int doors) {
        this.brand = brand;
        this.doors = doors;
    }
    public String getBrand() {
        return brand;
    }
    public int getDoors() {
        return doors;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setDoors(int doors) {
        this.doors = doors;
    }
}
