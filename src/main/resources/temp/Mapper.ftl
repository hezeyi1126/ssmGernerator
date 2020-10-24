<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<!--
* Created by hbm Generator<27683139@qq.com> on ${now?date}.
-->
<mapper namespace="${package}.dao.${model}Dao">
    <resultMap id="${model?lower_case}" type="${package}.entity.${model}">
    <#list fields as f>
        <#if f.key=='PRI'>
            <id property="${f.field}" column="${f.field_}"/>
        <#else>
            <result property="${f.field}" column="${f.field_}"/>
        </#if>
    </#list>
    </resultMap>

    <sql id="columns">
    <#list fields as f>
        <#if f_index==0>
            `${f.field_}` ,<#rt/>
        <#elseif f_has_next>
            <#t/>`${f.field_}` ,<#rt/>
        <#else >
            <#lt/>`${f.field_}`
        </#if>
    </#list>
    </sql>
    
      <insert id="insert">
    	<selectKey keyProperty="${pkField}" resultType="string" order="BEFORE">    
	            select uuid() as id from dual  
	    </selectKey> 
        insert into `${table}`
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list fields as f>
            <if test="${f.field} != null">
                `${f.field_}`,
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list fields as f>
            <if test="${f.field} != null">
            ${r'#{'}${f.field}${r'}'} ,
            </if>
        </#list>
        </trim>
    </insert>



    <delete id="deleteByIds">
        delete from `${table}`
        where `${pk}` in
        <foreach collection="ids" open="(" item="id" separator="," close=")">
        ${r'#{'}id${r'}'}
        </foreach>
    </delete>

    

    <select id="getById" resultMap="${model?lower_case}">
        select
        <include refid="columns" />
        from `${table}`
        where `${pk}` =  ${r'#{id}'}
    </select>

    
</mapper>