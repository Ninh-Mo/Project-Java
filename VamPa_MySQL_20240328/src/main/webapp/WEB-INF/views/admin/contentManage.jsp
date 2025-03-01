<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../resources/css/admin/contentManage.css">

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
</head>
</head>
<body>

<%@include file="../includes/admin/header.jsp" %>

<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>コンテンツ管理</span></div>
    <div class="author_table_wrap">
        <!-- 게시물 O -->
        <c:if test="${listCheck != 'empty' }">
            <table class="author_table">
                <thead>
                <tr>
                    <td class="th_column_1">Content ID</td>
                    <td class="th_column_2">Code</td>
                    <td class="th_column_3">Title</td>
                    <td class="th_column_4">Page Rows</td>
                    <td class="th_column_5">File Max Count</td>
                    <td class="th_column_6">File Max Size</td>
                    <td class="th_column_7">Skin Name</td>
                    <td class="th_column_8">Display Flag</td>
                    <td class="th_column_9">Create Date</td>
                    <td class="th_column_10">Edit Date</td>

                </tr>
                </thead>
                <c:forEach items="${list}" var="list">
                    <tr>
                        <td><c:out value="${list.contentsId}"></c:out> </td>
                        <td>
                            <a class="move" href='<c:out value="${list.contentsId}"/>'>
                                <c:out value="${list.code}"></c:out>
                            </a>
                        </td>
                        <td><c:out value="${list.title}"></c:out> </td>
                        <td><c:out value="${list.pageRows}"></c:out> </td>
                        <td><c:out value="${list.fileMaxCount}"></c:out> </td>
                        <td><c:out value="${list.fileMaxSize}"></c:out> </td>
                        <td><c:out value="${list.skinName}"></c:out> </td>
                        <td>
                            <c:choose>
                                <c:when test="${list.displayFlag eq 0}">
                                    表示
                                </c:when>
                                <c:otherwise>
                                    非表示
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${list.updDate}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <!-- 게시물 x -->
        <c:if test="${listCheck == 'empty'}">
            <div class="table_empty">
                データが存在していません。
            </div>
        </c:if>

    </div>

    <!-- 검색 영역 -->
    <div class="search_wrap">
        <form id="searchForm" action="/admin/contentManage" method="get">
            <div class="search_input">
                <input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                <input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
                <input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                <button class='btn search_btn'>検索</button>
            </div>
        </form>
        <div class="btn_section">
            <button id="addContentBtn" class="btn addContent_btn">追加</button>
        </div>
    </div>

    <!-- 페이지 이동 인터페이스 영역 -->
    <div class="pageMaker_wrap" >

        <ul class="pageMaker">

            <!-- 이전 버튼 -->
            <c:if test="${pageMaker.prev}">
                <li class="pageMaker_btn prev">
                    <a href="${pageMaker.cri.pageNum - 1}">前</a>
                </li>
            </c:if>

            <!-- 페이지 번호 -->
            <c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}" var="num">
                <li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
                    <a href="${num}">${num}</a>
                </li>
            </c:forEach>

            <!-- 다음 버튼 -->
            <c:if test="${pageMaker.next}">
                <li class="pageMaker_btn next">
                    <a href="${pageMaker.cri.pageNum + 1 }">後</a>
                </li>
            </c:if>

        </ul>

    </div>

    <form id="moveForm" action="/admin/contentManage" method="get">
        <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
        <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
        <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
    </form>

</div>

<%@include file="../includes/admin/footer.jsp" %>

<script>
    $(document).ready(function(){

        let result = '<c:out value="${enroll_result}"/>';
        let mresult = '<c:out value="${modify_result}"/>';
        let eResult = '<c:out value="${edit_result}"/>';
        console.log(eResult);

        checkResult(result);
        checkmResult(mresult);
        checkEditResult(eResult);

        function checkResult(result){
            if(result === ''){
                return;
            }
            alert("コンテンツ'${enroll_result}'追加しました。.");
        }
        function checkEditResult(result){
            if(result === ''){
                return;
            }
            alert("データが存在していません。");
        }

        function checkmResult(mresult){
            if(mresult === '1'){
                alert("コンテンツ修正が完了しました。");
            } else if(mresult === '0') {
                alert("コンテンツ修正が失敗しました。")
            }
        }

        /* 삭제 결과 경고창 */
        let delete_result = '${delete_result}';
        console.log(delete_result);
        if(delete_result == ''){
            return;
        }else if(delete_result == 1){
            alert("削除が完了しました。");
        } else if(delete_result == 2){
            alert("コンテンツが使用しているため、削除が出来ません。")
        } else if(delete_result == 0){
            alert("データが存在していません。")
        }
    });

    let moveForm = $('#moveForm');
    let searchForm = $('#searchForm');

    /* 페이지 이동 버튼 */
    $(".pageMaker_btn a").on("click", function(e){

        e.preventDefault();

        moveForm.find("input[name='pageNum']").val($(this).attr("href"));

        moveForm.submit();

    });

    /* 작거 검색 버튼 동작 */
    $("#searchForm button").on("click", function(e){

        e.preventDefault();

        /* 검색 키워드 유효성 검사 */
        if(!searchForm.find("input[name='keyword']").val()){
            alert("検索キーワード入力してください。");
            return false;
        }

        searchForm.find("input[name='pageNum']").val("1");

        searchForm.submit();

    });

    /* 작가 상세 페이지 이동 */
    $(".move").on("click", function(e){

        e.preventDefault();
        moveForm.append("<input type='hidden' name='contentId' value='"+ $(this).attr("href") + "'>");
        moveForm.attr("action", "/admin/contentDetail");
        moveForm.submit();

    });

    $("#addContentBtn").click(function(){
        location.href="/admin/contentEnroll"
    });

</script>

</body>
</html>