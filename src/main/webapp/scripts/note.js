//封装了note的操作
function loadBookNotes() {
    //显示切换
    $("#pc_part_2").show();
    $("#pc_part_6").hide();
    $("#pc_part_4").hide();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    //清空原有的noteli列表
    $("#note_ul li").remove();
    //清楚选中效果
    $("#book_ul a").removeClass("checked");
    //获取请求参数
    var bookId = $(this).data("bookId");
    $(this).find("a").addClass("checked");
    //参数格式校验
    //发送Ajax
    $.ajax({
       url: base_path+"/note/loadnotes.do",
       type: "post",
       data:{"bookId":bookId},
        dataType: "json",
        success:function (result) {
           //获取服务器端传过来的笔记集合
           var notes = result.data;
           //循环生成笔记li
            for (var i = 0;i < notes.length; i++ ){
                //获取笔记ID
                var noteId = notes[i].cn_note_id;
                var noteTitle = notes[i].cn_note_title;
                createNoteLi(noteId,noteTitle);
                //将新添加的元素判断是否该加分享标
                var typeId = notes[i].cn_note_type_id;
                if (typeId == 2){
                    //分享过来
                    var img = '<i class="fa fa-sitemap"></i>';
                    // $li.find(".btn_slide_down").before(img);
                    //获取新添加的li
                    var $li = $("#note_ul li:last");
                    //find 伪类选择器
                    $li.find(".btn_slide_down").before(img);
                }
            }
        },
        error:function() {
           alert("笔记加载异常");
        }
    });
}

//创建笔记li元素
function createNoteLi(noteId,noteTitle) {
    var sli = '';
    sli += '<li class="online">';
    sli += '<a>';
    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli += '</a>';
    sli += '<div class="note_menu" tabindex="-1">';
    sli += '<dl>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    sli += '</dl>';
    sli += '</div>';
    sli += '</li>';
    //将noteId绑定到对应li上
    var $li = $(sli);
    $li.data("noteId",noteId);
    //将li元素添加到笔记列表中
    $("#note_ul").append($li);
}
//根据笔记id加载笔记信息
function loadNote() {
    //清楚样式
    $("#note_ul a").removeClass("checked");
    //设置笔记选中效果
    $(this).find("a").addClass("checked");
    //获取请求参数
    var noteId = $(this).data("noteId");
    // console.log(noteId);
    //参数格式校验
    //发送Ajax
    $.ajax({
        url:base_path+"/note/load.do",
        type: "post",
        data: {"noteId":noteId},
        dataType: "json",
        success:function (result) {
            if (result.status == 0){
                var title = result.data.cn_note_title;
                var body = result.data.cn_note_body;
                //设置到编辑区域
                $("#input_note_title").val(title);
                um.setContent(body);
                //切换显示
                $("#pc_part_5").hide();
                $("#pc_part_3").show();
            }
        },
        error:function () {
            alert("加载笔记异常");
        }
    });
}
//"保存笔记"按钮的处理
function updateNote() {
    //获取请求参数
    var title = $("#input_note_title").val();
    var body = um.getContent();
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    $("#note_title_span").html("");
    //参数格式校验
    if (title == ""){
        $("#note_title_span").html("<font color='red'>标题不能为空</font>");
    }else if ($li.length == 0){
        alert("请选择要保存的笔记");
    }else {
        //发送Ajax
        $.ajax({
            url:base_path+"/note/update.do",
            type:"post",
            data:{"noteId":noteId,"title":title,"body":body},
            dataType:"json",
            success:function(result){
                if (result.status == 0){
                    //更新列表li中的标题
                    var sli = "";
                    sli += '';
                    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i>';
                    $li.find("a").html(sli);
                    alert(result.msg);
                }
            },
            error:function () {
                alert("保存笔记异常");
            }
        });
    }
}
//创建笔记
function addNote() {
    //获取参数
    var userId = getCookie("uid");
    var title = $("#input_note").val().trim();
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");
    var ok = true;
    if (userId == null){
        ok = false;
        window.location.href = "log_in.html";
    }
    if (title == ""){
        ok = false;
        $("#note_span").html("笔记名为空");
    }
    if(ok){
        $.ajax({
            url:base_path+"/note/add.do",
            type:"post",
            data:{"userId":userId,"bookId":bookId,"title":title},
            dataType:"json",
            success:function (result) {
                if(result.status == 0){
                    //关闭创建笔记弹窗
                    closeAlertWindow();
                    var noteId = result.data;
                    //生成笔记li
                    createNoteLi(noteId,title);
                    //提示成功
                    alert(result.msg);
                }
            },
            error:function () {
                alert("创建笔记异常");
            }
        });
    }
}
//显示笔记菜单
function popNoteMenu() {
    //清楚所有笔记菜单
    $("#note_ul div").hide();
    //获取笔记菜单
    var $menus =  $(this).parent().next();
    $menus.slideDown(1000);
    //点击下拉对应的笔记为选中状态
    $("#note_ul a").removeClass("checked");
    $(this).parent().addClass("checked");
    return false;
}
//隐藏笔记菜单
function hideNoteMenu() {
    $("#note_ul div").hide();
}
//笔记删除
function deleteNote() {
    //获取参数
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //参数格式校验
    //发送Ajax
    $.ajax({
        url:base_path+"/note/delete.do",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function (result) {
            //关闭删除笔记对话框
            closeAlertWindow();
            //移除笔记li
            $li.remove();
            //弹出成功提示信息
            alert(result.msg);
        },
        error:function () {
            alert("删除笔记异常");
        }
    });
}
function moveNote() {
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    var bookId = $("#moveSelect").val();
    if (bookId == "" || bookId == "none"){
        $("#moveSelect_span").html("请选择笔记本");
    }
    $.ajax({
        url:base_path+"/note/move.do",
        type:"post",
        data:{"noteId":noteId,"bookId":bookId},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                closeAlertWindow();
                $li.remove();
                alert(result.msg);
            }
        },
        error:function () {
            alert("移除笔记异常");
        }
    })
}
function shareNote() {
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //var noteId = $(this).parents("noteId");
    $.ajax({
        url:base_path+"/note/share.do",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function (result) {

            if (result.status == 0){
                var img = '<i class="fa fa-sitemap"></i>';
                $li.find(".btn_slide_down").before(img);
            }
            alert(result.msg);
        },
        error:function () {
            alert("分享失败");
        }
    });
}
//搜索分享笔记
function searchSharePage(keyword,page) {
    //获取参数
    //参数格式检验
    //发送Ajax
    $.ajax({
        url:base_path+"/note/searchShare.do",
        type:"post",
        data:{"keyword":keyword,"page":page},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                //获取服务器端返回的列表
                var shares = result.data;
                //循环生成share的li
                for (var  i = 0;i < shares.length; i++){
                    //分享ID
                    var shareId = shares[i].cn_share_id;
                    //分享标题
                    var shareTitle = shares[i].cn_share_title;
                    var sli = '';
                    sli += '<li class="online">';
                    sli += '<a>';
                    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+shareTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
                    sli += '</a>';
                    sli += '<div class="note_menu" tabindex="-1">';
                    sli += '</div>';
                    sli += '</li>'
                    var $li = $(sli);
                    $li.data("shareId",shareId);
                    $("#pc_part_6 ul").append($li);
                }
            }
        },
        error:function () {
            alert("搜索分享笔记异常");
        }
    });
}
//搜索分享笔记的列表信息
function shareReveal(){
    $("li a").removeClass("checked");
    //设置选中效果
    $(this).find("a").addClass("checked");
    //获取请求参数
    var shareId = $(this).data("shareId");
    //参数格式校验
    //发送Ajax
    $.ajax({
        url:base_path+"/note/findById.do",
        type:"post",
        data:{"shareId":shareId},
        dataType:"json",
        success:function (result) {
            //获取分享标题
            var title = result.data.cn_share_title;
            //获取分享内容
            var body = result.data.cn_share_body;
            //设置标题和内容
            $("#noput_note_title").html(title);
            $("#noput_note_title").next().html(body);
            //切换显示
            $("#pc_part_5").show();
            $("#pc_part_3").hide();
        },
        error:function () {
            alert("加载分享笔记异常");
        }
    })
}