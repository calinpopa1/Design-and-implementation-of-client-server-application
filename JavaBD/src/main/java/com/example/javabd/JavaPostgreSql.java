package com.example.javabd;
import javafx.scene.control.TextInputDialog;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Class{
    public static Connection connection = null;
}

public class JavaPostgreSql {


    public static void connection(String database, String username, String password){

        String url ="jdbc:postgresql://localhost:5432/"+database;
        String user = username;
        String pass = password;

        try
        {
            Class.connection = DriverManager.getConnection(url,user,password);
            System.out.println("DB connected!");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        finally {
            if(Class.connection == null){
                System.exit(-1);
            }
        }

    }

    public static List<String> readFromDatabase(Integer question){

            switch (question){
                case 1:
                    try{
                        Statement stmt = Class.connection.createStatement(
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                        ResultSet sqlReturnValues = stmt.executeQuery("SELECT pays, score from t8 where (score= (Select MAX(score) from t8));");

                        List<String> returnValues = new ArrayList<>();

                        while(sqlReturnValues .next()){
                            returnValues.add(String.valueOf(sqlReturnValues.getString(1)));
                            returnValues.add(String.valueOf(sqlReturnValues.getString(2)));
                        }

                        return returnValues;


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case 2:
                    try{
                        Statement stmt = Class.connection.createStatement(
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                        ResultSet sqlReturnValues = stmt.executeQuery("SELECT prenom, nom FROM a7, a8 WHERE (a7.nb_buts = a8.res) GROUP BY prenom, nom;");

                        List<String> returnValues = new ArrayList<>();

                        while(sqlReturnValues .next()){
                            returnValues.add(String.valueOf(sqlReturnValues.getString(1)));
                            returnValues.add(String.valueOf(sqlReturnValues.getString(2)));
                        }

                        return returnValues;


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case 3:
                    try{
                        Statement stmt = Class.connection.createStatement(
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                        ResultSet sqlReturnValues = stmt.executeQuery("SELECT prenom, nom, COUNT(nb_passes) as nb_passes from r3 group by prenom,nom;");

                        List<String> returnValues = new ArrayList<>();

                        while(sqlReturnValues .next()){
                            returnValues.add(String.valueOf(sqlReturnValues.getString(1)));
                            returnValues.add(String.valueOf(sqlReturnValues.getString(2)));
                            returnValues.add(String.valueOf(sqlReturnValues.getString(3)));
                        }

                        return returnValues;


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;

                case 4:
                    try{
                        Statement stmt = Class.connection.createStatement(
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                        ResultSet sqlReturnValues = stmt.executeQuery("select prenom, nom from Arbitre natural join b3;");

                        List<String> returnValues = new ArrayList<>();

                        while(sqlReturnValues .next()){
                            returnValues.add(String.valueOf(sqlReturnValues.getString(1)));
                            returnValues.add(String.valueOf(sqlReturnValues.getString(2)));
                        }

                        return returnValues;


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
            }

        return null;
    }
}