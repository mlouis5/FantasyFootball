/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.fantasyfootball.app.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mac
 */
@Transactional(readOnly = true)
public abstract class AbstractRepository<T> {
    @PersistenceContext(unitName = "com.design.perpetual_FantasyFootball_jar_1.0PU")
    protected EntityManager em;
    private final Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional(readOnly = false)
    public void create(T entity) {
        if (Objects.nonNull(entity)) {
            em.persist(entity);
        }
    }

    @Transactional(readOnly = false)
    public void edit(T entity) {
        em.merge(entity);
    }

    @Transactional(readOnly = false)
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    public T find(Object id) {
        T entity = null;
        if (Objects.nonNull(id)) {
            entity = em.find(entityClass, id);
        }
        return entity;
    }

    protected T findLast() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = em.createQuery(cq);

        List<T> option = Optional.ofNullable(q.setMaxResults(1).getResultList()).orElse(new ArrayList());

        T entity = null;
        if (Objects.nonNull(option.get(0))) {
            entity = option.get(0);
        }
        return entity;
    }
    
    public List<T> getCriteriaList(CriteriaQuery<T> cq){
        TypedQuery<T> query = em.createQuery(cq);
        return query.getResultList();
    }
    
    public T getsingleCriteria(CriteriaQuery<T> cq){
        TypedQuery<T> query = em.createQuery(cq);
        return query.getSingleResult();
    }
    
    public CriteriaBuilder getCriteriaBuilder(){
        return em.getCriteriaBuilder();
    }
    
    public CriteriaQuery<T> getCriteriaQuery(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        return cb.createQuery(entityClass);
    }
    
    public Metamodel getMetaModel(){
        return em.getMetamodel();
    }
    
    public EntityType<T> getEntityType(){
        return getMetaModel().entity(entityClass);
    }
    
    public Root<T> getRoot(){
        return getCriteriaQuery().from(entityClass);
    }

    public List<T> findListByNamedQuery(String name, Map<String, ? extends Object> parameters) {
        NamedQueries nq = entityClass.getAnnotation(NamedQueries.class);
        NamedQuery[] nqs = nq.value();
        String queryName = null;

        for (NamedQuery qn : nqs) {
            String qname = qn.name();
            if (qname.contains(name)) {
                queryName = qname;
                break;
            }
        }
        List<T> results = new ArrayList(1);
        if (Objects.nonNull(queryName)) {
            TypedQuery<T> query
                    = em.createNamedQuery(queryName, entityClass);
            if (Objects.nonNull(parameters) && !parameters.isEmpty()) {
                parameters.entrySet().stream().forEach((entry) -> {
                    query.setParameter((String) entry.getKey(), entry.getValue());
                });
            }
            results = query.getResultList();
        }
        return results;
    }
    
    public T findItemByNamedQuery(String name, Map<String, ? extends Object> parameters) {
        NamedQueries nq = entityClass.getAnnotation(NamedQueries.class);
        NamedQuery[] nqs = nq.value();
        String queryName = null;

        for (NamedQuery qn : nqs) {
            String qname = qn.name();
            if (qname.contains(name)) {
                queryName = qname;
                break;
            }
        }
        T result = null;
        if (Objects.nonNull(queryName)) {
            TypedQuery<T> query
                    = em.createNamedQuery(queryName, entityClass);
            if (Objects.nonNull(parameters) && !parameters.isEmpty()) {
                parameters.entrySet().stream().forEach((entry) -> {
                    query.setParameter((String) entry.getKey(), entry.getValue());
                });
            }
            result = query.getSingleResult();
        }
        return result;
    }

    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
}

