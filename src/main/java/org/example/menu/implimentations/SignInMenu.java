package org.example.menu.implimentations;

import org.example.configs.ApplicationContext;
import org.example.entitis.User;
import org.example.menu.Menu;
import org.example.services.UserManagementService;
import org.example.services.implimentations.DefaultUserManagementService;

import java.util.Scanner;

public class SignInMenu implements Menu {
    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        context=ApplicationContext.getInstance();
        userManagementService= DefaultUserManagementService.getInstance();
    }


    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);

        System.out.print("Please, enter your email: ");
        String userEmail = sc.next();

        System.out.print("Please, enter your password: ");
        String userPassword = sc.next();

        User user=userManagementService.getUserByEmail(userEmail);
        if (user != null && user.getPassword().equals(userPassword)) {
            System.out.printf("Glad to see you back %s %s", user.getFirstName(),
                    user.getLastName() + System.lineSeparator());
            context.setLoggedUser(user);
        } else {
            System.out.println("Unfortunately, such login and password doesn't exist");
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** Sign In *****");
    }
}
