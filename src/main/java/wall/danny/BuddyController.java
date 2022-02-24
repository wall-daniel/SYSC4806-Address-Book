package wall.danny;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuddyController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyRepository buddyRepository;

    public BuddyController(AddressBookRepository repository, BuddyRepository buddyRepository) {
        this.addressBookRepository = repository;
        this.buddyRepository = buddyRepository;
    }

    @GetMapping("/addressbooks/{id}/buddies")
    public List<BuddyInfo> allBuddies(@PathVariable long id) {
        return addressBookRepository
                .findById(id)
                .orElseThrow()
                .getBuddies();
    }

    @GetMapping("/addressbooks/{addressId}/buddies/{buddyId}")
    public BuddyInfo getBuddy(@PathVariable long addressId, @PathVariable long buddyId) {
        BuddyInfo buddyInfo = buddyRepository.findById(buddyId).orElseThrow();

        // Make sure the buddy has the correct address book
        if (buddyInfo.getAddressBook().getId() != addressId) {
            throw new RuntimeException("Buddy not in the address book");
        }

        return buddyInfo;
    }

    @PostMapping("/addressbooks/{id}/buddies")
    public AddressBook addBuddy(@PathVariable long id, @RequestBody BuddyInfo buddyInfo) {
        AddressBook addressBook = addressBookRepository.findById(id).orElseThrow();
        addressBook.addBuddy(buddyInfo);
        addressBookRepository.save(addressBook);
        return addressBook;
    }

    @DeleteMapping("/addressbooks/{addressId}/buddies/{buddyId}")
    public void deleteBuddy(@PathVariable long addressId, @PathVariable long buddyId) {
        // Make sure the buddy is in the address book
        AddressBook addressBook = addressBookRepository.findById(addressId).orElseThrow();
        boolean found = false;
        for (BuddyInfo buddyInfo : addressBook.getBuddies()) {
            if (buddyInfo.getId() == buddyId) {
                addressBook.removeBuddy(buddyInfo);
                found = true;
                break;
            }
        }
        if (!found) throw new RuntimeException("Buddy not in address book");

        // Delete the buddy now that it is confirmed to be in the address book
        addressBookRepository.save(addressBook);
        buddyRepository.deleteById(buddyId);
    }
}
