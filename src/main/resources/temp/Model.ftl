package ${package}.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
* Created by hbm Generator<27683139@qq.com> on ${now?date}.
*/
public class ${model} implements Serializable {
    <#list fields as f>
        /**
         *${f.comment}
         */
         <#if f.key=='PRI'>
         @Id
   		 @GeneratedValue(generator = "UUID")
         private ${f.type} ${f.field}; 
         <#else>
         	 <#if f.type == 'Date'>
   		 @JsonFormat(pattern = "yyyy-MM-dd")
   			 </#if> 
         @Column(name="${f.field_}")
         private ${f.type} ${f.field};
         </#if>  
         
    </#list>

    <#-- getter -->
    <#list fields as f>
        public ${f.type} get${f.field?cap_first}() {
            return ${f.field};
        }

        public void set${f.field?cap_first}(${f.type} ${f.field}) {
            this.${f.field} = ${f.field};
        }
    </#list>
}
