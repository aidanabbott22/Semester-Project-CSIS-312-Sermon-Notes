package csis312.semesterprojectcsis312;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SermonNotesController implements Initializable {

    // Instance Variables
    private SermonNotesToPDF pdfMaker;

    @FXML
    private TextArea txtBoxContent;

    @FXML
    private Button generateButton;

    @FXML
    private TextField txtFileName;

    @FXML
    private Button btnClear;

    // When the user clicks the clear button, it clears the text box
    @FXML
    void evtClearButton(ActionEvent event) {
        txtBoxContent.clear();
    }

    // When the user clicks the generate button, it creates a PDF from the input text
    @FXML
    void evtGeneratePdf(ActionEvent event) {
        String fileName = txtFileName.getText().trim();
        String content = txtBoxContent.getText().trim();

        // Validate input fields
        if (fileName.isEmpty()) {
            showAlert("Error", "Filename Required", "Please enter a filename before generating the PDF.");
            return;
        }
        if (content.isEmpty()) {
            showAlert("Error", "No Content", "Please enter text before generating the PDF.");
            return;
        }

        // Generate PDF
        boolean success = pdfMaker.createPdf(fileName, content);
        if (success) {
            showAlert("Success", "PDF Created", "The PDF has been successfully generated.");
        } else {
            showAlert("Error", "PDF Generation Failed", "An error occurred while generating the PDF.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pdfMaker = new SermonNotesToPDF();
    }

    // Utility method to display alerts
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}