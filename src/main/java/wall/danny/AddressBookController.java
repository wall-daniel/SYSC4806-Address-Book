package wall.danny;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressBookController {

    private final AddressBookRepository repository;

    public AddressBookController(AddressBookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/addressbooks/{id}")
    public AddressBook one(@PathVariable long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Address book not found"));
    }

    @GetMapping("/addressbooks")
    public List<AddressBook> all() {
        return repository.findAll();
    }

    @PostMapping("/addressbooks")
    public AddressBook postAddressBook(@RequestBody AddressBook addressBook) {
        return repository.save(addressBook);
    }

    @DeleteMapping("/addressbooks/{id}")
    public void deleteAddressBook(@PathVariable long id) {
        repository.deleteById(id);
    }
}
