package roomBooking.api.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "select r from Room r where r.property.id = :propertyId")
    List<Room> getAllRoomsByProperty(Long propertyId);

    @Query(value = "select r from Room r where r.sleepingPlaces = :numberOfSleepingPlaces")
    List<Room> getAllRoomsBySleepingPlaces(Integer numberOfSleepingPlaces);

    @Query(value = "select r from Room r where r.doubleBed = :value")
    List<Room> getAllRoomsWithDoubleBed(boolean value);

    @Query(value = "select r from Room r where r.pricePerNight between :minPrice and :maxPrice")
    List<Room> getAllRoomsByMinMaxPrice(BigDecimal minPrice, BigDecimal maxPrice);

    @Modifying
    @Query(value = "delete from Room r where r.id = :id")
    int deleteRoomById(Long id);

    @Modifying
    @Query(value = "delete from Room r where r.property.id = :propertyId")
    int deleteAllRoomsByPropertyId(Long propertyId);
}
