package com.example;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MetricsResource {

    private final Map<Integer, String> items = new HashMap<>();
    private final MeterRegistry registry;
    private final AtomicInteger inProgressRequests;

    @Inject
    public MetricsResource(MeterRegistry registry) {
        this.registry = registry;
        this.inProgressRequests = registry.gauge("http_requests_in_progress", new AtomicInteger(0));
    }

    @GET
    public Response root() {
        return Response.ok(Map.of("message", "Hello World")).build();
    }

    @POST
    @Path("/items")
    public Response createItem(Item item) {
        int itemId = items.size() + 1;
        items.put(itemId, item.getName());
        recordMetrics("POST", "/items", 201);
        return Response.status(Response.Status.CREATED)
                .entity(Map.of("item_id", itemId, "name", item.getName(), "status", "created"))
                .build();
    }

    @GET
    @Path("/items/{itemId}")
    public Response readItem(@PathParam("itemId") int itemId) {
        if (!items.containsKey(itemId)) {
            recordMetrics("GET", "/items/{itemId}", 404);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("detail", "Item not found"))
                    .build();
        }
        recordMetrics("GET", "/items/{itemId}", 200);
        return Response.ok(Map.of("item_id", itemId, "name", items.get(itemId))).build();
    }

    @PUT
    @Path("/items/{itemId}")
    public Response updateItem(@PathParam("itemId") int itemId, Item item) {
        if (!items.containsKey(itemId)) {
            recordMetrics("PUT", "/items/{itemId}", 404);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("detail", "Item not found"))
                    .build();
        }
        items.put(itemId, item.getName());
        recordMetrics("PUT", "/items/{itemId}", 200);
        return Response.ok(Map.of("item_id", itemId, "name", item.getName(), "status", "updated")).build();
    }

    @DELETE
    @Path("/items/{itemId}")
    public Response deleteItem(@PathParam("itemId") int itemId) {
        if (!items.containsKey(itemId)) {
            recordMetrics("DELETE", "/items/{itemId}", 404);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("detail", "Item not found"))
                    .build();
        }
        items.remove(itemId);
        recordMetrics("DELETE", "/items/{itemId}", 200);
        return Response.ok(Map.of("item_id", itemId, "status", "deleted")).build();
    }

    private void recordMetrics(String method, String path, int status) {
        Tags tags = Tags.of(
                "method", method,
                "path", path,
                "status", String.valueOf(status)
        );
        registry.counter("http_request_total", tags).increment();
    }

    public static class Item {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}