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

import com.eladmin.modules.system.domain.DictDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
public interface DictDetailRepository extends JpaRepository<DictDetail, String>, JpaSpecificationExecutor<DictDetail> {

    /**
     * 根据字典名称查询
     * @param name /
     * @return /
     */
    @Query(value = "select new DictDetail (dd.id,dd.dictId,dd.label,dd.value,dd.dictSort,d)  from DictDetail dd  join Dict d on d.id = dd.dictId" +
            " where  d.name = ?1 ")
    List<DictDetail> findByDictName(String name);

    /**
     * 根据字典名称查询-分页
     * @param name /
     * @return /
     */
    @Query(value = "select new DictDetail (dd.id,dd.dictId,dd.label,dd.value,dd.dictSort,d)  from DictDetail dd  join Dict d on d.id = dd.dictId" +
            " where  d.name = ?1 ")
    Page<DictDetail> findByDictNameWithPage(String name, Pageable pageable);

}
