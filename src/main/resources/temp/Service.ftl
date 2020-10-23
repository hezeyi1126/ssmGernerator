package ${package}.service;

import ${package}.entity.${model};
import java.util.List;

/**
* Created by hbm Generator<27683139@qq.com> on ${now?date}.
*/
public interface ${model}Service {

    int insert(${model} ${model?uncap_first});

    int delete(${model} ${model?uncap_first});

    int deleteByIds(String[] ids);

    int update(${model} ${model?uncap_first});

    ${model} getById(String id);

    List<${model}> list(${model} ${model?uncap_first});
    
}