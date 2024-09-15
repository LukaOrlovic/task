package com.plenigo.task.services.csv;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.plenigo.task.entity.Customer;
import com.plenigo.task.entity.Invoice;
import com.plenigo.task.entity.Order;
import com.plenigo.task.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CsvExporter {

    public void createCsv(List<Order> orders, Map<Long, Customer> customers, Map<Long, Invoice> invoices)
    {
        List<CsvItem> csvItems = new ArrayList<>();

        for (Order order : orders) {
            for (OrderItem item : order.getItems()) {
                Customer customer = customers.get(order.getInvoiceCustomerId());
                Invoice invoice = invoices.get(order.getOrderId());

                CsvItem csvItem = new CsvItem(
                        order.getOrderId(),
                        item.getPosition(),
                        item.getTitle(),
                        item.getPrice(),
                        item.getTax(),
                        customer != null ? customer.getCustomerId() : null,
                        customer != null ? customer.getEmail() : null,
                        customer != null ? customer.getCreatedDate() : null,
                        invoice != null ? invoice.getInvoiceId() : null,
                        invoice != null ? invoice.getInvoiceDate() : null
                );
                csvItems.add(csvItem);
            }
        }

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(CsvItem.class).withHeader();

        File file = new File("all.csv");
        try {
            csvMapper.writerFor(CsvItem.class)
                    .with(schema)
                    .writeValues(file)
                    .writeAll(csvItems);
        } catch (IOException e) {
            System.out.println("Error creating CSV");
        }
    }
}
