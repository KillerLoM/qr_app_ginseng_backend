package qr.app.backend.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import qr.app.backend.model.Location;
import qr.app.backend.model.Newspapers;

import java.util.List;

public interface NewspapersRepo extends JpaRepository<Newspapers, Long> {
    Newspapers findNewspapersByLink(String link);
    Newspapers findNewspapersById(int id);
    @Override
    @NonNull
    Page<Newspapers>findAll(@NonNull Pageable pageable);
    List<Newspapers> findNewspapersByDescriptionContainingOrNameContaining(String description, String name);
}
