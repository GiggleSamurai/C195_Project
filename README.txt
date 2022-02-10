Title: C195_Project
Application Purpose: Provide a friendly UI interface for SQL operation on database.
    Function such as generate report, view in month or week.
Author: Louis Wong
E-Mail: lwong33@wgu.edu
Application Version: v3
Date: 2-7-2022
IDE: IntelliJ IDEA 2021.3.2 (Community Edition)
JDK: 17(Oracle OpenJDK version 17.0.1)
JavaFX:JavaFX-SDK-17.0.1
JavaDataBaseConnector: mysql-connector-java-8.0.25.jar

Direction: Both SQL Driver & JavaFX SDK is included in "C195_Project\Dependencies_Modules",
    at VM option setting input "--module-path ${PATH_TO_FX} --add-modules javafx.fxml,javafx.controls,javafx.graphics".
    Application have two tables customer and appointment, each can be adding new data, update or delete.
    View will let you view all the appointments by month/week filter, report will return a summary of appointments.

Additional Report: Return last 10 user login information, if less than 10 will return all information.


