package com.netcracker.database;

import com.netcracker.model.Employee;

import java.io.*;
import java.util.Arrays;
import java.util.EnumMap;

public class DataBase {
    private final File dataBase = new File("src/main/resources/employee-data-base.txt");

    public void addEmployee(Employee employee){
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter writer = new FileWriter(dataBase, true);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(employee.toString() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public Employee searchEmployee(Employee employee){
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        BufferedReader bufferedReader = null;
        try{
            FileReader fileReader = new FileReader(dataBase);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split("\\|");
                if ((columns[0].equals(firstName)) && (columns[2].equals(lastName))) {
                    return new Employee(columns[0],
                            columns[1],
                            columns[2],
                            Integer.parseInt(columns[3]),
                            Integer.parseInt(columns[4]),
                            columns[5],
                            columns[6],
                            columns[7]);
                }

            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }


}
