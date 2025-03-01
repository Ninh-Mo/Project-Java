<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../resources/css/admin/contentEnroll.css">

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
</head>
</head>
<body>

<%@include file="../includes/admin/header.jsp" %>

<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>コンテンツ情報</span></div>
    <div class="admin_content_main">
        <form:form modelAttribute="contentVO" action="/admin/contentEnroll.do" method="POST"  id="contentForm" >
            <div class="form_section">
                <div class="form_section_title">
                    <label>コンテンツコード</label>
                </div>
                <div class="form_section_content">
                    <form:input path="code" name="code"/>
                    <form:errors path="code" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>タイトル</label>
                </div>
                <div class="form_section_content">
                    <form:input path="title" name="title" />
                    <form:errors path="title" cssClass="error" cssStyle="display: block" />
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>一覧表示行数</label>
                </div>
                <div class="form_section_content">
                    <form:input path="pageRows" name="pageRows" type="number"/>
                    <form:errors path="pageRows" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>ファイルMax件数</label>
                </div>
                <div class="form_section_content">
                    <form:input path="fileMaxCount" name="fileMaxCount" type="number"/>
                    <form:errors path="fileMaxCount" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>ファイルMaxサイズ</label>
                </div>
                <div class="form_section_content">
                    <form:input path="fileMaxSize" name="fileMaxSize" type="number"/>
                    <form:errors path="fileMaxSize" cssClass="error" cssStyle="display: block" />
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>カテゴリー</label>
                </div>
                <div class="form_section_content">
                    <form:input path="category" name="category" />
                    <form:errors path="category" cssClass="error" cssStyle="display: block" />
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>スキン名</label>
                </div>
                <div class="form_section_content">
                    <select name="skinName">
                        <c:forEach items="${listSkinName}" var="skinName">
                            <option c:out value="${skinName}">${skinName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>表示区分</label>
                </div>
                <div class="form_section_content">
                    <select name="displayFlag">
                        <option value="0">表示</option>
                        <option value="1">非表示</option>
                    </select>
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_1</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_1">
                    <span id="warn_wk_1">wk_1</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_2</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_2">
                    <span id="warn_wk_2">wk_2</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_3</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_3">
                    <span id="warn_wk_3">wk_3</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_4</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_4">
                    <span id="warn_wk_4">wk_4</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_5</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_5">
                    <span id="warn_wk_5">wk_5</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_6</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_6">
                    <span id="warn_wk_6">wk_6</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_7</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_7">
                    <span id="warn_wk_7">wk_7</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_8</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_8">
                    <span id="warn_wk_8">wk_8</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_9</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_9">
                    <span id="warn_wk_9">wk_9</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>wk_10</label>
                </div>
                <div class="form_section_content">
                    <input name="wk_10">
                    <span id="warn_wk_10">wk_10</span>
                </div>
            </div>
        </form:form>
        <div class="btn_section">
            <button id="cancelBtn" class="btn">キャンセル</button>
            <button id="contentBtn" class="btn content_btn">追加</button>
        </div>
    </div>
</div>

<%@include file="../includes/admin/footer.jsp" %>

<script>

    /* 취소 버튼 */
    $("#cancelBtn").click(function(){
        location.href="/admin/contentManage"
    });
    $("#contentBtn").click(function(){
        $("#contentForm").submit();
    });

</script>

</body>
</html>