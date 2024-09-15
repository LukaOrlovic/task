package com.plenigo.task.shell;

import com.plenigo.task.entity.Customer;
import com.plenigo.task.entity.Invoice;
import com.plenigo.task.entity.Order;
import com.plenigo.task.services.csv.CsvExporter;
import com.plenigo.task.services.plenigosdk.PlenigoSdkService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ShellComponent
@AllArgsConstructor
public class ShellEntryPoint {

    private PlenigoSdkService plenigoSdkService;
    private CsvExporter csvExporter;

    @ShellMethod(key = "csv-orders", value = "This will get orders and persist them to a CSV file")
    public void createOrdersCsv() {


        System.out.println("Starting");

        HashMap<Long, Customer> ordersCustomerMap = new HashMap<>();
        HashMap<Long, Invoice> ordersInvoiceMap = new HashMap<>();
        List<Order> orders = plenigoSdkService.getList(Order.class, "/orders", 300, 10);

        for (Order order : orders){
            Customer customer = plenigoSdkService.get(Customer.class, "/customers/" + order.getCreatedBy(), 1);
            if(customer != null){
                ordersCustomerMap.put(order.getOrderId(), customer);
            }

            List<Invoice> invoices = plenigoSdkService.searchList(Invoice.class, "/invoices/", Integer.MAX_VALUE, 11, Map.of("orderId", String.valueOf(order.getOrderId())));
            for(Invoice invoice : invoices){
                ordersInvoiceMap.put(order.getOrderId(), invoice);
            }
        }

        csvExporter.createCsv(orders, ordersCustomerMap, ordersInvoiceMap);

        System.out.println("ALL DONE");


    }
}
