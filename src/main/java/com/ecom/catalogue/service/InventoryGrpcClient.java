package com.ecom.catalogue.service;

import com.ecom.inventory.grpc.InventoryRequest;
import com.ecom.inventory.grpc.InventoryResponse;
import com.ecom.inventory.grpc.InventoryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventoryGrpcClient {
    private static final Logger logger = LoggerFactory.getLogger(InventoryGrpcClient.class);

    public Map<Integer, Integer> getStock(List<Integer> products){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8000)
                .usePlaintext()
                .build();

        logger.info("Requesting inventory service for product stock");

        InventoryServiceGrpc.InventoryServiceBlockingStub stub = InventoryServiceGrpc.newBlockingStub(channel);
        InventoryRequest req = InventoryRequest.newBuilder().addAllProducts(products).build();
        InventoryResponse resp = stub.getInventory(req);
        logger.info("Response received over gRPC");
        channel.shutdown();
        return resp.getStockMap();
    }
}
