import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame {
    JTextArea displayArea;
    JTextField productTitleField;
    JTextField streetField;
    JTextField cityField;
    JTextField stateField;
    JTextField zipField;
    JTextField countryField;
    JTextField productNameField;
    JTextField productPriceField;
    JTextField productQuantityField;
    JButton addButton;
    JButton displayButton;
    Invoice invoice;


    public InvoiceFrame() {
        JPanel mainPanel = new JPanel();
        JPanel addressPanel = new JPanel();
        JPanel productPanel = new JPanel();
        JPanel invoicePanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        addressPanel.setLayout(new GridLayout(5, 2));
        addressPanel.add(new JLabel("Street: "));
        streetField = new JTextField();
        addressPanel.add(streetField);
        addressPanel.add(new JLabel("City: "));
        cityField = new JTextField();
        addressPanel.add(cityField);
        addressPanel.add(new JLabel("State: "));
        stateField = new JTextField();
        addressPanel.add(stateField);
        addressPanel.add(new JLabel("ZIP: "));
        zipField = new JTextField();
        addressPanel.add(zipField);
        addressPanel.add(new JLabel("Country: "));
        countryField = new JTextField();
        addressPanel.add(countryField);

        productTitleField = new JTextField(20);
        productPanel.setLayout(new GridLayout(4, 2));

        productPanel.add(new JLabel("Product Name: "));
        productNameField = new JTextField();
        productPanel.add(productNameField);
        productPanel.add(new JLabel("Product Price: "));
        productPriceField = new JTextField();
        productPanel.add(productPriceField);
        productPanel.add(new JLabel("Product Quantity: "));
        productQuantityField = new JTextField();
        productPanel.add(productQuantityField);

        productPanel.add(new JLabel(""));
        addButton = new JButton("Add Product to Invoice");
        addButton.addActionListener(e -> addProductToInvoice());

        productPanel.add(addButton);

        displayButton = new JButton("Display Invoice");
        displayButton.addActionListener(e -> displayInvoice());


        displayArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        invoicePanel.setLayout(new GridLayout(2, 1));
        invoicePanel.add(scrollPane);
        invoicePanel.add(displayButton);

        JLabel mainTitle = new JLabel("Invoice Form", JLabel.CENTER);
        mainTitle.setFont(new Font("Serif", Font.BOLD, 30));
        mainPanel.add(mainTitle);
        mainPanel.add(addressPanel);
        mainPanel.add(productPanel);
        mainPanel.add(invoicePanel);
        add(mainPanel);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void addProductToInvoice() {

        if (invoice == null) {
            invoice = new Invoice(new InvoiceAddress(streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText(), countryField.getText()));
            invoice.setitemEntriesList(new ArrayList<>());
        }

        Product product = new Product(productNameField.getText(), Double.parseDouble(productPriceField.getText()));

        ItemEntry detail = new ItemEntry(product, Integer.parseInt(productQuantityField.getText()));

        invoice.getitemEntriesList().add(detail);

        productNameField.setText("");
        productPriceField.setText("");
        productQuantityField.setText("");
    }

    private void displayInvoice() {
        if (invoice != null) {
            StringBuilder output = new StringBuilder();
            output.append(invoice.getFormattedString());
            displayArea.setText(output.toString());
        }
    }

    public static void main(String[] args) {
        new InvoiceFrame();
    }
}
