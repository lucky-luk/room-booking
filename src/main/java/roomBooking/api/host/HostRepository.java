package roomBooking.api.host;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {

    @Query(value = "select h from Host h where h.companyName = :companyName")
    Host getHostByCompanyName(String companyName);

    @Modifying
    @Query(value = "delete from Host h where h.id = :id")
    int deleteHostById(Long id);
}
