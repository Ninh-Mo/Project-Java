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
    <link rel="stylesheet" href="../resources/css/admin/contentModify.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
</head>
<body>

<%@include file="../includes/admin/header.jsp" %>

<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>コンテンツ修正</span></div>
    <div class="admin_content_main">
        <form:form modelAttribute="contentVO" action="/admin/contentModify" method="post" id="modifyForm">
            <input name="contentsId" type="hidden" value="<c:out value='${contentVO.contentsId}'></c:out>">

            <div class="form_section">
                <div class="form_section_title">
                    <label>コンテンツコード</label>
                </div>
                <div class="form_section_content">
                    <input name="code" readonly="readonly" value="<c:out value='${contentVO.code}'></c:out>">
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>タイトル</label>
                </div>
                <div class="form_section_content">
                    <form:input path="title" name="title" value="${contentVO.title}"/>
                    <form:errors path="title" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>一覧表示行数</label>
                </div>
                <div class="form_section_content">
                    <form:input path="pageRows" value="${contentVO.pageRows}" name="pageRows" type="number"/>
                    <form:errors path="pageRows" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>ファイルMax件数</label>
                </div>
                <div class="form_section_content">
                    <form:input path="fileMaxCount" value="${contentVO.fileMaxCount}" name="fileMaxCount" type="number"/>
                    <form:errors path="fileMaxCount" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>ファイルMaxサイズ</label>
                </div>
                <div class="form_section_content">
                    <form:input path="fileMaxSize" value="${contentVO.fileMaxSize}" name="fileMaxSize" type="number"/>
                    <form:errors path="fileMaxSize" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>カテゴリー</label>
                </div>
                <div class="form_section_content">
                    <form:input path="category" name="category" value="${contentVO.category}" />
                    <form:errors path="category" cssClass="error" cssStyle="display: block" />

                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>スキン名</label>
                </div>
                <div class="form_section_content">
                    <select class="input_block" name="skinName">
                        <c:forEach items="${listSkinName}" var="skinName">
                            <c:choose>
                                <c:when test="${skinName eq contentVO.skinName}">
                                    <option c:out value="${skinName}" selected>${skinName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option c:out value="${skinName}">${skinName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>表示区分</label>
                </div>
                <div class="form_section_content">
                    <select class="input_block" name="displayFlag" >
                        <option value="0" <c:out value=" ${contentVO.displayFlag eq '0' ?'selected':''}"/>>表示</option>
                        <option value="1" <c:out value=" ${contentVO.displayFlag eq '1' ?'selected':''}"/>>非表示</option>
                    </select>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_1</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_1" value="<c:out value='${contentVO.wk_1}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_2</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_2" value="<c:out value='${contentVO.wk_2}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_3</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_3" value="<c:out value='${contentVO.wk_3}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_4</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_4" value="<c:out value='${contentVO.wk_4}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_5</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_5" value="<c:out value='${contentVO.wk_5}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_6</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_6" value="<c:out value='${contentVO.wk_6}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_7</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_7" value="<c:out value='${contentVO.wk_7}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_8</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_8" value="<c:out value='${contentVO.wk_8}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_9</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_9" value="<c:out value='${contentVO.wk_9}'></c:out>" >
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_10</label>
                </div>
                <div class="form_section_content">
                    <input class="input_block" name="wk_10" value="<c:out value='${contentVO.wk_10}'></c:out>" >
                </div>
            </div>

        </form:form>
        <div class="btn_section">
            <button id="cancelBtn" class="btn">キャンセル</button>
            <button id="modifyBtn" class="btn modify_btn">修正</button>
        </div>
    </div>
    <form id="moveForm" action="/admin/contentDetail" method="get" >
        <input type="hidden" name="pageNum" value="${cri.pageNum}">
        <input type="hidden" name="amount" value="${cri.amount}">
        <input type="hidden" name="keyword" value="${cri.keyword}">
        <input type="hidden" name='contentId' value="${contentVO.contentsId}">
    </form>
</div>

<%@include file="../includes/admin/footer.jsp" %>

<script>
    let moveForm = $("#moveForm");
    let modifyForm = $("#modifyForm");
    let contentsId = $('input[name=contentsId]').val();
    console.log(contentsId);

    /* 취소 버튼 */
    $("#cancelBtn").on("click", function(e){
        moveForm.attr("action", "/admin/contentManage")
        moveForm.submit();
    });
    /* 파일 삭제 메서드 */
    function deleteFile(){

        $("#result_card").remove();

    }
    /* 수정 버튼 */
    $("#modifyBtn").on("click",function(e){

        e.preventDefault();
        $("#modifyForm").submit();
    });

</script>

</body>
</html>