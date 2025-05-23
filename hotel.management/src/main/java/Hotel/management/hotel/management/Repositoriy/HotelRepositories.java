package Hotel.management.hotel.management.Repositoriy;

import Hotel.management.hotel.management.Entitys.Model.Hotel;
import Hotel.management.hotel.management.Entitys.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface HotelRepositories extends JpaRepository<Hotel , Long> {

    Optional<Hotel> findByName(String hotelName);



    Optional<Hotel> findByEmail(String  email);



    Optional<Hotel> findByPhone(String  phone);

    List<Hotel> findAllByUser(User user);

}
