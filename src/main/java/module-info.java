module com.etudiant.managestudent {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.etudiant.managestudent to javafx.fxml;
    exports com.etudiant.managestudent;
    exports com.etudiant.managestudent.views;
    opens com.etudiant.managestudent.views to javafx.fxml;
}