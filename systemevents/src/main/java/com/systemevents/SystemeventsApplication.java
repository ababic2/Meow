package com.systemevents;

import com.microservice.donation.DonationApplication;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SystemeventsApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder
				.forPort(8080)
				.addService(new DonationApplication()).build();

		server.start();
		server.awaitTermination();
	}



}
