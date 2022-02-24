package wall.danny;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab4Application {

    private static final Logger log = LoggerFactory.getLogger(Lab4Application.class);

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(Lab4Application.class);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository repository) {
        return (args) -> {
            // This populates a default address book into the database
            AddressBook addressBook = new AddressBook();
            addressBook.setName("My Addressboook");
            // save a few customers
            addressBook.addBuddy(new BuddyInfo("Jack", "6138096755"));
            addressBook.addBuddy(new BuddyInfo("Chloe", "6138096756"));
            addressBook.addBuddy(new BuddyInfo("Kim", "6138096757"));
            addressBook.addBuddy(new BuddyInfo("David", "6138092347"));
            addressBook.addBuddy(new BuddyInfo("Michelle", "6138311083"));

            repository.save(addressBook);

            // fetch all customers
            log.info("BuddyInfo found with findAll():");
            log.info("-------------------------------");
            for (AddressBook a : repository.findAll()) {
                log.info(a.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            AddressBook a1 = repository.findById(1L).orElseThrow(() -> new RuntimeException("Bad?"));
            log.info("BuddyInfo found with findById(1L):");
            log.info("--------------------------------");
            log.info(a1.toString());
            log.info("");
        };
    }
}
