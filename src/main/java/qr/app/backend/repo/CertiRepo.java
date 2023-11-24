package qr.app.backend.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import qr.app.backend.model.Certificate;
import java.util.List;

public interface CertiRepo extends JpaRepository<Certificate, Integer> {
    Certificate findCertificateById(Integer id);
    @NonNull
    @Override
    Page<Certificate>findAll(@NonNull Pageable pageable);
    List<Certificate> findByNamecertiContainingOrDescriptionContaining(String name, String description);
}
