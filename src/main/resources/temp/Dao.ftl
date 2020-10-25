package ${package}.dao;

import ${package}.entity.${model};
import java.util.List;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by hbm Generator<27683139@qq.com> on ${now?date}.
*/
@org.apache.ibatis.annotations.Mapper
public interface ${model}Dao extends Mapper<${model}>{

   int insert(${model} ${model?uncap_first});

    int delete(${model} ${model?uncap_first});

    int deleteByIds(@Param("ids") String[] ids);

    int update(${model} ${model?uncap_first});

    ${model} getById(@Param("id") String id);

    List<${model}> list(${model} ${model?uncap_first});
}