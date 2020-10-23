package ${package}.controller;


import ${package}.service.${model}Service;
import ${package}.entity.${model};
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/** 
* Created by hbm Generator<27683139@qq.com> on ${now?date}.
*/
@Controller
@RequestMapping("/${model?lower_case}")
public class ${model}Controller{

    @Autowired
    ${model}Service ${model?uncap_first}Service;

    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody
    public Object list${model}(){
        ${model} ${model?uncap_first}=new ${model}();
        return ${model?uncap_first}Service.list(${model?uncap_first});
    }

    @RequestMapping(value="getById.ztc",method = RequestMethod.GET)
    @ResponseBody
    public Object getById${model}(String id){
        return ${model?uncap_first}Service.getById(id);
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public Object insert${model}(@RequestBody ${model} ${model?uncap_first}){
        return ${model?uncap_first}Service.insert(${model?uncap_first});
    }


    @RequestMapping(value="",method = RequestMethod.PUT)
    @ResponseBody
    public Object update${model}(@RequestBody ${model} ${model?uncap_first}){
        return ${model?uncap_first}Service.update(${model?uncap_first});
    }


    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete${model}(@RequestBody ${model} ${model?uncap_first}){
        return ${model?uncap_first}Service.delete(${model?uncap_first});
    }

    @RequestMapping(value="/selective",method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete${model}ByIds(@RequestBody String[] ids){
        return ${model?uncap_first}Service.deleteByIds(ids);
    }
}
