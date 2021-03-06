<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/checkVal.js"></script>

<!-- 创建兼容 IE/FireFox 的 event 及 event 的 srcElement、fromElement、toElement 属性 -->

<script type="text/javascript">

    if (window.addEventListener) {
        FixPrototypeForGecko();
    }
    
    function FixPrototypeForGecko() {
        HTMLElement.prototype.__defineGetter__("runtimeStyle", element_prototype_get_runtimeStyle);
        window.constructor.prototype.__defineGetter__("event", window_prototype_get_event);
        Event.prototype.__defineGetter__("srcElement", event_prototype_get_srcElement);
        Event.prototype.__defineGetter__("fromElement", element_prototype_get_fromElement);
        Event.prototype.__defineGetter__("toElement", element_prototype_get_toElement);
    }
    
    function element_prototype_get_runtimeStyle() {
        return this.style;
    }
    function window_prototype_get_event() {
        return SearchEvent();
    }
    function event_prototype_get_srcElement() {
        return this.target;
    }
    function element_prototype_get_fromElement() {
        var node;
        if (this.type == "mouseover") node = this.relatedTarget;
        else if (this.type == "mouseout") node = this.target;
        if (!node) return;
        while (node.nodeType != 1) node = node.parentNode;
        return node;
    }
    function element_prototype_get_toElement() {
        var node;
        if (this.type == "mouseout") node = this.relatedTarget;
        else if (this.type == "mouseover") node = this.target;
        if (!node) return;
        while (node.nodeType != 1) node = node.parentNode;
        return node;
    }
    function SearchEvent() {
        if (document.all) return window.event;
        func = SearchEvent.caller;
        while (func != null) {
            var arg0 = func.arguments[0];
            if (arg0 instanceof Event) {
                return arg0;
            }
            func = func.caller;
        }
        return null;
    }
    
    var  highlightcolor='#c1ebff';
    var  clickcolor='#51b2f6';

    function changeto(){
        source=event.srcElement;
        if  (source.tagName=="TR"||source.tagName=="TABLE")
        return;
        while(source.tagName!="TD")
        source=source.parentElement;
        source=source.parentElement;
        cs  =  source.children;
        //alert(cs.length);
        if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
        for(i=0;i<cs.length;i++){
            cs[i].style.backgroundColor=highlightcolor;
        }
        }
        
        function  changeback(){
        if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
        return
        if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
        //source.style.backgroundColor=originalcolor
        for(i=0;i<cs.length;i++){
            cs[i].style.backgroundColor="";
        }
    }

    function  clickto(){
        source=event.srcElement;
        if  (source.tagName=="TR"||source.tagName=="TABLE")
        return;
        while(source.tagName!="TD")
        source=source.parentElement;
        source=source.parentElement;
        cs  =  source.children;
        //alert(cs.length);
        if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
        for(i=0;i<cs.length;i++){
            cs[i].style.backgroundColor=clickcolor;
        }
        else
        for(i=0;i<cs.length;i++){
            cs[i].style.backgroundColor="";
        }
    }

    /**
    * 分页显示.
    **/
    function pageNow(pageNow){
        var fy=$("#fenye").serialize(); //对表彰数据进行序列化
        var f=$("#fenye").attr("action");//获取表单action的属性值
        var pageSize = $("#pageSizeChoose").val();
        var pCount = parseInt("${pageView.pageCount}");
        if(pageNow < 1){
            alert(" 不 好 意 思 ， 已 经 是 第 一 页 啦  !");
            return false ;
        }else if(pCount < pageNow){
            alert(" 没 有 下 一 页 啦 !");
            return false ;
        }else{
            //window.location.href=f+"?pageNow="+pageNow+"&"+fy;
            window.location.href=f+"?pageNow="+pageNow+"&pageSize="+pageSize+"&"+fy;
        };
    }
    
    /**
    *去往分页
    */
    function GOPage(){
        var pageNow = $("#pageNowCount").val();
        var pageSize = $("#pageSizeChoose").val();
        if(pageNow!=null && pageNow!='' && pageNow > 0){
            var fy=$("#fenye").serialize(); //对表彰数据进行序列化
            var f=$("#fenye").attr("action");//获取表单action的属性值
            var pCount = parseInt("${pageView.pageCount}");
            if(pageNow < 1){
                alert(" 不 好 意 思 ， 已 经 是 第 一 页 啦  !");
                return false ;
            }else if(pCount < pageNow){
                alert(" 输 入 页 数 大 于 实 际 页 数 啦 !");
                return false ;
            }else{
                //window.location.href=f+"?pageNow="+pageNow+"&"+fy;
                window.location.href=f+"?pageNow="+pageNow+"&pageSize="+pageSize+"&"+fy;
            };
        }
     }
    
    /**
    *分页显示多少条.
    */
    function ChoosePageSize(rowCount){
        var pageSize = $("#pageSizeChoose").val();
        var pageNow = $("#pageNowCount").val(); 
        
        //共多少页
        var sumPage = rowCount / pageSize;
        sumPage = parseInt(sumPage);
        
        if(pageSize!=null && pageSize!='' && pageSize > 0){
            var fy=$("#fenye").serialize(); //对表彰数据进行序列化
            var f=$("#fenye").attr("action");//获取表单action的属性值
            //提交.
            if(parseInt(pageSize) > parseInt(rowCount) || parseInt(sumPage) < parseInt(pageNow) )
            {
                //window.location.href=f+"?pageSize="+pageSize+"&"+fy;
                window.location.href=f+"?pageSize="+pageSize+"&pageNow=1&"+fy;
            }
            else
            {
                //window.location.href=f+"?pageSize="+pageSize+"&"+fy;
                window.location.href=f+"?pageSize="+pageSize+"&pageNow="+pageNow+"&"+fy;
            }
        }
     }
    
    //<!--################# 全选取消全选-->
    function selectAllCheckBox()
    {
        var chose;
        if(document.getElementById("chose").checked){
            chose = document.getElementById("chose").checked;
        }
        var checkboxArray = document.getElementsByName("check");
        if(checkboxArray != null)
        {
            for(var i = 0; i < checkboxArray.length; i++)
            {
                checkboxArray[i].checked = chose;
            };
        };
    }
    
    //点击删除时是否有勾选
    function result(){
        var checks = document.getElementsByName("check");
        for(var i = 0; i < checks.length; i++){
            if(checks[i].checked == true){
                return false;
            };
        };
    }
    
    var pks="";
    function getPks(){
        var checks = document.getElementsByName("check");
        for(var i = 0; i < checks.length; i++){
            if(checks[i].checked == true){
                pks+=checks[i].value+",";
            };
        };
        //最后的字符串.
        pks = pks.substring(0,pks.length-1); 
    }
    
    function deleteAll(){
        if(window.confirm("此 多 项 删 除 的 操 作 很 危险,一 旦 删 除 将 会 后 果 很 严 重!")){
            var f=$("#fenye").attr("action");//获取表单action的属性值
            f=f.substring(0,f.lastIndexOf("/")+1);
            if(result() != false){
                alert(" 请 选 择 你 要 删 除 的 项 !");
            }else{
                if(window.confirm(" 你 确  定 要 全 部 删 除 吗 ！删 除 后 不 可 恢 复 !")){ 
                    getPks();
                    document.fenye.action = f+"deleteAll.html?pks="+pks;
                    document.fenye.submit();
                };
            };
        }
    }
    
    function deleteId(url,objId){
        if(window.confirm(" 你 确  定 要 删 除 吗 ！删 除 后 不 可 恢 复 !"))
        { 
            $.ajax({
               url:url,
               data:{"objId":objId},
               type : "POST",
               success:function(msg){
                   var obj = eval(msg);
                   if (obj.msg == "Y"){
                       alert(obj.content);
                       //重新加载.
                       window.location.reload();
                       return ;
                   }
               }
           });
        };
    }
    
    //针对重置按钮失效，清空查询条件
    function clearText(){
    
       $("input[type='text']").each(function (){
           $(this).val("");
           $(this).attr("value","");
       });
    
        var input = document.getElementsByTagName("input");
        for(var i=0;i<input.length;i++){
            if(input[i].type=="text" || input[i].type=="password"){
                input[i].value="";
            }
        }
    }

    function GOTO(url){
        window.location.href=url;
    }

</script>
