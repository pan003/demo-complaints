package com.example;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@SpringBootApplication
public class DemoComplaintsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoComplaintsApplication.class, args);
    }

    @RestController
    public static class ComplaintsAPI {

        private final ComplaintQueryObjectRepository repository;
        private final CommandGateway commandGateway;

        public ComplaintsAPI(ComplaintQueryObjectRepository repository, CommandGateway commandGateway) {
            this.repository = repository;
            this.commandGateway = commandGateway;
        }

        @PostMapping
        public CompletableFuture<String> fileComplaints(@RequestBody Map<String, String> request) {
            String id = UUID.randomUUID().toString();
            return commandGateway.send(new FileComplaintCommand(id, request.get("company"), request.get("description")));
        }

        @GetMapping
        public List<ComplaintQueryObject> findAll() {
            return repository.findAll();
        }

        @GetMapping("/{id}")
        public ComplaintQueryObject findOne(@PathVariable String id) {
            return repository.getOne(id);
        }

    }

    @Aggregate
    public static class Complaint {
        @AggregateIdentifier
        private String identifier;

        @CommandHandler
        public Complaint(FileComplaintCommand command) {
            apply(new ComplaintFiledEvent(command.getId(), command.getCompany(), command.getDescription()));
        }

        public class ComplaintFiledEvent {
            public ComplaintFiledEvent(String id, String company, String description) {
            }
        }
    }


    public static class FileComplaintCommand {

        private final String id;
        private final String company;
        private final String description;

        public FileComplaintCommand(String id, String company, String description) {
            this.id = id;
            this.company = company;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getCompany() {
            return company;
        }

        public String getDescription() {
            return description;
        }
    }

}
