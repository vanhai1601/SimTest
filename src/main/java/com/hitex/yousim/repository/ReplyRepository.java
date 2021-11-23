package com.hitex.yousim.repository;

import com.hitex.yousim.model.Reply;
import com.hitex.yousim.model.view.ViewReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    @Query(value = "select vr from ViewReply vr where vr.codeLanguage =?1 and vr.status = 1 order by vr.priority")
    List<ViewReply> getListReply(String codeLanguage, Pageable pageable);

    @Query(value = "select count(vr) from ViewReply vr where vr.codeLanguage =?1 and vr.status = 1")
    int countListReply(String codeLanguage);

}
