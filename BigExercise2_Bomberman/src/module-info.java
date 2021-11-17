module BigExercise2_Bomberman {
    requires javafx.controls;
    requires javafx.fxml;


    opens Solution.Design to javafx.fxml;
    exports Solution.Design;
}