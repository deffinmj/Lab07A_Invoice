import java.util.List;

public class Invoice {
    private InvoiceAddress customerAddress;
    private List<ItemEntry> itemEntriesList;


    public Invoice(InvoiceAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<ItemEntry> getitemEntriesList() {
        return itemEntriesList;
    }

    public void setitemEntriesList(List<ItemEntry> itemEntriesList) {
        this.itemEntriesList = itemEntriesList;
    }


    public double calculateTotal() {
        double total = 0.0;
        if (itemEntriesList != null) {
            for (ItemEntry detail : itemEntriesList) {
                total += detail.calculateTotal();
            }
        }
        return total;
    }

    public String getFormattedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice\n");
        sb.append("Customer Address: ").append(customerAddress.toString()).append("\n");
        sb.append("Order Details:\n");
        if (itemEntriesList != null) {
            for (ItemEntry detail : itemEntriesList) {
                sb.append(detail.toString()).append("\n");
            }
        }
        sb.append("Total Amount Due: $").append(calculateTotal());
        return sb.toString();
    }
}
