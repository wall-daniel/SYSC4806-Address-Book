package wall.danny;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressBook", fetch = FetchType.EAGER)
    private List<BuddyInfo> buddyInfoList;

    public AddressBook() {
        buddyInfoList = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddyInfo) {
        buddyInfoList.add(buddyInfo);
        buddyInfo.setAddressBook(this);
    }

    public int getSize() {
        return buddyInfoList.size();
    }

    public boolean isEmpty() { return buddyInfoList.isEmpty(); }

    public List<BuddyInfo> getBuddies() {
        return buddyInfoList;
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        System.out.println("Initial address book:");
        System.out.println(addressBook.toString());
        System.out.println("\n");

        // Add some buddies
        addressBook.addBuddy(new BuddyInfo("Danny", "613"));
        addressBook.addBuddy(new BuddyInfo("Todd", "614"));
        addressBook.addBuddy(new BuddyInfo("Emily", "999"));

        System.out.println("Address book with buddies:");
        System.out.println(addressBook.toString());
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Buddies:");

        buddyInfoList.forEach((buddyInfo -> sb.append(buddyInfo.toString()).append("\n")));

        return sb.toString();
    }

    public void removeBuddy(BuddyInfo buddyInfo) {
        if (buddyInfoList.remove(buddyInfo)) {
//            buddyInfo.setAddressBook(null);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
