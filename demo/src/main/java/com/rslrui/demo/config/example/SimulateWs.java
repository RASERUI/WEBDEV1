package com.rslrui.demo.config.example;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.rslrui.demo.dto.Messages;

@Component
public class SimulateWs implements CommandLineRunner {

    private final SimpMessagingTemplate messagingTemplate;

    public SimulateWs(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.isBlank()) {
                continue;
            }

            Messages msg = new Messages(input, "SERVER");
            messagingTemplate.convertAndSend("/topic/channel1", msg);
            System.out.println("***** server sent a message: " + input);
        }
    }
}
