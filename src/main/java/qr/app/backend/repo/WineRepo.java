package qr.app.backend.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import qr.app.backend.model.Wine;

import java.util.List;

public interface WineRepo extends JpaRepository<Wine, Long> {
    Wine findWinesByCodewine(String code);
    @NonNull
    @Override
    Page<Wine> findAll(@NonNull Pageable pageable);
    List<Wine> findByCodewineContainingOrNamewineContaining(String codewine, String namewine);

}
