package com.ttknp.basicapi.dto;

import com.ttknp.basicapi.entities.OrderItem;
import java.util.HashMap;
import java.util.List;

public interface OrderItemDTO {
    List<OrderItem> getOrderItems();
    // For testing json
    List<OrderItem> getOrderItemsForJasperReport();
    List<OrderItem> getOrderItemsForJasperReportLimit10();
    List<OrderItem> getOrderItemsForJasperReportWhereLikeDatetime(String datetime);
    // For testing download file
    HashMap<String,byte[]> getOrderItemsHasMapReport(String fileType);
    HashMap<String,byte[]> getOrderItemsHasMapReportApplyThread(String fileType);
    HashMap<String,byte[]> getOrderItemsHasMapReport(String fileType,String datetime);
}
