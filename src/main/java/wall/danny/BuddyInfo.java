package wall.danny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "addressbook_id", nullable = false)
    @JsonIgnore
    private AddressBook addressBook;

    public BuddyInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public BuddyInfo() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " - " + number;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
