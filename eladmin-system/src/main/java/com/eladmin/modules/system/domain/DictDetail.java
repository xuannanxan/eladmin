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
package com.eladmin.modules.system.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.eladmin.base.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Entity
@Getter
@Setter
@Table(name="sys_dict_detail",indexes = {@Index(columnList = "dict_id")})
public class DictDetail extends BaseEntity implements Serializable {


    @Column(name = "dict_id")
    @ApiModelProperty(value = "字典", hidden = true)
    private String dictId;

    @ApiModelProperty(value = "字典标签")
    private String label;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "排序")
    private Integer dictSort = 999;

    @Transient
    private Dict dict;

    public DictDetail(String id,String dictId,String label,String value,Integer dictSort,Dict dict) {
        this.setDictId(id);
        this.dictId = dictId;
        this.label = label;
        this.value = value;
        this.dictSort = dictSort;
        this.dict = dict;
    }


    public DictDetail() {

    }
}