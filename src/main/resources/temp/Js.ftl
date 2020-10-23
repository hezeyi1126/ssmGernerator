$(document).ready(function () {
    initTble();
});

(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

function initTble() {
    $('#myTable').bootstrapTable({
        method: 'get',
        url: '../${model?lower_case}',
        striped: true,
        cache: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        sidePagination: "client",
        search: true,
        uniqueId: "id",
        clickToSelect: true,
        columns: [{
            field: 'check',
            checkbox: true,
            formatter: function (value, row, index) {
                return false;
            }
        },
        <#list fields as f>
        {
            field: '${f.field?uncap_first}',
            title: '${(f.comment=='')?string(f.field,f.comment)}',
            align: 'center'
        },
        </#list>
        {
            field: 'edit',
            title: '修改',
            align: 'center',
            formatter: function (value, row, index) {
                var a = '<button class="btn btn-default" onclick="edit(' + row.id + ')">修改</button>';
                return a;
            }
        }],
        onDblClickCell:function(field, value, row, $element){
            edit(row.id);
        }
    });
}

function edit(id) {
    var row=$('#myTable').bootstrapTable('getRowByUniqueId', id);
    $('#myAjaxType').val("put");
    $('#myModalLabel').val("修改");
    <#list fields as f>
        $('#${f.field?uncap_first}').val(row.${f.field?uncap_first});
    </#list>
    $('#editModal').modal('show');
}

function create() {
    $('#myAjaxType').val("post");
    $('#myModalLabel').val("添加");
    <#list fields as f>
        $('#${f.field?uncap_first}').val('');
    </#list>
    $('#editModal').modal('show');
}

function save() {
    var json=$('#myForm').serializeJson();
    console.log(toJSONString(json));
    $.ajax({
        type:$('#myAjaxType').val(),
        url:"../${model?lower_case}",
        contentType:'application/json',
        data:JSON.stringify(json),
        success:function(result){
            console.log(result);
            $('#editModal').modal('hide');
            $('#myTable').bootstrapTable('refresh');
        }
    });
}


function del() {
    var rows=$('#myTable').bootstrapTable('getSelections');
 	var params= new Array();
    for(var i=0;i<rows.length;i++){
    	params.push(rows[i].id);
    	
    }
    console.log(params);
    $.ajax({
        type:'delete',
        url:"../${model?lower_case}/selective",
        contentType:'application/json',
        data:JSON.stringify(params),
        success:function(result){
            console.log(result);
            $('#myTable').bootstrapTable('refresh');
        }
    });
}

function toJSONString(jsonObj) {
    var jStr = "{";
    for(var item in jsonObj){
        jStr += "'"+item+"':'"+jsonObj[item]+"',";
    }
    return jStr.substring(0,jStr.length-1)+'}';
}