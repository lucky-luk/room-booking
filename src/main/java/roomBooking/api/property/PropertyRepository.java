package roomBooking.api.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query(value = "select p from Property p where p.propertyName = :propertyName")
    Property getPropertyByName(String propertyName);

    @Query(value = "select p from Property p where p.address.city = :city")
    List<Property> getAllPropertiesByCity(String city);

    @Query(value = "select p from Property p where p.propertyType = :propertyType")
    List<Property> getAllPropertiesByType(PropertyType propertyType);

    @Query(value = "select p from Property p where p.host.id = :hostId")
    List<Property> getAllPropertiesByHostId(Long hostId);

    @Query(value = "select p.address.id from Property p where p.id = :propertyId")
    Long getPropertyAddressId(Long propertyId);

    @Modifying
    @Query(value = "delete from Property p where p.id = :id")
    void deletePropertyById(Long id);

    @Modifying
    @Query(value = "delete from Property p where p.propertyName = :propertyName")
    void deletePropertyByName(String propertyName);

    @Modifying
    @Query(value = "delete from Address a where a.id = :addressId")
    void deleteAddressById(Long addressId);
}
