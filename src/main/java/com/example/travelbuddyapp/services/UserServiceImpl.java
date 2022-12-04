package com.example.travelbuddyapp.services;

import com.example.travelbuddyapp.entities.User;
import com.example.travelbuddyapp.repositories.UserRepository;
import com.example.travelbuddyapp.utility.PasswordAuthentication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserServiceImpl {

    public static User registerUser(User user, UserRepository repository) {
        try {
            if (checkIfEmailExists(user.getEmail(), repository)) {
                throw new Exception("Email already exists!");
            }
            if (!user.getPassword().equals(user.getPasswordConfirm())) {
                throw new Exception("Password Mismatch!");
            }
            user.setStatus(1);
            user.setUserType(1);
            user.setCreatedOn(new Date());
            user.setLastUpdatedOn(new Date());
            user.setPassword(convertPassword(user.getPassword()));
            if (user.getDlExpiryDateString() != null) {
                user.setDlExpiryDate(formatDate(user.getDlExpiryDateString()));
            }

            User u = repository.save(user);
            u.setCreatedBy(u.getId());
            u.setLastUpdatedBy(u.getId());
            repository.save(u);
            return u;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Date formatDate(String dateStr) throws ParseException {
        System.out.println(dateStr);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);
        System.out.println(date);
        return date;
    }

    private static String convertPassword(String pw) {
        PasswordAuthentication authentication = new PasswordAuthentication();
        return authentication.hash(pw);
    }

    public static boolean checkIfEmailExists(String email, UserRepository repo) {
        List<User> users = repo.findUserByEmail(email);
        if (users.isEmpty()) {
            return false;
        }
        return true;
    }

    public  static User loginUser(User loginCred, UserRepository repository) throws Exception{
        List<User> users = repository.findUserByEmail(loginCred.getEmail());
        PasswordAuthentication auth = new PasswordAuthentication();
        if (users.isEmpty()) {
            throw new Exception("Invalid Credentials!");
        }
        User user = users.get(0);
        if (!auth.authenticate(loginCred.getPassword(), user.getPassword())) {
            throw new Exception("Invalid Credentials!");
        }
        return user;
    }

    public static User editUser(User user, UserRepository repository) {
        try {
            if (checkIfEmailExists(user.getEmail(), repository)) {
                throw new Exception("Email already exists!");
            }
            if (!user.getPassword().equals(user.getPasswordConfirm())) {
                throw new Exception("Password Mismatch!");
            }
            user.setLastUpdatedOn(new Date());
            user.setLastUpdatedBy(user.getId());
            user.setPassword(convertPassword(user.getPassword()));
            if (user.getDlExpiryDateString() != null) {
                user.setDlExpiryDate(formatDate(user.getDlExpiryDateString()));
            }
            repository.save(user);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}