function subbmitComment() {
    var parentId = $("#parentId").val();
    var content = $("#content").val();
    postComment(parentId, 1, content);
}
function subbmitChildComment(){
    var id = $("#parentCommentId").val();
    var content = $("#childComment").val();
    postComment(id, 2, content);
    btnHidden();
}

function postComment(parentId, type, content){
    if (content == null || content == ""){
        alert("评论内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        dataType: "json",
        //将数据转化为string再进行传输
        data: JSON.stringify({
            type: type,
            parentId: parentId,
            content: content
        }),
        success: function(result){
            if (result.code == 200){
                window.location.reload();
            }else{
                alert(result.message);
            }
        }
    });
}

function collapseComment(e) {
    var id = e.getAttribute("data-id");
    var content = $("#comment-"+id);

    var collapse = e.getAttribute("data-collapse");
    if(collapse){
        //关闭二级评论
        content.removeClass("in");
        //标记关闭状态
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{
        //展开二级评论
        content.addClass("in");
        //标记激活状态
        e.setAttribute("data-collapse");
        e.classList.add("active");
    }
}

function btnShow(){
    $("#commentBtn").show();
}
function btnHidden(){
    $("#commentBtn").hide();
}