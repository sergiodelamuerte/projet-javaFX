module appli.accueil {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens appli.accueil to javafx.fxml;
    exports appli.accueil;
}