package RadancyAssignment.api.Controller;

import RadancyAssignment.api.model.User;
import RadancyAssignment.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    UserService userService;
    public UserController(){
        userService = new UserService();
    }

    @GetMapping(value = "/user")
    public User getUser(@RequestParam int id){
        return userService.getUser(id);
    }

    @GetMapping(value = "/user/all")
    public List<User> getAllUser(){
        return UserService.userList;
    }

    @PostMapping(value = "/user")
    public String setUser(@RequestBody User user){
        return userService.setUser(user);
    }

    @DeleteMapping(value = "/user")
    public String deleteUser(@RequestParam int id){
        return userService.deleteUser(id);
    }
    @PutMapping(value = "/user")
    public String updateUser(@RequestBody User user, @RequestParam int id){
        userService.updateUser(id, user);
        return "Updated ...";
    }

    @PostMapping(value = "/user/deposit")
    public String deposit(@RequestParam int id, @RequestParam Double deposit){
        if (deposit > 10000.0){
            return "You can't deposit more than 10,000$ !";
        }else if (deposit < 0.0){
            return "The input is incorrect ! ";
        }else {
            userService.deposit(id, deposit);
            return "Deposit added to the account ...";
        }
    }

    @PostMapping(value = "/user/withdraw")
    public String withdraw(@RequestParam int id, @RequestParam Double withdraw){
        return userService.withdraw(id, withdraw);
    }

}
