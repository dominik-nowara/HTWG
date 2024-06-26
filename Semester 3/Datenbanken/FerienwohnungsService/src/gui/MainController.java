package gui;

import data.SqlUtility;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private TextField nameField, fromMoney, toMoney, roomsField;
    @FXML
    private ComboBox<String> countryDropdown;
    @FXML
    private VBox listContainer;
    @FXML
    private DatePicker fromDate, toDate;
    @FXML
    private CheckComboBox<String> equipmentDropdown;

    @FXML
    public void initialize() {
        fromDate.setValue(LocalDate.now());
        toDate.setValue(LocalDate.now());
        SqlUtility.setDropdowns(countryDropdown, equipmentDropdown);
    }

    @FXML
    private void handleSearch() {
        listContainer.getChildren().clear(); // Clear existing items

        List<String> equips = equipmentDropdown.getCheckModel().getCheckedItems().stream().toList();
        new Thread(() -> {
            if (equips.isEmpty()) {
                SqlUtility.setApartments(listContainer, countryDropdown.getValue(), fromMoney.getText(), toMoney.getText(), roomsField.getText(), fromDate.getValue(), toDate.getValue());
            }
            else {
                SqlUtility.setApartmentsWithEquip(listContainer, countryDropdown.getValue(), fromMoney.getText(), toMoney.getText(), roomsField.getText(), fromDate.getValue(), toDate.getValue(), equips);
            }
        }).start();
        /*
        // Mock data fetch
        for (int i = 0; i < 10; i++) {
            HBox itemBox = new HBox(2);
            ImageView imageView = new ImageView(new Image("https://image.petmd.com/files/styles/978x550/public/2020-10/2187242989_2eacb23b1e_z-slide1.jpg"));
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(300);  // Set desired width for the ImageView
            imageView.maxHeight(200);  // Set maximum height

            Label nameLabel = new Label("Item " + i);
            nameLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 24));
            Label priceLabel = new Label((10 + i) + " €");
            priceLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 16));
            Label starsLabel = new Label("★★★★☆"); // Simplified rating display
            starsLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 20));
            starsLabel.setTextFill(javafx.scene.paint.Color.GOLD);

            Label rooms = new Label("2");

            VBox itemDetails = new VBox(5);
            itemDetails.getChildren().addAll(nameLabel, starsLabel, priceLabel);

            itemBox.getChildren().addAll(imageView, itemDetails);
            itemBox.setSpacing(10);
            listContainer.getChildren().add(itemBox);
        }*/
    }

    public static void addApartmentItem(VBox listContainer, int id, String gotName, String gotPreis, String gotZimmer, String gotSize, float gotSterne, String gotBildlink, LocalDate fromDate, LocalDate toDate) {
        HBox itemBox = new HBox(2);
        ImageView imageView = new ImageView(new Image(gotBildlink));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(300);  // Set desired width for the ImageView
        imageView.maxHeight(200);  // Set maximum height
        imageView.minHeight(100);

        Label nameLabel = new Label(gotName);
        nameLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 24));
        Label priceLabel = new Label(gotPreis + " €");
        priceLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 16));
        Label starsLabel = new Label(gotSterne+ "★"); // Simplified rating display
        starsLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 20));
        starsLabel.setTextFill(javafx.scene.paint.Color.GOLD);
        Label sizelabel = new Label(gotSize + "m^2");
        Label roomLabel = new Label(gotZimmer + " Zimmer");
        Button bookButton = new Button("Buchen");
        bookButton.setOnMouseClicked(event -> {
            String returnValue = SqlUtility.bookApartment(id, fromDate, toDate, gotPreis);
            if (!returnValue.isEmpty()) {
                Notifications notificationBuilder = Notifications.create()
                        .title("Buchung fehlgeschlagen")
                        .text("Dieses Apartment konnte nicht gebucht werden! Fehler: " + returnValue)
                        .graphic(null) // or you can set a graphic(node)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.BOTTOM_RIGHT);

                notificationBuilder.showError();
            }
            else {
                Notifications notificationBuilder = Notifications.create()
                        .title("Buchung erfolgreich")
                        .text("Dieses Apartment wurde erfolgreich gebucht!")
                        .graphic(null) // or you can set a graphic(node)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.BOTTOM_RIGHT);

                notificationBuilder.showConfirm();
            }
        });

        VBox itemDetails = new VBox(5);
        VBox.setMargin(nameLabel, new javafx.geometry.Insets(10, 0, 0, 0));
        itemDetails.getChildren().addAll(nameLabel, starsLabel, priceLabel, sizelabel, roomLabel, bookButton);

        itemBox.getChildren().addAll(imageView, itemDetails);
        itemBox.setSpacing(10);
        itemBox.setMinHeight(200);

        Platform.runLater(() -> listContainer.getChildren().add(itemBox));
    }
}