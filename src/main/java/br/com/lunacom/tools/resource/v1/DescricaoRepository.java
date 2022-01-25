package br.com.lunacom.tools.resource.v1;

import br.com.lunacom.tools.domain.entity.DescricaoEntity;
import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DescricaoRepository extends JpaRepository<DescricaoEntity, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE DescricaoEntity SET status = :novoStatus WHERE id = :id")
    public void atualizarStatus(@Param("novoStatus") StatusEnum novoStatus,
                                @Param("id") Long id);
}
