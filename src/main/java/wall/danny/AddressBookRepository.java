package wall.danny;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

}
