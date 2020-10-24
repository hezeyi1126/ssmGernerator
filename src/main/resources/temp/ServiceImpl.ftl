package ${package}.service.impl;

import ${package}.service.${model}Service;
import ${package}.dao.${model}Dao;
import ${package}.entity.${model};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import springboot.hello.entity.base.ParamObject;
import springboot.hello.util.BeanUtil;
import java.util.List;

/**
* Created by hbm Generator<27683139@qq.com> on ${now?date}.
*/
@Service("${model?uncap_first}Service")
public class ${model}ServiceImpl implements ${model}Service {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Object delSelected(ParamObject<${model}> paramObject ) throws Exception {
    	BeanUtil.buildEntity(${model}.class, paramObject);
    	String idsArr = (String) paramObject.getValue("ids");
    	String[] ids =  idsArr.split(",");
    	${model?uncap_first}Dao.deleteByIds(ids);
    	paramObject.setMsg(  "删除成功");
    	return null;
    }

    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Object add(ParamObject<${model}> paramObject ) throws Exception {
    	BeanUtil.buildEntity(${model}.class, paramObject);
    	${model?uncap_first}Dao.insert(paramObject.getEntity());
    	paramObject.setMsg(  "添加成功");
    	return null;
    }
    
  // @CacheEvict(value = "${model?uncap_first}", key = "#p0.id")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Object del(ParamObject<${model}> paramObject ) throws Exception{
    	BeanUtil.buildEntity(${model}.class, paramObject);
    	${model?uncap_first}Dao.delete(paramObject.getEntity());
    	paramObject.setMsg(  "删除成功");
    	 return null;
    }
    
    
    //@Cacheable(value = "${model?uncap_first}", key = "#p0.id")
    public Object getBId(ParamObject<${model}> paramObject) throws Exception{
    	BeanUtil.buildEntity(${model}.class, paramObject);
        return  ${model?uncap_first}Dao.selectOne(paramObject.getEntity());
    }
    
    
   // @CachePut(value = "${model?uncap_first}", key = "#p0.id")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Object edit(ParamObject<${model}> paramObject ) throws Exception{
    	BeanUtil.buildEntity(${model}.class, paramObject);
    	${model} entity = paramObject.getEntity();
    	${model?uncap_first}Dao.updateByPrimaryKey(entity);
    	paramObject.setMsg(  "保存成功");
        return entity;
    }




    @Autowired
    private ${model}Dao ${model?uncap_first}Dao;
    
     @Override
    public ${model} getById(String id){
        return ${model?uncap_first}Dao.getById(id);
    }
    
        @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByIds(String[] ids) {
        return  ${model?uncap_first}Dao.deleteByIds(ids);
    }
    
    
/*
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(${model} ${model?uncap_first}){
        return ${model?uncap_first}Dao.insert(${model?uncap_first});
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(${model} ${model?uncap_first}){
        return ${model?uncap_first}Dao.delete(${model?uncap_first});
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int update(${model} ${model?uncap_first}){
        return ${model?uncap_first}Dao.updateByPrimaryKey(${model?uncap_first});
    }

   

    @Override
    public List<${model}> list(${model} ${model?uncap_first}){
        return ${model?uncap_first}Dao.list(${model?uncap_first});
    }*/
    
    /**
    *getter and setter
    */
    public ${model}Dao get${model}(){
    	return this.${model?uncap_first}Dao;
    }
    
    public void set${model}Dao(${model}Dao ${model?uncap_first}Dao){
      this.${model?uncap_first}Dao = ${model?uncap_first}Dao;
    }
    

}