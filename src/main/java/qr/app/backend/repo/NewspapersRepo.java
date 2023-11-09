package qr.app.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import qr.app.backend.model.Newspapers;

public interface NewspapersRepo extends JpaRepository<Newspapers, Long> {
    Newspapers findNewspapersByLink(String link);
    Newspapers findNewspapersById(int id);

}
