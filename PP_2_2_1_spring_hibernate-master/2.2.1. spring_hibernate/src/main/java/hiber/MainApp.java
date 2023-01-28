package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);
        Car car1 = new Car("Moskvich", 2125);
        carService.add(car1);
        Car car2 = new Car("Mitsubishi Padjero", 2);
        carService.add(car2);
        Car car3 = new Car("WolkswagenPasssat", 1);
        carService.add(car3);
        Car car4 = new Car("LandRoverDiscavery", 3);
        carService.add(car4);
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setUserCar(car1);
        userService.add(user1);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setUserCar(car2);
        userService.add(user2);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setUserCar(car3);
        userService.add(user3);
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setUserCar(car4);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("userCar = " + user.getUserCar());
            System.out.println();
        }
        System.out.println(carService.showOwner(car4.getModel(), car4.getSeries()));
        context.close();
    }
}
