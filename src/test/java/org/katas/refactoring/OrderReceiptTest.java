package org.katas.refactoring;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


public class OrderReceiptTest {
    @Test
    public void shouldPrintCustomerInformationOnOrder() {
        Order order = new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>());
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();

        assertThat(output).contains("Mr X", "Chicago, 60601");
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() {
        ArrayList<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems));

        String output = receipt.printReceipt();

        assertThat(output).contains(
                "milk\t10.0\t2\t20.0\n",
                "biscuits\t5.0\t5\t25.0\n",
                "chocolate\t20.0\t1\t20.0\n",
                "Sales Tax\t6.5",
                "Total Amount\t71.5"
        );

    }
    @Test
    public void shouldReturnItemInformation() {
        //given
        LineItem lineItem = new LineItem("milk",10.0,2);
        //when
        String description=lineItem.getDescription();
        double price=lineItem.getPrice();
        int quantity=lineItem.getQuantity();
        //then
        assertThat(description).contains("milk");
        assertEquals(price,10.0);
        assertSame(quantity,2);
    }
    @Test
    public void shouldReturnOderInformation() {
        //given
        Order order = new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>());
        String name=order.getCustomerName();
        String address=order.getCustomerAddress();
        List<LineItem>lineItems=order.getLineItems();
        //then
        assertThat(name).contains("Mr X");
        assertEquals("Chicago, 60601",address);
        assertEquals(new ArrayList<LineItem>(),lineItems);
    }

}
