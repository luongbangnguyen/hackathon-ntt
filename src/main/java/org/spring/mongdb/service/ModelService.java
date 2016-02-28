package org.spring.mongdb.service;

/**
 * Created by root on 2/25/16.
 */

import org.spring.mongdb.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface  ModelService <T extends Model> {
    /**
     * save model
     * @param t
     * @return
     */
    T save(T t);

    /**
     * update model
     * @param t
     * @return
     */
    T update (T t);

    /**
     * delete id
     * @param id
     * @return
     */
    boolean delete (String id);

    /**
     * find model by Id
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * find all
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);
}
