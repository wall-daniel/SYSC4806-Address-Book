package wall.danny;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UIController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyRepository buddyRepository;

    public UIController(AddressBookRepository addressBookRepository, BuddyRepository buddyRepository) {
        this.addressBookRepository = addressBookRepository;
        this.buddyRepository = buddyRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String getIndex(Model model){
        model.addAttribute("buddyInfo", new BuddyInfo());
        model.addAttribute("addressBook", new AddressBook());

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addresses")
    public String getAddresses(Model model) {
        model.addAttribute("addressBooks", addressBookRepository.findAll());

        return "addressbooks";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addresses")
    public String postAddresses(Model model) {
        model.addAttribute("addressBooks", addressBookRepository.findAll());

        return "addressbooks";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addresses/new")
    public String addressBookForm(Model model) {
        model.addAttribute("addressBook", new AddressBook());
        return "new_addressbook";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addresses/{addressId}/buddy/new")
    public String buddyInfoForm(@PathVariable long addressId, Model model) {
        model.addAttribute("buddyInfo", new BuddyInfo());
        model.addAttribute("addressId", addressId);
        return "new_buddyinfo";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addresses/{id}")
    public String getAddress(@PathVariable long id, Model model) {
        model.addAttribute("addressBook", addressBookRepository.findById(id).orElseThrow());

        return "addressbook";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addresses/{addressId}/buddy/{buddyId}")
    public String getAddress(@PathVariable long addressId, @PathVariable long buddyId, Model model) {
        BuddyInfo buddyInfo = buddyRepository.findById(buddyId).orElseThrow();

        // Make sure the buddy has the correct address book
        if (buddyInfo.getAddressBook().getId() != addressId) {
            throw new RuntimeException("Buddy not in the address book");
        }

        model.addAttribute("buddy", buddyInfo);

        return "buddyinfo";
    }
}
