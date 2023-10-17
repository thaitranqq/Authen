package com.admin.admin.repository;

import com.admin.admin.model.Product;
import com.admin.admin.model.Role;
import com.admin.admin.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCusRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findByOrderByNameAsc(){
        StringBuilder sql = new StringBuilder()
                .append("select * from product_item p\n" +
                        "order by p.name asc");
        NativeQuery<Product> query = ((Session)entityManager.getDelegate()).createNativeQuery(sql.toString());
        query.addScalar("id", StandardBasicTypes.LONG);
        query.addScalar("category_id", StandardBasicTypes.LONG);
        query.addScalar("name", StandardBasicTypes.STRING);
        query.addScalar("description", StandardBasicTypes.STRING);
        query.addScalar("amount", StandardBasicTypes.INTEGER);
        query.addScalar("price", StandardBasicTypes.FLOAT);
        query.addScalar("product_image", StandardBasicTypes.STRING);
        query.addScalar("is_customer", StandardBasicTypes.BOOLEAN);
        query.addScalar("is_post", StandardBasicTypes.BOOLEAN);
        query.setResultTransformer(Transformers.aliasToBean(Product.class));
        return query.list();
    }

    public List<Product> findByOrderByNameDesc(){
        StringBuilder sql = new StringBuilder()
                .append("select * from product_item p\n" +
                        "order by p.name desc");
        NativeQuery<Product> query = ((Session)entityManager.getDelegate()).createNativeQuery(sql.toString());
        query.addScalar("id", StandardBasicTypes.LONG);
        query.addScalar("category_id", StandardBasicTypes.LONG);
        query.addScalar("name", StandardBasicTypes.STRING);
        query.addScalar("description", StandardBasicTypes.STRING);
        query.addScalar("amount", StandardBasicTypes.INTEGER);
        query.addScalar("price", StandardBasicTypes.FLOAT);
        query.addScalar("product_image", StandardBasicTypes.STRING);
        query.addScalar("is_customer", StandardBasicTypes.BOOLEAN);
        query.addScalar("is_post", StandardBasicTypes.BOOLEAN);
        query.setResultTransformer(Transformers.aliasToBean(Product.class));
        return query.list();
    }
}
