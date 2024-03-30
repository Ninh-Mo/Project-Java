<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../resources/css/admin/messageDetail.css">
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
</head>
<script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
<body>
<%@include file="../includes/admin/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>メッセージ詳細</span></div>
    <div class="admin_content_main">
        <div class="form_section">
            <div class="form_section_title">
                <label>メッセージ Id</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="messagesId" readonly="readonly" value="<c:out value='${messageInfo.messagesId }'></c:out>">

            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>コンテンツ管理テーブルID</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="code" readonly="readonly" value="<c:out value='${code}'></c:out>">

            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>メッセージのタイトル</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="subject" readonly="readonly" value="<c:out value='${messageInfo.subject }'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>メッセージのタイトル本文</label>
            </div>
            <div class="form_section_content bit">
                <textarea name="body" id="body_textarea" disabled>${messageInfo.body}</textarea>
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>閲覧カウント</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="fileMaxCount" readonly="readonly" value="<c:out value='${messageInfo.viewCount}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>カテゴリー</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="category" readonly="readonly" value="<c:out value='${messageInfo.category}'></c:out>" >
            </div>
        </div>
        <div class="btn_section">
            <button id="cancelBtn" class="btn">キャンセル</button>
            <button id="modifyBtn" class="btn modify_btn">修正</button>
            <button id="deleteBtn" class="btn delete_btn">削除</button>
        </div>
    </div>
</div>
<form id="moveForm" method="get">
    <input type="hidden" name="contentsId" value='<c:out value="${messageInfo.contentsId}"/>'>
    <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
    <input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
    <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
    <input type="hidden" name="tableName" value='<c:out value="${tableName }"/>'>
</form>
<%@include file="../includes/admin/footer.jsp" %>

<script>
    ClassicEditor
        .create(document.querySelector('#body_textarea'))
        .then(editor => {
            console.log(editor);
            editor.isReadOnly = true;
        })
        .catch(error=>{
            console.error(error);
        });

    let moveForm = $("#moveForm");

    /* 작가 관리 페이지 이동 버튼 */
    $("#cancelBtn").on("click", function(e){
        e.preventDefault();
        var isConfirmed = confirm("キャンセルしていいですか？");
        if (isConfirmed) {
            moveForm.attr("action", "/admin/messageList")
            moveForm.submit();
        }
    });

    /* 작가 수정 페이지 이동 버튼 */
    $("#modifyBtn").on("click", function(e){

        e.preventDefault();
        moveForm.append('<input type="hidden" name="messagesId" value="${messageInfo.messagesId}">');
        moveForm.attr("action", "/admin/messageModify");
        moveForm.submit();

    });
    $("#deleteBtn").on("click", function(e){
        e.preventDefault();
        let moveForm = $("#moveForm");
        var isConfirmed = confirm("このデータを削除していいですか？");
        if (isConfirmed) {
            moveForm.find("input").remove();
            moveForm.append('<input type="hidden" name="messagesId" value="${messageInfo.messagesId}">');
            moveForm.attr("action", "/admin/messageDelete");
            moveForm.attr("method", "post");
            moveForm.submit();
        } else {
            alert("キャンセル");
            moveForm.attr("action", "/admin/messageList")
        }
    });

</script>

</body>
</html>