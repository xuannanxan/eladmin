/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.eladmin.modules.system.repository;

import com.eladmin.modules.system.domain.Dict;
import com.eladmin.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
public interface DictRepository extends JpaRepository<Dict, String>, JpaSpecificationExecutor<Dict> {

    /**
     * 删除
     * @param ids /
     */
    void deleteByIdIn(Set<String> ids);

    /**
     * 查询
     * @param ids /
     * @return /
     */
    List<Dict> findByIdIn(Set<String> ids);

    /**
     * 根据字典名称查询
     * @param name /
     * @return /
     */

//    @Query(value = "SELECT d.* FROM `sys_dict` as d left join `sys_dict_detail` as dd on dd.dict_id = d.id"+
//            " where  d.name = ?1 " ,nativeQuery = true)
    @Query(value = "select d.* from sys_dict d  " +
        "where d.name = ?1", nativeQuery = true)
    List<Dict> findByName(String name);
}

