package RadancyAssignment.service;

import RadancyAssignment.api.model.User;
import RadancyAssignment.api.repo.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static List<User> userList;

    public UserService(){
        userList = new ArrayList<>();
        userList.add(new User(1, "John", 1000.0));
        userList.add(new User(2, "Aidin", 1550.0));
    }

    public User getUser(int id){
        for(User user: userList){
            if(id == user.getId()){
                return user;
            }
        }
        return null;
    }

    public String setUser(User user){
        if (user.getBalance() < 100) {
            return "The balance can't be less than 100$!";
        }else {
            int lastUserId = userList.get(userList.size()-1).getId();
            userList.add(new User(lastUserId+1, user.getName(), user.getBalance()));
            return "User added ...";
        }
    }

    public String deleteUser(int id){
        for (User user: userList){
           if (id == user.getId()){
               userList.remove(user);
               return "User deleted ...";
           }
        }
        return null;
    }

    public void updateUser(int id, User currentUser){
        for (User user: userList){
            if(id == user.getId()){
                user.setName(currentUser.getName());
                user.setBalance(currentUser.getBalance());
            }
        }
    }

    public void deposit(int id, Double deposit){
        for (User user: userList){
            if( id == user.getId()){
                user.setBalance(user.getBalance() + deposit);
            }
        }
    }

    public String withdraw(int id, Double withdraw){
        for (User user: userList){
            if( id == user.getId()){
                if (withdraw > (user.getBalance()*0.9)){
                    return "Withdraw must be less than 90% of the balance !";
                }else if (user.getBalance() - withdraw < 100){
                    return "User must have at least 100$ balance !";
                }else {
                    user.setBalance(user.getBalance() - withdraw);
                    return "User current balance : " + user.getBalance().toString();
                }
            }
        }
        return null;
    }
}
