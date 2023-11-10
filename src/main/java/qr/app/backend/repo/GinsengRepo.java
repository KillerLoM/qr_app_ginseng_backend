package qr.app.backend.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import qr.app.backend.model.Ginseng;

import java.util.List;

public interface GinsengRepo extends JpaRepository<Ginseng, Long > {
    Ginseng findGinsengByCode(String code);
    @NonNull
    @Override
    Page<Ginseng>findAll(@NonNull Pageable pageable);

    List<Ginseng> findByCodeContainingOrNameContaining(String code, String name);
}
