package edu.mi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("John", "Wayne");
        Employee e2 = new Employee("Maria", "Calas");
        Employee[] database = {e1, e2};

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String test1 = gson.toJson(e1); // converting to json
        System.out.println(test1);
        Employee e3 = gson.fromJson(test1, Employee.class);
        System.out.println(e3);

        System.out.println(gson.toJson(database));

        File file = new File("test1.json");
        // try with resources
        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(database, fileWriter);
        } catch (IOException e) {
            System.out.println("I could not write to a file");
        }

        Employee[] testDatabase = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            testDatabase=gson.fromJson(bufferedReader,Employee[].class);

        } catch (IOException e) {
            System.out.println("I could not read from file");
        }

        System.out.println(Arrays.toString(testDatabase));
    }
}