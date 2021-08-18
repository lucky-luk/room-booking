package roomBooking.api.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select b from Booking b where b.room.id = :id")
    List<Booking> getAllBookingsByRoom(Long id);

    @Query(value = "select b from Booking b where b.client.id = :clientId")
    List<Booking> getAllBookingsByClientId(Long clientId);

    @Query(value = "select b from Booking b where b.client.email = :email")
    List<Booking> getAllBookingsByClientEmail(String email);

    @Modifying
    @Query(value = "delete from Booking b where b.id = :id")
    int deleteBookingById(Long id);

    @Modifying
    @Query(value = "delete from Booking b where b.client.id = :clientId")
    void deleteAllBookingsByClientId(Long clientId);

    @Modifying
    @Query(value = "delete from Booking b where b.property.id = :propertyId")
    void deleteAllBookingsByPropertyId(Long propertyId);

    @Modifying
    @Query(value = "delete from Booking b where b.room.id = :roomId")
    void deleteAllBookingsByRoomId(Long roomId);
}
