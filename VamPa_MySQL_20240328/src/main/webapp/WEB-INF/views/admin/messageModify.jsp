<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../resources/css/admin/messageModify.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
</head>
<body>

<%@include file="../includes/admin/header.jsp" %>

<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>メッセージ修正</span></div>
    <div class="admin_content_main">
        <form:form modelAttribute="messageVO" action="/admin/messageModify" method="post" id="modifyForm">
            <input type="hidden" name="tableName" value="${tableName}">
            <input type="hidden" name="messagesId" value="${messageVO.messagesId}">
            <div class="form_section">
                <div class="form_section_title">
                    <label>メッセージのタイトル</label>
                </div>
                <div class="form_section_content">
                    <form:input path="subject" name="subject" value="${messageVO.subject}"/>
                    <form:errors path="subject" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>メッセージの本文</label>
                </div>
                <div class="form_section_content">
                    <form:textarea path="body" name="body" id="body_textarea" value="${messageVO.body}"/>
                    <form:errors path="body" cssClass="error" cssStyle="display: block" />
                </div>
            </div>

        </form:form>
        <div class="btn_section">
            <button id="cancelBtn" class="btn">キャンセル</button>
            <button id="modifyBtn" class="btn modify_btn">更新</button>
        </div>
    </div>
    <form id="moveForm" action="/admin/messageDetail" method="get" >
        <input type="hidden" name="pageNum" value="${cri.pageNum}">
        <input type="hidden" name="amount" value="${cri.amount}">
        <input type="hidden" name="keyword" value="${cri.keyword}">
        <input type="hidden" name="message" value="${messageVO}">
    </form>
</div>

<%@include file="../includes/admin/footer.jsp" %>

<script>

    ClassicEditor
        .create(document.querySelector('#body_textarea'))
        .then(editor => {
            console.log(editor);
        })
        .catch(error=>{
            console.error(error);
        });
    let moveForm = $("#moveForm");
    let modifyForm = $("#modifyForm");
    $("#cancelBtn").on("click", function(e){
        e.preventDefault();
        var isConfirmed = confirm("キャンセルしていいですか？");
        if (isConfirmed) {
            moveForm.attr("action", "/admin/messageList")
            moveForm.submit();
        }
    });
    /* 파일 삭제 메서드 */
    function deleteFile(){

        $("#result_card").remove();

    }
    /* 수정 버튼 */
    $("#modifyBtn").on("click",function(e){
        e.preventDefault();
        modifyForm.append('<input type="hidden" name="contentsId" value="${messageVO.contentsId}">');
        $("#modifyForm").submit();
    });

</script>

</body>
</html>