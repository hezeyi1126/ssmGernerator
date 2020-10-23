<?xml version="1.0" encoding="UTF-8"?>
<query queryId="${model?uncap_first}Query">
	<sql>
		<![CDATA[
		SELECT  
			<#list fields as f>
		      ${f.field_} as ${f.field}<#if f_has_next>,
		      						   <#else>
		  							   </#if>
		    </#list>
		
		FROM ${table}
		WHERE 1=1
	<#list fields as f>
		<#if f.issearch=="YES">
		 [@${f.field}; AND ${f.field_} = '{#${f.field}}' ]
		 <#else>
	     </#if>
	</#list>
		]]>
		
	</sql>
	
	<table>
		<head>
				<td type="checkbox" fixed="left"  totalRowText='合计' />
			<#list fields as f>
		     	<td field="${f.field}" column="${f.field_}" title="${f.comment}" <#if f.frontType=="hidden">hide="true"</#if>></td>
		    </#list>
		    	<td  title="操作" templet="#editpane" ></td>
		</head>
	</table>
	
	
</query>