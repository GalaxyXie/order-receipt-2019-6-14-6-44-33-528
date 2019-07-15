package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();


        appendHeader(output);
        appendOrderInformation(output);

        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            appendItemsInformation(output, lineItem);
            final double salesRate=.10;
            double salesTax = getSalesTax(lineItem, salesRate);
            totalSalesTax += salesTax;
            totalAmount += getTotal(lineItem, salesTax);
        }

        printTotalSalesTax(output, totalSalesTax, "Sales Tax");

        printTotalAmount(output, totalAmount, "Total Amount");
        return output.toString();
    }

    public void  printTotalSalesTax(StringBuilder output, double totalSalesTax, String s) {
        output.append(s).append('\t').append(totalSalesTax);
    }
    public void  printTotalAmount(StringBuilder output, double totalSalesTax, String s) {
        output.append(s).append('\t').append(totalSalesTax);
    }

    public double getTotal(LineItem lineItem, double salesTax) {
        return lineItem.totalAmount() + salesTax;
    }
    public double getSalesTax(LineItem lineItem, double salesRate) {
        return lineItem.totalAmount() * salesRate;
    }
    public void appendItemsInformation(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
    }
    public void appendHeader(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }
    public void appendOrderInformation(StringBuilder output) {
        String orderName=order.getCustomerName();
        String orderAddress=order.getCustomerAddress();
        output.append(orderName);
        output.append(orderAddress);
    }
}