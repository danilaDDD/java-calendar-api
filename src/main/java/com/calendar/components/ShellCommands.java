package com.calendar.components;

import com.calendar.services.ApiClientService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Scanner;

@ShellComponent
public class ShellCommands {
    private final ApiClientService apiClientService;
    private final Scanner scanner;


    public ShellCommands(ApiClientService apiClientService) {
        this.apiClientService = apiClientService;
        this.scanner = new Scanner(System.in);
    }

    @ShellMethod("create api client")
    public String createApiClient(){
        try {
            String login = prompt("input login: ");
            String password = prompt("input password: ");
            System.out.println(apiClientService.createApiClient(login, password));

            return "API client created!";
        }catch (Exception e){
            return e.toString();
        }
    }

    private String prompt(String message){
        System.out.print(message);
        return scanner.nextLine();
    }
}
