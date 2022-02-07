package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TypeObj {
    private static ObservableList<String> nonUniqueListType = FXCollections.observableArrayList();

    public static void addTypes2List(String thisType){
        nonUniqueListType.add(thisType);
    }
}
