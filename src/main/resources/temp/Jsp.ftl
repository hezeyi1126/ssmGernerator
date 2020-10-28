<!DOCTYPE html>
<html class="x-admin-sm">

<head>
<meta charset="UTF-8">
<title>用户列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="stylesheet" href="/css/font.css">
<link rel="stylesheet" href="/css/xadmin.css">

<script src="/js/axios.min.js" charset="utf-8"></script>
<script src="/js/jquery.min.js" charset="utf-8"></script>
<script src="/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/tools.js"></script>
</head>
<body>

	<div class="layui-fluid">
		<!-- 表格显示面板  👇-->
		<div class="layui-row layui-col-space15" id="dataPane">
			<div class="layui-col-md12">
				<div class="layui-card">
				   <!-- 查询面板 👇-->
				    <form class="layui-form" action="">
						<div class="layui-card-body " id="searchPane">
							<#list fields as f>
								<#if f.issearch=="YES">
									<#if f.frontType=="input">
										 <div class="layui-input-inline layui-show-xs-block">
											<input type="text" name="${f.field}" placeholder="${f.comment}"
												autocomplete="off" class="layui-input">
									   	  </div>
									<#elseif f.frontType=="select">
										<div class="layui-input-inline layui-show-xs-block">
											<select name="${f.field}">
											</select>
										</div>
									<#elseif f.frontType=="date">
										<div class="layui-input-inline layui-show-xs-block">
											<label class="layui-form-label">${f.comment}</label>
											<div class="layui-input-inline">
												<input type="text" class="layui-input" 
												inputtype="date" name="${f.field}" placeholder="yyyy-MM-dd">
											</div>
										</div> 
									</#if>
									
								 <#else>
								 </#if>
							</#list>
								
								<div class="layui-input-inline lafite_search layui-show-xs-block">
									<button type="button" class="layui-btn  " onclick="search()" >
											
											查询</button>
								</div>
						</div>
					</form>
					   <!-- 查询面板 👆-->

					<div class="layui-card-body">

						<table class="layui-table" id="test" lay-filter="test"></table>
						<script type="text/html" id="toolbarDemo">
                                <div class="layui-btn-container">
                                     <button class="layui-btn lafite_main_color " lay-event="add">新增</button>
                                   <button class="layui-btn lafite_main_color" lay-event="delSelected">删除选中</button>
                                   
                                  </div>
	                    </script>

						<script type="text/html" id="editpane">
									<i class="layui-icon layui-icon-edit" onclick="edit('{{d.${pkField}}}')"  style="font-size: 18px; color: #1E9FFF;"></i>  
									<i class="layui-icon layui-icon-delete" onclick="del('{{d.${pkField}}}')" style="font-size: 18px; color: #1E9FFF;"></i>  
						</script>

					</div>
					<div id="lafite_pages"></div> 
				</div>
			</div>
		</div>
		<!-- 表格显示面板  👆-->


		<!-- 编辑面板  👇-->
		<div class="layui-row layui-col-space10" id="editPane"
			style="display: none">
			<form class="layui-form" id="editform"  >
				<#list fields as f>
					<#if f.frontType=="hidden">
						<input type="hidden" name="${f.field}"  />
					<#elseif f.frontType=="input">
						<div class="layui-form-item">
							<label for="L_email" class="layui-form-label"> 
								<#if f.isnull=="NO">
										<span class="x-red">*</span>
									</#if>
							 ${f.comment}
							</label>
							<div class="layui-input-block lafite_width_60">
								<input type="text" id="${f.field}" name="${f.field}" 
									<#if f.isnull=="NO">
										lay-verify="required"
									</#if>
									autocomplete="off" class="layui-input">
							</div>
						</div>
					<#elseif f.frontType=="date">
						<div class="layui-form-item">
							<label class="layui-form-label">
								<#if f.isnull=="NO">
										<span class="x-red">*</span>
									</#if>
							${f.comment}</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" 
								<#if f.isnull=="NO">
										lay-verify="required"
									</#if>
								inputtype="date" name="${f.field}" placeholder="yyyy-MM-dd">
							</div>
						</div> 
					<#elseif f.frontType=="select">
						<div class="layui-form-item">
									<label class="layui-form-label">
									<#if f.isnull=="NO">
											<span class="x-red">*</span>
										</#if>
								${f.comment}</label>
								<div class="layui-input-block lafite_width_60">
									<select name="${f.field}" 
									<#if f.isnull=="NO">
										lay-verify="required"
									</#if>
									>
									</select>
								</div> 
							</div> 
					<#elseif f.frontType=="textarea">
						<div class="layui-form-item">
							<label for="L_repass" class="layui-form-label">
								<#if f.isnull=="NO">
										<span class="x-red">*</span>
									</#if>
	                            ${f.comment}
	                        </label>
	                        <div class="layui-input-block lafite_width_60">
	                            <textarea name="${f.field}" placeholder="请输入内容" 
	                            <#if f.isnull=="NO">
											lay-verify="required"
										</#if>
	                            class="layui-textarea lafite_textarea lafite_min_height_80px"></textarea>    
	                        </div>
						</div> 
					</#if>
				</#list>
				


				<div class="layui-form-item">
					<div class="layui-input-block">
						<div class="lafite-layui-btn lafite_float_left lafite_height_35px"
							onclick="back()">取消</div>
						<button lay-submit lay-filter="subform"
							class="lafite-layui-btn2 lafite_margin_left_10 lafite_height_35px">保存</button>
					</div>
				</div>

			</form>
		</div>
		<!-- 编辑面板  👆-->


	</div>





</body>
<script>
	var loadingtype = 3;
	var service = "${model?uncap_first}Service"
	var queryid = "${model?uncap_first}Query"
	var cmd = "add";//默认为添加 可以为edit
	$(document).ready(function() {

	});

	layui.use([ 'form', 'table', 'laypage','laydate' ], function() {
		var form = layui.form;
		var laydate = layui.laydate;
		var laypage = layui.laypage;
		var table = layui.table;
		var tableIns;
		var params = new Object();//查询参数
		
		//初始化
		 //同时绑定多个
		  lay('[inputtype=date]').each(function(){
		    laydate.render({
		      elem: this
		      ,trigger: 'click'
		    });
		  });
		
		//监听查询
		window.search = function(){
			//获取所有条件
			var inputs = $("#searchPane").find("input,select");
			//重新初始化table 添加查询条件
			 params.queryId = queryid;
			  inputs.each(function(index){
				 if($(this).attr("name") == "")return;
				 params[ $(this).attr("name")] = $(this).val();
			 }); 
			 console.log(params);
			initTable(params);
		}
		
		//填充表单
		 window.fillForm =function (obj){
		 var eform = $("#editform");
			for(var name in obj){
			//$("input")
				eform.find("[name='"+ name +"']").val(obj[name]);
			}
			form.render();
		}
		
		//监听提交
		form.on('submit(subform)', function(data) {
			 var loadindex = layer.load(loadingtype);
			pro.callServer(service, cmd, data.field, function(res) {
				layer.close(loadindex);
				layer.msg(res.msg);
				if (res.state == "1") {
					search();
					back();
				} else {
					
				}
			});
			return false;
		});
		
		 //初始化table↓
		initTable();
		//初始化table↑
		
		window.init = function(){
			//查询所有带code的select
			var codeSelectArr = $('select[code]');
			var arrstr = "";
			codeSelectArr.each(function(){
				arrstr +=$(this).attr('code') + ",";
		    });
			if(arrstr != ''){
				arrstr = arrstr.substring(0,arrstr.length - 1);
				 pro.callServer("sysCodeService", "getAllCode",{code : arrstr} , function(res) {
						if (res.state == "1") {
							var data = res.data;
							codeSelectArr.each(function(){
								for(var i in data){
									if($(this).attr('code') == i){
										for(var j in data[i]){
											$(this).append('<option value="'+ data[i][j].codeVal +'">'+ data[i][j].code +'</option>');
										}
										
									}
								}
						    });
							form.render();
						} else {
							
						}
				}); 
			}
		}
		
		function initTable(params){
			//console.log(params);
			 if(params == undefined  || params == null){
				 params = new Object();
				 params.queryId = queryid;
			 }
			 var loadindex = layer.load(loadingtype);
			pro.callServer("queryService", "queryById", params, function(res) {
			
				layer.close(loadindex);
				init();
				//layer.msg(res.msg);
				if (res.state == "1") {
					//console.log(res);
					loadTable(res);
					var counts = 0;
				 	// 分页样式
					laypage.render({
						elem : 'lafite_pages',
						count : res.data.count,
						layout: ['count', 'prev', 'page', 'next', 'limit'],
						theme : '#D91715',
						jump : function(obj) {
							counts++;
							//模拟渲染
							console.log(obj);
							//第一次生成page不调用
							if(counts != 1){
								params.page = obj.curr;
								params.size = obj.limit;
								 var loadindex = layer.load(loadingtype);
								pro.callServer("queryService", "queryById", params,function(res){
									//console.log(res)
									layer.close(loadindex);
									//layer.msg(res.msg);
									if (res.state == "1") {
										loadTable(res);
									}
								});
							}
							
						}
					}); 
					// 分页样式

				} else {
					//初始化失败
				}
				
			}); 
		 }
		
		//渲染table
		function loadTable(res){
			table.render({
				elem : '#test'
				,
				toolbar : '#toolbarDemo',
				title : '用户数据表',
				totalRow : true,
				width : $('body').width() - $('body').width()*5/100,
				limit: res.data.length,
				cols : [ res.data.header],
				data : res.data.data
			});
		}

		//工具栏事件
		table.on('toolbar(test)', function(obj) {
			var checkStatus = table.checkStatus(obj.config.id);
			switch (obj.event) {
			case 'add':
				add();
				break;
			case 'delSelected':
				var data = checkStatus.data;
				if(data.length == 0){
					layer.msg('请选中需要删除的条目');
					break;
				}
				delSelected(data);
				break;
			case 'isAll':
				layer.msg(checkStatus.isAll ? '全选' : '未全选')
				break;
			}
			;
		});
	});
/**-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	/*添加用户*/
	function add() {
		clearForm();
		goedit() ;
		cmd = "add";
	}

	function goedit() {
		cmd = "edit";
		$("#dataPane").hide();
		$("#editPane").fadeIn(200);
	}

	function back() {
		$("#dataPane").fadeIn(200);
		$("#editPane").hide();
	}
	
	/**
	清空form内容
	*/
	function clearForm(){
		$("#editform input,select,textarea").val("");
	}
	

	/*编辑*/
	function edit( id) {
		 var loadindex = layer.load(loadingtype);
		pro.callServer(service, "getBId",{${pkField} : id},function(res){
			layer.close(loadindex);
			
			if(res.state == '1'){
				console.log(res);
				//清除页面的值
				 clearForm();
				//绑定值
				fillForm(res.data)
				//进入编辑页面
				goedit();
			}
		});
	}
	/*用户-删除*/
	function del( id) {
		layer.confirm('是否确认删除当前项目？', {
			title : "项目删除"
		}, function(index) {
			//发异步删除数据
			 var loadindex = layer.load(loadingtype);
			pro.callServer(service, "del",{${pkField} : id},function(res){
				layer.close(loadindex);
				layer.msg(res.msg);
				if(res.state == '1'){
					//刷新页面
					back();
					search();
				}
			});
			
		});
	}
	
	/**
	删除选中条目
	*/
	function delSelected(data){
		var ids = "";
		for(var i = 0 ;i < length;i++){
			ids += data[i].${pkField} + ",";
		}
		ids = ids.substring(0 , ids.length - 1);
		layer.confirm('是否确认删除当前项目？', {
			title : "项目删除"
		}, function(index) {
			console.log(index)
			//发异步删除数据
			 var loadindex = layer.load(loadingtype);
			 pro.callServer(service, "delSelected",{ids : ids},function(res){
					layer.close(loadindex);
					layer.msg(res.msg);
					if(res.state == '1'){
						//刷新页面
						back();
						search();
					}
				});
			 
		
		});
		
	}

</script>

</html>