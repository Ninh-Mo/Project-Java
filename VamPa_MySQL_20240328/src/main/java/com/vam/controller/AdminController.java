package com.vam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vam.model.*;
import com.vam.request.AddMessageRequest;
import com.vam.request.MessageListRequest;
import com.vam.service.AdminService;
import com.vam.service.AuthorService;
import com.vam.service.OrderService;
import com.vam.utils.Constant;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;	
	
	@Autowired
	private OrderService orderService;
	
	/* 관리자 메인 페이지 이동 */
	@RequestMapping(value="main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception{
		
		logger.info("관리자 페이지 이동");
	}
	
	/* 상품 관리(상품목록) 페이지 접속 */
	@RequestMapping(value = "goodsManage", method = RequestMethod.GET)
	public void goodsManageGET(Criteria cri, Model model) throws Exception{
		
		logger.info("상품 관리(상품목록) 페이지 접속");
		
		/* 상품 리스트 데이터 */
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		/* 페이지 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
		
	}
	
	/* 상품 등록 페이지 접속 */
	@RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
	public void goodsEnrollGET(Model model) throws Exception{
		
		logger.info("상품 등록 페이지 접속");
		
		ObjectMapper objm = new ObjectMapper();

		List list = adminService.cateList();

		String cateList = objm.writeValueAsString(list);

		model.addAttribute("cateList", cateList);
		
	}
	
	/* 상품 조회 페이지, 상품 수정 페이지 */
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException {
		
		logger.info("goodsGetInfo()........." + bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		/* 카테고리 리스트 데이터 */
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));		
		
		/* 목록 페이지 조건 정보 */
		model.addAttribute("cri", cri);
		
		/* 조회 페이지 정보 */
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
		
	}	
	
	/* 상품 정보 수정 */
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO vo, RedirectAttributes rttr) {
		
		logger.info("goodsModifyPOST.........." + vo);
		
		int result = adminService.goodsModify(vo);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";		
		
	}	
	
	/* 상품 정보 삭제 */
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		
		logger.info("goodsDeletePOST..........");
		
		
		List<AttachImageVO> fileList = adminService.getAttachInfo(bookId);
		
		if(fileList != null) {
			
			List<Path> pathList = new ArrayList();
			
			fileList.forEach(vo ->{
				
				// 원본 이미지
				Path path = Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
				pathList.add(path);
				
				// 섬네일 이미지
				path = Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid()+"_" + vo.getFileName());
				pathList.add(path);
				
			});
			
			pathList.forEach(path ->{
				path.toFile().delete();
			});
			
		}				
		
		int result = adminService.goodsDelete(bookId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
	
	/* 작가 등록 페이지 접속 */
	@RequestMapping(value = "authorEnroll", method = RequestMethod.GET)
	public void authorEnrollGET() throws Exception{
		logger.info("작가 등록 페이지 접속");
	}
	
	/* 작가 관리 페이지 접속 */
	@RequestMapping(value = "authorManage", method = RequestMethod.GET)
	public void authorManageGET(Criteria cri, Model model) throws Exception{
		
		logger.info("작가 관리 페이지 접속.........." + cri);
		
		/* 작가 목록 출력 데이터 */
		List list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
		
		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
		
	}		
	
	/* 작가 등록 */
	@RequestMapping(value="authorEnroll.do", method = RequestMethod.POST)
	public String authorEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{

		logger.info("authorEnroll :" +  author);
		
		authorService.authorEnroll(author);  	// 작가 등록 쿼리 수행
		
		rttr.addFlashAttribute("enroll_result", author.getAuthorName());	// 등록 성공 메시지(작가이름)
		
		return "redirect:/admin/authorManage";
		
	}
	
	/* 작가 상세, 수정 페이지 */
	@GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		
		logger.info("authorDetail......." + authorId);
		
		/* 작가 관리 페이지 정보 */
		model.addAttribute("cri", cri);
		
		/* 선택 작가 정보 */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
		
	}	
		
	/* 작가 정보 수정 */
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
		
		logger.info("authorModifyPOST......." + author);
		
		int result = authorService.authorModify(author);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
	/* 작가 정보 삭제 */
	@PostMapping("/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes rttr) {
		
		logger.info("authorDeletePOST..........");
		
		int result = 0;
		
		try {
			
			result = authorService.authorDelete(authorId);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/authorManage";
			
		}
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/authorManage";
		
	}		

	/* 상품 등록 */
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {
		
		logger.info("goodsEnrollPOST......" + book);
		
		adminService.bookEnroll(book);
		
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		
		return "redirect:/admin/goodsManage";
	}	

	
	/* 작가 검색 팝업창 */
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception{
		
		logger.info("authorPopGET.......");
		
		cri.setAmount(5);
		
		/* 게시물 출력 데이터 */
		List list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}

		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));		
	}	
		
	/* 첨부 파일 업로드 */
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		logger.info("uploadAjaxActionPOST..........");
		
		/* 이미지 파일 체크 */
		for(MultipartFile multipartFile: uploadFile) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				logger.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) {
				
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
				
			}
			
		}// for
		
		String uploadFolder = "C:\\upload";
		
		/* 날짜 폴더 경로 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-", File.separator);
		
		/* 폴더 생성 */
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		/* 이미저 정보 담는 객체 */
		List<AttachImageVO> list = new ArrayList();
		
		// 향상된 for
		for(MultipartFile multipartFile : uploadFile) {
			
			/* 이미지 정보 객체 */
			AttachImageVO vo = new AttachImageVO();
			
			/* 파일 이름 */
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			/* uuid 적용 파일 이름 */
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			/* 파일 위치, 파일 이름을 합친 File 객체 */
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* 파일 저장 */
			try {
				
				multipartFile.transferTo(saveFile);
				
				/* 썸네일 생성(ImageIO) */
				/*
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName); 
				
				BufferedImage bo_image = ImageIO.read(saveFile);

					//비율 
					double ratio = 3;
					//넓이 높이
					int width = (int) (bo_image.getWidth() / ratio);
					int height = (int) (bo_image.getHeight() / ratio);				
				
				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
								
				Graphics2D graphic = bt_image.createGraphics();
				
				graphic.drawImage(bo_image, 0, 0,width,height, null);
					
				ImageIO.write(bt_image, "jpg", thumbnailFile);				
				*/
				
				/* 방법 2 */
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);	
				
					BufferedImage bo_image = ImageIO.read(saveFile);

					//비율 
					double ratio = 3;
					//넓이 높이
					int width = (int) (bo_image.getWidth() / ratio);
					int height = (int) (bo_image.getHeight() / ratio);					
				
				
				Thumbnails.of(saveFile)
		        .size(width, height)
		        .toFile(thumbnailFile);
					
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			} 
			
			list.add(vo);
			
		} //for

		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		
		return result;
	}
	
	/* 이미지 파일 삭제 */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		logger.info("deleteFile........" + fileName);
		
		File file = null;
		
		try {
			/* 썸네일 파일 삭제 */
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			/* 원본 파일 삭제 */
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			logger.info("originFileName : " + originFileName);
			
			file = new File(originFileName);
			
			file.delete();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
			
		} // catch
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
		
	}	
	
	/* 주문 현황 페이지 */
	@GetMapping("/orderList")
	public String orderListGET(Criteria cri, Model model) {
		
		List<OrderDTO> list = adminService.getOrderList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			model.addAttribute("pageMaker", new PageDTO(cri, adminService.getOrderTotal(cri)));
		} else {
			model.addAttribute("listCheck", "empty");
		}

		return "/admin/orderList";
	}	
	
	/* 주문삭제 */
	@PostMapping("/orderCancle")
	public String orderCanclePOST(OrderCancelDTO dto) {
		
		orderService.orderCancle(dto);
		
		return "redirect:/admin/orderList?keyword=" + dto.getKeyword() + "&amount=" + dto.getAmount() + "&pageNum=" + dto.getPageNum();
	}

	@RequestMapping(value = "contentManage", method = RequestMethod.GET)
	public void contentManageGET(Criteria cri, Model model) throws Exception{

		logger.info("content Manage.........." + cri);

		/* 작가 목록 출력 데이터 */
		List list = adminService.contentGetList(cri);

		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.contentGetTotal(cri)));

	}
	@RequestMapping(value = "contentEnroll", method = RequestMethod.GET)
	public void contentEnrollGET(@ModelAttribute("contentVO") ContentVO contentVO,Model model) throws Exception{
		logger.info("コンテンツ情報入力");
		List<String> listSkinName = adminService.getPackageList(Constant.BASE_PACKAGE);
		if(!listSkinName.isEmpty()) {
			model.addAttribute("listSkinName",listSkinName);	// 작가 존재 경우
		} else {
			model.addAttribute("listSkinName", "empty");	// 작가 존재하지 않을 경우
		}
	}

@RequestMapping(value="contentEnroll.do", method = RequestMethod.POST)
public String contentEnrollPOST(@Valid  @ModelAttribute("contentVO") ContentVO contentVO, BindingResult bindingResult, Model model,RedirectAttributes rttr) throws IOException, ClassNotFoundException {
	logger.info("contentEnroll :" +  contentVO);
	List<String> listSkinName = adminService.getPackageList(Constant.BASE_PACKAGE);
	if(!listSkinName.isEmpty()) {
		model.addAttribute("listSkinName",listSkinName);	// 작가 존재 경우
	} else {
		model.addAttribute("listSkinName", "empty");	// 작가 존재하지 않을 경우
	}
	try {
		if (bindingResult.hasErrors()) {
			return "admin/contentEnroll";
		}
		adminService.contentEnroll(contentVO);
		String tableName = contentVO.getCode() + "_message";
		int lastId = adminService.getLastInsertedId();
		contentVO.setContentsId(lastId);
		String constraint = "fk_messages_contentsId"+lastId;
		try {
			adminService.createMessageTable(tableName,constraint);
		}catch (Exception e) {
			adminService.contentDelete(lastId);
			model.addAttribute("listError", "empty");
			return "error/error";
		}
		rttr.addFlashAttribute("enroll_result", contentVO.getCode());
		return "redirect:/admin/contentManage";
	} catch (Exception e) {
		model.addAttribute("listError", "empty");
		return "error/error";
	}
}

	/* 작가 상세, 수정 페이지 */
	@GetMapping({"/contentDetail"})
	public void contentGetInfoGET(int contentId, Criteria cri, Model model) throws Exception {

		logger.info("contentDetail......." + contentId);
		logger.info("contentDetail......." + cri);
		model.addAttribute("cri", cri);
		List<String> listSkinName = adminService.getPackageList(Constant.BASE_PACKAGE);
		if(!listSkinName.isEmpty()) {
			model.addAttribute("listSkinName",listSkinName);	// 작가 존재 경우
		} else {
			model.addAttribute("listSkinName", "empty");	// 작가 존재하지 않을 경우
		}
		ContentVO content = adminService.contentGetDetail(contentId);
		if (content != null) {
			model.addAttribute("contentVO", content);
		} else {
			model.addAttribute("contentVO", new ContentVO());
		}
	}
	@GetMapping({"/contentModify"})
	public String contentGetModify(int contentId, Criteria cri, Model model,RedirectAttributes rttr) throws Exception {

		logger.info("contentDetail......." + contentId);
		logger.info("contentDetail......." + cri);
		model.addAttribute("cri", cri);
		List<String> listSkinName = adminService.getPackageList(Constant.BASE_PACKAGE);
		if(!listSkinName.isEmpty()) {
			model.addAttribute("listSkinName",listSkinName);	// 작가 존재 경우
		} else {
			model.addAttribute("listSkinName", "empty");	// 작가 존재하지 않을 경우
		}
		ContentVO content = adminService.contentGetDetail(contentId);
		if (content != null) {
			model.addAttribute("contentVO", content);
			return "admin/contentModify";
		} else {
			rttr.addFlashAttribute("edit_result", false);
			return "redirect:/admin/contentManage";
		}
	}

	@PostMapping("/contentModify")
	public String contentModifyPOST(@Valid @ModelAttribute("contentVO") ContentVO contentVO, BindingResult bindingResult, RedirectAttributes rttr,Model model) throws Exception{

		logger.info("ContentModifyPOST......." + contentVO);
		List<String> listSkinName = adminService.getPackageList(Constant.BASE_PACKAGE);
		if(!listSkinName.isEmpty()) {
			model.addAttribute("listSkinName",listSkinName);
		} else {
			model.addAttribute("listSkinName", "empty");
		}
		int result = 0;
		try {
			if (bindingResult.hasErrors()) {
				return "admin/contentModify";
			}
			result = adminService.contentModify(contentVO);

			rttr.addFlashAttribute("modify_result", result);
			return "redirect:/admin/contentManage";
		} catch (Exception e) {
			rttr.addFlashAttribute("modify_result", result);
			return "redirect:/admin/contentManage";
		}
	}

	@PostMapping("/contentDelete")
	public String contentDeletePOST(int contentId, RedirectAttributes rttr) {

		logger.info("contentDeletePOST..........");

		int result;
		try {
			result = adminService.contentDelete(contentId);
			rttr.addFlashAttribute("delete_result", result);
			return "redirect:/admin/contentManage";

		} catch (Exception e) {
			result = 2;

			e.printStackTrace();
			rttr.addFlashAttribute("delete_result", result);

			return "redirect:/admin/contentManage";
		}
	}

	@RequestMapping(value = "messageManage", method = RequestMethod.GET)
	public void messageManageGET(Criteria cri, Model model) throws Exception{
		logger.info("メッセージ管理.........." + cri);
		/* 작가 목록 출력 데이터 */
		List list = adminService.contentGetList(cri);

		if(!list.isEmpty()) {
			model.addAttribute("list",list);
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.contentGetTotal(cri)));

	}

	@RequestMapping(value = "messageList", method = RequestMethod.GET)
	public void messageListGET(Criteria cri, Model model, MessageListRequest messListReq) throws Exception{
		logger.info("メッセージリスト.........." + cri);
		ContentVO content = adminService.contentGetDetail(messListReq.getContentsId());
		String category = content.getCategory();
		String contentCode = content.getCode();
		String tableName = contentCode +"_message";
		messListReq.setTableName(tableName);
		messListReq.setCategory(category);
		messListReq.setFileMaxCount(content.getFileMaxCount());
		messListReq.setFileMaxSize(content.getFileMaxSize());
		model.addAttribute("messListReq", messListReq);
		/* 작가 목록 출력 데이터 */
		List list = adminService.messageGetList(cri,tableName);

		if(!list.isEmpty()) {
			model.addAttribute("list",list);
		// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");
		}
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.messageGetTotal(tableName,cri)));
	}
	@RequestMapping(value = "messageEnroll", method = RequestMethod.GET)
	public void messageEnrollGET(@ModelAttribute("messageVO") MessageVO messageVO,
								 @ModelAttribute("messageFileVO") MessageFileVO messageFileVO,
								 @ModelAttribute("messageReq") AddMessageRequest messageReq,
								 Model model) throws Exception{
		logger.info("メッセージ情報入力");
		String category = messageReq.getCategoryContent();
		List<String> categoryList = splitString(category);
		if(!categoryList.isEmpty()) {
			model.addAttribute("categoryList",categoryList);	// 작가 존재 경우
			MessageCommonVO messCommonVO = new MessageCommonVO(messageVO,messageFileVO,messageReq);
			model.addAttribute("messCommonVO",messCommonVO);
			String[] fileExtensionList = Constant.FILE_EXTENSION_NOT_ALLOW;
			ObjectMapper mapper = new ObjectMapper();
			String fileExtensionListJson = mapper.writeValueAsString(fileExtensionList);
			model.addAttribute("fileExtensionListJson", fileExtensionListJson);
			System.out.println(messCommonVO);
		} else {
			model.addAttribute("categoryList", "empty");	// 작가 존재하지 않을 경우
		}
	}

	@Transactional
	@RequestMapping(value="messageEnroll.do", method = RequestMethod.POST)
	public String messageEnrollPOST(@Valid @ModelAttribute("messCommonVO") MessageCommonVO messCommonVO,
									BindingResult bindingResult,
									RedirectAttributes rttr,Model model) throws Exception{

		logger.info("messageEnroll :" +  messCommonVO);
		String category = messCommonVO.getMessageReq().getCategoryContent();
		List<String> categoryList = splitString(category);
		model.addAttribute("categoryList",categoryList);
		try {
			if (bindingResult.hasErrors()) {
				return "admin/messageEnroll";
			}
			adminService.addMessageTable(messCommonVO.getMessageVO(),messCommonVO.getMessageReq().getTableName());
			rttr.addFlashAttribute("enroll_result", messCommonVO.getMessageVO().getSubject());
			return "redirect:/admin/messageList?contentsId=" + messCommonVO.getMessageVO().getContentsId();
		} catch (Exception e) {
			return "error/error";
		}
	}
	@GetMapping({"/messageDetail"})
	public void messageGetInfoGET(int messageId, Criteria cri, Model model, String tableName) throws Exception {

		logger.info("messageDetail......." + messageId);
		logger.info("messageDetail......." + cri);
		String code = tableName.replace("_message", "");

		MessageVO messageVO = adminService.messageGetDetail(messageId,tableName);
		String category = messageVO.getCategory();
		List<String> categoryList = splitString(category);
		if(!categoryList.isEmpty()) {
			model.addAttribute("categoryList",categoryList);	// 작가 존재 경우
		} else {
			model.addAttribute("categoryList", "empty");	// 작가 존재하지 않을 경우
		}

		/* 작가 관리 페이지 정보 */
		model.addAttribute("cri", cri);
		model.addAttribute("code", code);
		model.addAttribute("tableName", tableName);
		/* 선택 작가 정보 */
		model.addAttribute("messageInfo", messageVO);

	}
	@GetMapping({"/messageModify"})
	public String messageGetModify(int messagesId, Criteria cri, Model model,RedirectAttributes rttr, String tableName) throws Exception {

		logger.info("MessageId......." + messagesId);
		logger.info("Criteria........" + cri);
		logger.info("tableName......." + tableName);
		model.addAttribute("cri", cri);
		MessageVO messageVO = adminService.messageGetDetail(messagesId,tableName);
		if (messageVO != null) {
			model.addAttribute("messageVO", messageVO);
			model.addAttribute("tableName", tableName);
			return "admin/messageModify";
		} else {
			rttr.addFlashAttribute("edit_result", false);
			return "redirect:/admin/messageList?contentsId=" + messageVO.getContentsId();
		}
	}
	@PostMapping("/messageModify")
	public String messageModify(@Valid @ModelAttribute("messageVO") MessageVO messageVO, BindingResult bindingResult, RedirectAttributes rttr, String tableName) throws Exception{

		logger.info("messageModify......." + messageVO);
		int result = 0;
		try {
			if (bindingResult.hasErrors()) {
				return "redirect:admin/messageModify";
			}
			result = adminService.messageModify(messageVO,tableName);
			rttr.addFlashAttribute("modify_result", result);
			return "redirect:/admin/messageList?contentsId=" + messageVO.getContentsId();
		} catch (Exception e) {
			rttr.addFlashAttribute("modify_result", result);
			return "redirect:/admin/messageList?contentsId=" + messageVO.getContentsId();
		}
	}

	private List<String> splitString(String input) {
		String[] elementsArray = input.split("\\|");
		List<String> elementsList = Arrays.asList(elementsArray);
		return elementsList;
	}

	@PostMapping("/uploadFile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes) {
//		System.out.println("Handle File Upload");
//		storageService.store(file);
//		redirectAttributes.addFlashAttribute("message",
//				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}



}


