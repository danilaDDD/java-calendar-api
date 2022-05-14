package com.calendar.commands;


import com.calendar.models.User;
import com.calendar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;

import java.util.Scanner;

//@ShellComponent
public class StartApp {
    Scanner scanner = new Scanner(System.in);
    UserService userService;

    @Autowired
    public void StartApp(UserService service){
        this.userService = service;
    }

//    @ShellMethod("Add need data before start app")
    public void start(){
        System.out.println("Creating admin user");
        User user = new User();

        String login = read("login", true);
        user.setLogin(login);

        String password = read("password", true);
        user.setPassword(password);

        try {
            String strStatus = read("status", false);
            if (strStatus.length() > 0)
                user.setStatus(Boolean.parseBoolean(strStatus));
        }catch (Exception e){
            System.out.println(e.toString());
        }

        try {
            String strSex = read("sex", false);
            if (strSex.length() > 0)
                user.setSex(User.Sex.valueOf(strSex));
        }catch (Exception e) {
            System.out.println(e.toString());
        }

        user.setRole(User.Role.ADMIN);

        try {
            userService.createUser(user);
            System.out.println("saved admin user");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String read(String fieldKey, boolean isRequired){
        System.out.print(fieldKey + ": ");
        String value = scanner.nextLine();

        if(!isRequired)
            return value;

        if(value.length() > 0)
            return value;
        else
            return read(fieldKey, isRequired);


    }
}
