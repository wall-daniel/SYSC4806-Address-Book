package wall.danny;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "buddyinfo", path = "buddyinfo")
public interface BuddyRepository extends JpaRepository<BuddyInfo, Long> {

}
