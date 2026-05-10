module appli.accueil {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires spring.security.crypto;
    requires java.mail;

    opens appli.accueil to javafx.fxml;
    opens appli.user to javafx.fxml;
    opens model to javafx.base, javafx.fxml;
    exports appli.accueil;

}