<%@ page import="com.vam.model.MessageCommonVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../resources/css/admin/messageEnroll.css">

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
</head>
</head>
<body>

<%@include file="../includes/admin/header.jsp" %>

<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>メッセージ情報入力</span></div>
    <div class="admin_content_main">
        <form:form modelAttribute="messCommonVO" action="/admin/messageEnroll.do" method="post" id="messageForm">
            <input type="hidden" name="messageVO.contentsId" value="${messCommonVO.messageVO.contentsId}">
            <input type="hidden" name="messageFileVO.contentsId" value="${messCommonVO.messageFileVO.contentsId}">
            <input type="hidden" name="messageReq.contentsId" value="${messCommonVO.messageReq.contentsId}">
            <input type="hidden" name="messageReq.fileMaxCount" value="${messCommonVO.messageReq.fileMaxCount}">
            <input type="hidden" name="messageReq.fileMaxSize" value="${messCommonVO.messageReq.fileMaxSize}">
            <input type="hidden" name="messageReq.categoryContent" value="${messCommonVO.messageReq.categoryContent}">
            <input type="hidden" name="messageReq.tableName" value="${messCommonVO.messageReq.tableName}">

            <div class="form_section">
                <div class="form_section_title">
                    <label>カテゴリー</label>
                </div>
                <div class="form_section_content">
                    <select name="messageVO.category">
                        <c:forEach items="${categoryList}" var="category">
                            <option c:out value="${category}">${category}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>メッセージのタイトル</label>
                </div>
                <div class="form_section_content">
                    <form:input path="messageVO.subject" name="subject"/>
                    <form:errors path="messageVO.subject" cssClass="error" cssStyle="display: block"/>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>メッセージのタイトル本文</label>
                </div>
                <div class="form_section_content bit">
                    <form:textarea path="messageVO.body" name="body" id="body_textarea"/>
                    <form:errors path="messageVO.body" cssClass="error" cssStyle="display: block"/>
                </div>
            </div>

            <div class="form_section">
                <form method="POST" enctype="multipart/form-data" action="/admin/uploadFile">
                    <div class="form_section_title">
                        <label>ファイルアップロード</label>
                    </div>
                    <div class="form_section_content">
                        <div class="container">
                            <input type="file" id="upload-button" multiple />
                            <label class="upload-button" for="upload-button"
                            ><i class="fa-solid fa-upload"></i>&nbsp; Choose Or Drop
                            </label>
                            <div id="file-list"></div>
                        </div>
                        <div id="error-max-file"></div>
                    </div>
                    <div class="form_section_title">
                        <ul class="upload_file_link">
                            <li th:each="file : ${files}">
                                <a th:href="${file}" th:text="${file}"/>
                                    <%--                                <a href="#" class="delete_icon">abc</a>--%>
                                    <%--                                <img src="../resources/img/delete_icon.png">--%>
                            </li>
                        </ul>
                    </div>
                </form>
            </div>

        </form:form>
        <div class="btn_section">
            <button id="cancelBtn" class="btn">キャンセル</button>
            <button id="messageBtn" class="btn message_btn">追加</button>
        </div>
    </div>
</div>

<%@include file="../includes/admin/footer.jsp" %>

<%
    MessageCommonVO messageCommonVO = (MessageCommonVO) request.getAttribute("messCommonVO");
    int maxsize = messageCommonVO.getMessageReq().getFileMaxSize();
    int maxFile = messageCommonVO.getMessageReq().getFileMaxCount();
    String fileExtensionListJson = (String) request.getAttribute("fileExtensionListJson");
%>>
<script>
    let uploadButton = document.getElementById("upload-button");
    let fileList = document.getElementById("file-list");
    let container = document.querySelector(".container");
    let errorMaxFile = document.getElementById("error-max-file");
    let fileExtensionList = JSON.parse('<%= fileExtensionListJson %>');
    var maxSizeB =<%= maxsize %>*1000000;
    var maxSizeM = <%= maxsize %>;
    var maxFile =  <%= maxFile %>;

    var fileObject;

    console.log(maxSizeM);
    console.log(maxSizeM);
    console.log(maxFile);
    console.log(fileExtensionList);
    let totalFiles = 0;
    let flagFirstUpload = 0;

    function fileHandler(files){
        errorMaxFile.innerHTML='';
        var fileLoad = files.length;
        var totalFilesAfterLoad = fileLoad + totalFiles;
        console.log(totalFiles);
        console.log(files);
        if (totalFilesAfterLoad > maxFile) {
            errorMaxFile.innerHTML += '<div>最大ファイル数は' + maxFile + 'ファイルを超えることはできません</div>';
        } else {
            flagFirstUpload = 1;
            Array.from(files).forEach((file) => {
                var fileName = file.name;
                const fileExtension = fileName.split('.').pop().toLowerCase();
                console.log(fileExtension);
                if (fileExtensionList.includes(fileExtension)) {
                    errorMaxFile.innerHTML += '<div class="error-max-file">' + fileExtension + 'ファイルアップロードできません</div>';
                    totalFilesAfterLoad -= 1;
                    return;
                }
                if (file.size > maxSizeB) {
                    errorMaxFile.innerHTML += '<div class="error-max-file">' + fileName + 'はファイルサイズの上限を超えています。' + maxSizeM + 'Mb 以下のファイルを選択してください。</div>';
                    totalFilesAfterLoad -= 1;
                    return;
                }
                totalFiles = totalFilesAfterLoad;
                console.log(totalFiles);
                const listItem = document.createElement('div');
                listItem.textContent = fileName;
                const iconImg = document.createElement('img');
                iconImg.src = '../resources/img/delete_icon.png';
                iconImg.alt = 'Delete';
                iconImg.style.paddingLeft = '10px';
                iconImg.onclick = function () {
                    totalFiles -= 1;
                    listItem.remove();
                }
                listItem.appendChild(iconImg);
                fileList.appendChild(listItem);
                fileObject = {
                    name: file.name,
                    size: file.size,
                    type: file.type
                }
            });
        }

    }

    //Upload Button
    uploadButton.addEventListener("change", () => {
        const files = uploadButton.files;
        fileHandler(files);
    });
    container.addEventListener(
        "dragenter",
        (e) => {
            e.preventDefault();
            e.stopPropagation();
            container.classList.add("highlight");
        },
        false
    );
    container.addEventListener(
        "dragleave",
        (e) => {
            e.preventDefault();
            e.stopPropagation();
            container.classList.remove("highlight");
        },
        false
    );
    container.addEventListener(
        "dragover",
        (e) => {
            e.preventDefault();
            e.stopPropagation();
            container.classList.add("highlight");
        },
        false
    );
    container.addEventListener(
        "drop",
        (e) => {
            e.preventDefault();
            e.stopPropagation();
            container.classList.remove("highlight");
            let draggedData = e.dataTransfer;
            let files = draggedData.files;
            fileHandler(files);
        },
        false
    );
    window.onload = () => {
        fileList.innerText = "";
    };

    ClassicEditor
        .create(document.querySelector('#body_textarea'))
        .catch(error => {
            console.error(error);
        });

    /* 등록 버튼 */
    $("#messageBtn").click(function () {

        $("#messageForm").submit();
    });

    /* 취소 버튼 */
    $("#cancelBtn").click(function () {
        location.href = "/admin/messageManage"
    });

    $('.authorId_btn').on("click", function (e) {
        e.preventDefault();
        let popUrl = "/admin/fileUpload";
        let popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";

        window.open(popUrl, "작가 찾기", popOption);

    });

</script>

</body>
</html>