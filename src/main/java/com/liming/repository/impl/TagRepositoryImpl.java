package com.liming.repository.impl;

import com.liming.repository.wrapper.TagWrapper;
import com.liming.vo.TagVo;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TagRepositoryImpl implements TagWrapper {

    @PersistenceContext
    private EntityManager em;

    private Session getSession() {
        return em.unwrap(Session.class);
    }

    @Override
    public List<TagVo> findAllDetail() {
        String sql = "select t.id,t.tag_name as tagName,count(at.tag_id) as articles from article_tag at "
                + "right join tag t on t.id = at.tag_id group by t.id";
        NativeQuery query = getSession().createSQLQuery(sql);
        query.addScalar("id");
        query.addScalar("tagName");
        query.addScalar("articles", IntegerType.INSTANCE);
        query.setResultTransformer(Transformers.aliasToBean(TagVo.class));
        return query.list();
    }

    @Override
    public TagVo getTagDetail(Integer tagId) {
        String sql = "select t.id,t.tag_name as tagName,count(at.tag_id ) as articles from article_tag at "
                + "right join tag t on t.id = at.tag_id where t.id = :tagId ";

        NativeQuery query = getSession().createSQLQuery(sql);
        query.setInteger("tagId", tagId);
        query.addScalar("id");
        query.addScalar("tagName");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(TagVo.class));
        return (TagVo) query.uniqueResult();
    }
}
