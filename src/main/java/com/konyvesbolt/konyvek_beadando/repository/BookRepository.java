package com.konyvesbolt.konyvek_beadando.repository;
import com.konyvesbolt.konyvek_beadando.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByTitlesOrId(String titles, Long id);
    void deleteByTitles(String titles);
    List<BookEntity> findByGenre(String genre);
}
