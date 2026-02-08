package com.ttknp.basicapi.controller;

import com.ttknp.basicapi.dto.OrderItemDTO;
import com.ttknp.basicapi.entities.FileType;
import com.ttknp.basicapi.entities.OrderItem;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

/// Note, when you want to use a res header you have to Expose it by exposedHeaders={} for Expose specific headers for all methods in this controller
/// Because the default it won't see it
@CrossOrigin(originPatterns = "http://localhost:4200",exposedHeaders = {"X-Custom-Header","File-Name"})
@RestController
@RequestMapping(value = "/api/order-item")
public class OrderItemController {

    private final OrderItemDTO orderItemDTO;

    public OrderItemController(OrderItemDTO orderItemDTO) {
        this.orderItemDTO = orderItemDTO;
    }

    @GetMapping(value = "/reads")
    private ResponseEntity<List<OrderItem>> readsOrderItems() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON) // optional
                .body(orderItemDTO.getOrderItems());
    }

    @GetMapping(value = "/reads-for-jasper-report")
    private ResponseEntity<List<OrderItem>> readsOrderItemsForJasper() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderItemDTO.getOrderItemsForJasperReport());
    }

    @GetMapping("/reads-report")
    private ResponseEntity<Resource>  readsOrderItemsAsReport(@RequestParam("fileType") String fileType)  {
        HashMap<String,byte[]> map = orderItemDTO.getOrderItemsHasMapReport(fileType);
        if (!map.isEmpty()) {
            List<String> keySet = map.keySet().stream().toList();
            ByteArrayResource resource = new ByteArrayResource(map.get(keySet.get(0)));
            return ResponseEntity.ok()
                    .header(CONTENT_DISPOSITION, "attachment; filename=\"" + keySet.get(0) + "\"")
                    .header("File-Name", keySet.get(0))
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        else {
            throw new RuntimeException("File Download Failed");
        }
    }

    @PostMapping("/reads-report")
    private ResponseEntity<Resource>  readsOrderItemsAsReport(@RequestBody FileType fileType)  {
        HashMap<String,byte[]> map = orderItemDTO.getOrderItemsHasMapReport(fileType.getFileExtension());
        if (!map.isEmpty()) {
            List<String> keySet = map.keySet().stream().toList();
            ByteArrayResource resource = new ByteArrayResource(map.get(keySet.get(0)));
            return ResponseEntity.ok()
                    // Set Content-Disposition to attachment for download
                    .header(CONTENT_DISPOSITION, "attachment; filename=\"" + keySet.get(0) + "\"")
                    .header("File-Name", keySet.get(0))
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        else {
            throw new RuntimeException("File Download Failed");
        }
    }

    // @PostMapping("/preview-report")
    @GetMapping("/preview-report") // For pdf only
    private ResponseEntity<Resource>  previewOrderItemsAsReport()  {
        FileType fileType = new FileType();
        fileType.setFileExtension("PDF");
        HashMap<String,byte[]> map = orderItemDTO.getOrderItemsHasMapReport(fileType.getFileExtension());
        if (!map.isEmpty()) {
            List<String> keySet = map.keySet().stream().toList();
            ByteArrayResource resource = new ByteArrayResource(map.get(keySet.get(0)));
            return ResponseEntity.ok()
                    // Set Content-Disposition to inline for preview
                    .header(CONTENT_DISPOSITION, "inline; filename=\"" + keySet.get(0) + "\"")
                    .header("File-Name", keySet.get(0))
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_PDF) // change only contentType for review report
                    .body(resource);
        }
        else {
            throw new RuntimeException("Preview File Failed");
        }
    }


    @PostMapping("/reads-report/{datetime}")
    private ResponseEntity<Resource>  readsOrderItemsAsReportWhereLike(@RequestBody FileType fileType,@PathVariable String datetime)  {
        HashMap<String,byte[]> map = orderItemDTO.getOrderItemsHasMapReport(fileType.getFileExtension(),datetime);
        if (!map.isEmpty()) {
            List<String> keySet = map.keySet().stream().toList();
            ByteArrayResource resource = new ByteArrayResource(map.get(keySet.get(0)));
            return ResponseEntity.ok()
                    .header(CONTENT_DISPOSITION, "attachment; filename=\"" + keySet.get(0) + "\"")
                    .header("File-Name", keySet.get(0))
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        else {
            throw new RuntimeException("File Download Failed");
        }
    }


}
