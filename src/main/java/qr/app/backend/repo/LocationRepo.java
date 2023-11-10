package qr.app.backend.repo;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import qr.app.backend.model.Location;
import java.util.List;
public interface LocationRepo extends JpaRepository<Location, Long> {
    Location findLocationByAddress(String address);
    @NonNull
    @Override
    Page<Location> findAll(@NonNull Pageable pageable);
    List<Location> findLocationByAddressContainingOrDescriptionContaining(String address, String description);
}
