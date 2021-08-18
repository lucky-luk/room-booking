package roomBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:dbconfig.properties")
@SpringBootApplication
public class RoomBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomBookingApplication.class, args);
    }

}
