import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JsonProbeersels {

    public static void main(String[] args) throws IOException {

    }

    //File handeling
    public static void maakNieuweTextFile(){
        try {
            File myObj = new File("Test1.txt");
            if (myObj.createNewFile()) { //maakt lege file aan in root directory van het project
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void maakNieuweTextFileInDirAbsolute(){
        try {
            //absolute path
            File myObj = new File("C:\\git\\UHasselt\\tests\\JsonTesten\\src\\main\\resources\\Test2.txt");
            if (myObj.createNewFile()) {               //maakt lege file aan in de gespecifieerde directory
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void maakNieuweTextFileInDirRelative(){
        try {
            String localDir = System.getProperty("user.dir");
            File myObj = new File(localDir + "\\src\\main\\resources\\Test3.txt");
            if (myObj.createNewFile()) {               //maakt lege file aan in de gespecifieerde directory
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void schrijfIetsInEenFile(){
        //OVERSCHRIJFT ALLES IN DE FILE
        try {
            FileWriter myWriter = new FileWriter("Test1.txt"); //maakt een nieuwe file aan als de file nog niet bestaat!!!!!
            myWriter.write("Overchrijft wat in de file staat\n lalaland"); //overschrijft alles in de file ale er al iets in stond
            myWriter.close(); //VERGEET DE CLOSE NIET
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void leesEenFile() {
        try {
            File myObj = new File("Test1.txt"); //GEEFT EEN ERROR ALS HIJ HET BESTAND MET DIE NAAM NIET VINDT
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void readFileExample() throws IOException {
        File myObj = new File("Test1.txt");
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Path: " + myObj.getPath());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("Canonical path: " + myObj.getCanonicalPath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " + myObj.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }
    public static void deleteFile(){
        File myObj = new File("Test1.txt");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    public static void makeDir() {
        try {
            File f = null;
            boolean bool = false;
            try {
                // returns pathnames for files and directory
                f = new File("Test");
                // create
                bool = f.mkdir(); //returns false als bv de directory al bestaat
                // print
                System.out.print("Directory created? " + bool);
            } catch (Exception e) {
                // if any error occurs
                e.printStackTrace();
            }
        } finally {

        }
    }
    public static void deleteDir(){
        File myObj = new File("Test");
        if (myObj.delete()) {
            System.out.println("Deleted the folder: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the folder.");
        }
    }

    //JSON handeling
    public static void maakJson(){
        //Maak een javaObject van een JSON object
        Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
            //Kan enkel stringified jsons lezen
        String json = "{\"theBrand\":\"Jeep\", \"doors\": 3}"; //LET OP DE SLASHES VOOR DE QUOTES!!!!!!!!!!!!!
        Car car = gson.fromJson(json, Car.class);
        System.out.println(car.getBrand());

        //Maak een JSON object van een javaObject
        Gson gson2 = new Gson();
        Car car2 = new Car();
        car2.setBrand("opel");
        car2.setDoors(2);
            //Kan enkel stringified jsons maken
        String json2 = gson2.toJson(car2);
        System.out.println(json2);
        //JavaScript
        /*
        var myObj = {"name": "John", "age": 31, "city": "New York"};
        var myJSON = JSON.stringify(myObj);
        var myObj = {name: "John", age: 31, city: "New York"};
        var myJSON = JSON.stringify(myObj);
         */
    }
    public static void readJson(){
        String json = "";
        try {
            File myObj = new File("oneCar.json"); //GEEFT EEN ERROR ALS HIJ HET BESTAND MET DIE NAAM NIET VINDT
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                json = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        Car car = gson.fromJson(json, Car.class);
        System.out.println(car.getBrand());
    }
}
