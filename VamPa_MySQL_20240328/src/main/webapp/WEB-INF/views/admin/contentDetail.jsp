<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../resources/css/admin/contentDetail.css">

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
</head>
<body>
<%@include file="../includes/admin/header.jsp" %>
<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>コンテンツ詳細</span></div>
    <div class="admin_content_main">
        <div class="form_section">
            <div class="form_section_title">
                <label>コンテンツId</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="contentId" readonly="readonly" value="<c:out value='${contentVO.contentsId }'></c:out>">

            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>コンテンツコード</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="code" readonly="readonly" value="<c:out value='${contentVO.code }'></c:out>">

            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>タイトル</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="title" readonly="readonly" value="<c:out value='${contentVO.title }'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>一覧表示行数</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="pageRows" readonly="readonly" value="<c:out value='${contentVO.pageRows }'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>ファイルMax件数</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="fileMaxCount" readonly="readonly" value="<c:out value='${contentVO.fileMaxCount}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>ファイルMaxサイズ</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="fileMaxSize" readonly="readonly" value="<c:out value='${contentVO.fileMaxSize}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>カテゴリー</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="pageRows" readonly="readonly" value="<c:out value='${contentVO.category}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>スキン名</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="pageRows" readonly="readonly" value="<c:out value='${contentVO.skinName}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>表示区分</label>
            </div>
            <div class="form_section_content">
                    <c:if test="${contentVO.displayFlag eq '0'}">
                        <input class="input_block" name="skinName" readonly="readonly" value="表示" >
                    </c:if>
                    <c:if test="${contentVO.displayFlag eq '1'}">
                        <input class="input_block" name="skinName" readonly="readonly" value="非表示" >
                    </c:if>
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_1</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_1" readonly="readonly" value="<c:out value='${contentVO.wk_1}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_2</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_2" readonly="readonly" value="<c:out value='${contentVO.wk_2}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_3</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_3" readonly="readonly" value="<c:out value='${contentVO.wk_3}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_4</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_4" readonly="readonly" value="<c:out value='${contentVO.wk_4}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_5</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_5" readonly="readonly" value="<c:out value='${contentVO.wk_5}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_6</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_6" readonly="readonly" value="<c:out value='${contentVO.wk_6}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_7</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_7" readonly="readonly" value="<c:out value='${contentVO.wk_7}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_8</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_8" readonly="readonly" value="<c:out value='${contentVO.wk_8}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_9</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_9" readonly="readonly" value="<c:out value='${contentVO.wk_9}'></c:out>" >
            </div>
        </div>
        <div class="form_section">
            <div class="form_section_title">
                <label>wk_10</label>
            </div>
            <div class="form_section_content">
                <input class="input_block" name="wk_10" readonly="readonly" value="<c:out value='${contentVO.wk_10}'></c:out>" >
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
    <input type="hidden" name="contentId" value='<c:out value="${contentVO.contentsId}"/>'>
    <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
    <input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
    <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
</form>
<%@include file="../includes/admin/footer.jsp" %>

<script></script>

<script>

    let moveForm = $("#moveForm");

    /* 작가 관리 페이지 이동 버튼 */
    $("#cancelBtn").on("click", function(e){

        e.preventDefault();
        moveForm.attr("action", "/admin/contentManage")
        moveForm.submit();

    });

    /* 작가 수정 페이지 이동 버튼 */
    $("#modifyBtn").on("click", function(e){
        e.preventDefault();
        moveForm.attr("action", "/admin/contentModify");
        moveForm.submit();

    });

    /* 삭제 버튼 */
    $("#deleteBtn").on("click", function(e){
        e.preventDefault();
        let moveForm = $("#moveForm");
        var isConfirmed = confirm("このデータを削除していいですか？");
        if (isConfirmed) {
            moveForm.find("input").remove();
            moveForm.append('<input type="hidden" name="contentId" value="${contentVO.contentsId}">');
            moveForm.attr("action", "/admin/contentDelete");
            moveForm.attr("method", "post");
            moveForm.submit();
        } else {
            alert("キャンセル");
            moveForm.attr("action", "/admin/contentManage")
        }
    });

</script>

</body>
</html>