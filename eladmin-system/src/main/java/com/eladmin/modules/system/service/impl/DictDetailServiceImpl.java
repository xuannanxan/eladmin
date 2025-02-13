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
package com.eladmin.modules.system.service.impl;

import com.eladmin.exception.BadRequestException;
import com.eladmin.modules.system.repository.DictDetailRepository;
import com.eladmin.modules.system.repository.DictRepository;
import com.eladmin.modules.system.service.mapstruct.DictDetailMapper;
import com.eladmin.utils.*;
import lombok.RequiredArgsConstructor;
import com.eladmin.modules.system.domain.Dict;
import com.eladmin.modules.system.domain.DictDetail;
import com.eladmin.modules.system.service.dto.DictDetailQueryCriteria;
import com.eladmin.modules.system.service.DictDetailService;
import com.eladmin.modules.system.service.dto.DictDetailDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dict")
public class DictDetailServiceImpl implements DictDetailService {


    private final DictRepository dictRepository;
    private final DictDetailRepository dictDetailRepository;
    private final DictDetailMapper dictDetailMapper;
    private final RedisUtils redisUtils;

    @Override
    public Map<String,Object> queryAll(DictDetailQueryCriteria criteria, Pageable pageable) {
        Page<DictDetail> page = dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(dictDetailMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(DictDetail resources) {
        checkResources(resources);
        dictDetailRepository.save(resources);
        // 清理缓存
        delCaches(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DictDetail resources) {
        DictDetail dictDetail = dictDetailRepository.findById(resources.getId()).orElseGet(DictDetail::new);
        ValidationUtil.isNull( dictDetail.getId(),"DictDetail","id",resources.getId());
        resources.setId(dictDetail.getId());
        checkResources(resources);
        dictDetailRepository.save(resources);
        // 清理缓存
        delCaches(resources);
    }

    @Override
    @Cacheable(key = "'name:' + #p0")
    public List<DictDetailDto> getByDictName(String name) {
        return dictDetailMapper.toDto(dictDetailRepository.findByDictName(name));
    }

    @Override
    public Map<String,Object> getByDictName(String dictName, Pageable pageable) {
        Page<DictDetail> page = dictDetailRepository.findByDictName(dictName,pageable);
        return PageUtil.toPage(page.map(dictDetailMapper::toDto));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        DictDetail dictDetail = dictDetailRepository.findById(id).orElseGet(DictDetail::new);
        if(dictDetail.getCreateBy().equals("System")){
            throw new BadRequestException( "不能删除系统字典");
        }
        // 清理缓存
        delCaches(dictDetail);
        dictDetailRepository.deleteById(id);
    }


    public void delCaches(DictDetail dictDetail){
        Dict dict = dictRepository.findById(dictDetail.getDictId()).orElseGet(Dict::new);
        redisUtils.del(CacheKey.DICT_NAME + dict.getName());
    }

    public void checkResources(DictDetail resources){
        DictDetailQueryCriteria criteria = new DictDetailQueryCriteria();
        criteria.setDictId(resources.getDictId());
        criteria.setValue(resources.getValue());
        //如果是修改或删除
        if(resources.getId() != null) {
            if(resources.getCreateBy().equals("System")){
                throw new BadRequestException( "不能修改或删除系统字典");
            }
            if (resources.getId().length() > 0) {
                criteria.setIsEdit(resources.getId());
            }
        }
        List<DictDetail> dictDetails= dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder));
        if(dictDetails.size() > 0){
            throw new BadRequestException( "字典的值不能重复");
        }
    }
}