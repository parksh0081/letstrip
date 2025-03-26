package com.example.letstrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class TransportController {

    @GetMapping("/transport")
    public String showTransportPage() {
        return "transport/transport"; // /transport/transport.html 
    }
    
    @GetMapping("/train")
    public String showTrainPage() {
        return "transport/train"; // /transport/train.html 
    }
    
    @GetMapping("/bus")
    public String showBusPage() {
        return "transport/bus"; // /transport/bus.html 
    }
}

