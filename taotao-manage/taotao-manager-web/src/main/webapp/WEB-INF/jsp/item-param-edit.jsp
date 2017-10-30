<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table cellpadding="5" style="margin-left: 30px" id="itemParamAddTable" class="itemParam">
	<tr>
		<td>商品类目:</td>
		<td><a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a> 
			<input type="hidden" name="cid" style="width: 280px;"></input>
		</td>
	</tr>
	<tr class="hide addGroupTr">
		<td>规格参数:</td>
		<td>
			<ul>
				<li><a href="javascript:void(0)" class="easyui-linkbutton addGroup">添加分组</a></li>
			</ul>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<a href="javascript:void(0)" class="easyui-linkbutton submit">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton close">关闭</a>
		</td>
	</tr>
</table>
<div  class="itemParamAddTemplate" style="display: none;">
	<li class="param">
		<ul>
			<li>
				<input class="easyui-textbox" id="group" style="width: 150px;" name="group"/>&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton addParam"  title="添加参数" data-options="plain:true,iconCls:'icon-add'">
				</a>
			</li>
			<li>
				<span>|-------</span><input   id="param" style="width: 150px;" class="easyui-textbox" name="param"/>&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton delParam" title="删除" data-options="plain:true,iconCls:'icon-cancel'">
				</a>						
			</li>
		</ul>
	</li>
</div>
<script style="text/javascript">
	$(function(){
		TAOTAO.initItemCat({
			fun:function(node){
			$(".addGroupTr").hide().find(".param").remove();
				//  判断选择的目录是否已经添加过规格
/* 			  $.getJSON("/item/param/query/itemcatid/" + node.id,function(data){
				  if(data.ststus == 200 && data.data){
					  $.messager.alert("提示", "该类目已经添加，请选择其他类目。", undefined, function(){
						 $("#itemParamAddTable .selectItemCat").click();
					  });
					  return ;
				  }
				  $(".addGroupTr").show();
			  });  */
				
				$.ajax({
				  type: "GET",
				  url: "/item/param/query/itemcatid/" + node.id,
				  success: function(data){
					 if(data.status == 200 && data.data){
						$(".addGroupTr").show();
 						var paramData = JSON.parse(data.data.paramData);
						var a = 0;
						for(var i in paramData){
							a = a + 1;
							var pd = paramData[i];
							//在隐藏栏中回显数据
							$(".itemParamAddTemplate #group").textbox('setValue',pd.group);
							var temple = $(".itemParamAddTemplate li").eq(0).clone();
							temple.children().children().remove("li:last"); 
							temple.find(".addParam").click(function(){
								//转化jQuery对象
								var li = $(".itemParamAddTemplate li").eq(2).clone();
								li.find(".delParam").click(function(){
									$(this).parent().remove();
								});
								li.appendTo($(this).parentsUntil("ul").parent());
							});
							temple.find(".delParam").click(function(){
								$(this).parent().remove();
							});
							for(var j in pd.params){
								var ps = pd.params[j]
								//在隐藏栏中回显数据
								$(".itemParamAddTemplate #param").textbox('setValue', ps);
								var li = $(".itemParamAddTemplate li").eq(2).clone();
								li.find(".delParam").click(function(){
									$(this).parent().remove();
								});
								li.appendTo(temple.find(".addParam").parentsUntil("ul").parent());
							}
							$(".addGroupTr ul").eq(0).append(temple);
						 }
						 return ;
					  }					  
					  $.messager.alert("提示", "该类目未添加，请选择其他类目。", undefined, function(){
							 $("#itemParamAddTable .selectItemCat").click();
						});
				   },
				   error: function(){
					   alert("error");
				   }
				}); 
			}
		});
		
		$(".addGroup").click(function(){
			//转化jQuery对象
			var temple = $(".itemParamAddTemplate li").eq(0).clone();
			$(this).parent().parent().append(temple);
			temple.find(".addParam").click(function(){
				//转化jQuery对象
				var li = $(".itemParamAddTemplate li").eq(2).clone();
				li.find(".delParam").click(function(){
					$(this).parent().remove();
				});
				li.appendTo($(this).parentsUntil("ul").parent());
			});
			temple.find(".delParam").click(function(){
				$(this).parent().remove();
			});
		});
		
		$("#itemParamAddTable .close").click(function(){
			$(".panel-tool-close").click();
		});
		
		$("#itemParamAddTable .submit").click(function(){
			var params = [];
			var groups = $("#itemParamAddTable [name=group]");
			groups.each(function(i,e){
				var p = $(e).parentsUntil("ul").parent().find("[name=param]");
				var _ps = [];
				p.each(function(_i,_e){
					var _val = $(_e).siblings("input").val();
					if($.trim(_val).length>0){
						_ps.push(_val);						
					}
				});
				var _val = $(e).siblings("input").val();
				if($.trim(_val).length>0 && _ps.length > 0){
					params.push({
						"group":_val,
						"params":_ps
					});					
				}
			});
			var url = "/item/param/save/"+$("#itemParamAddTable [name=cid]").val();
			$.post(url,{"paramData":JSON.stringify(params)},function(data){
				$.messager.alert('提示','新增商品规格成功!',undefined,function(){
					$(".panel-tool-close").click();
   					$("#itemParamList").datagrid("reload");
   				});
			});
		});
	});
</script>