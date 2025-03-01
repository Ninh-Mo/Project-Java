package com.vam.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import com.vam.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vam.mapper.AdminMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	/* 상품 등록 */
	@Transactional
	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("(srevice)bookEnroll........");
		
		adminMapper.bookEnroll(book);
		
		if(book.getImageList() == null || book.getImageList().size() <= 0) {
			return;
		}
		
		book.getImageList().forEach(attach ->{
			
			attach.setBookId(book.getBookId());
			adminMapper.imageEnroll(attach);
			
		});

	}
	
	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList........");
		
		return adminMapper.cateList();
	}		
	
	/* 상품 리스트 */
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		log.info("goodsGetTotalList()..........");
		return adminMapper.goodsGetList(cri);
	}

	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri) {
		log.info("goodsGetTotal().........");
		return adminMapper.goodsGetTotal(cri);
	}	
	
	/* 상품 조회 페이지 */
	@Override
	public BookVO goodsGetDetail(int bookId) {
		
		log.info("(service)bookGetDetail......." + bookId);
		
		return adminMapper.goodsGetDetail(bookId);
	}		
	
	/* 상품 정보 수정 */
	@Transactional
	@Override
	public int goodsModify(BookVO vo) {

		int result = adminMapper.goodsModify(vo);
		
		if(result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
			
			adminMapper.deleteImageAll(vo.getBookId());
			
			vo.getImageList().forEach(attach -> {
				
				attach.setBookId(vo.getBookId());
				adminMapper.imageEnroll(attach);
				
			});
			
		}
		
		return result;
	}		
	
	/* 상품 정보 삭제 */
	@Override
	@Transactional	
	public int goodsDelete(int bookId) {

		log.info("goodsDelete..........");
		
		adminMapper.deleteImageAll(bookId);			
		
		return adminMapper.goodsDelete(bookId);
	}		
	
	/* 지정 상품 이미지 정보 얻기 */
	@Override
	public List<AttachImageVO> getAttachInfo(int bookId) {
		
		log.info("getAttachInfo........");
		
		return adminMapper.getAttachInfo(bookId);
	}		
	
	/* 주문 상품 리스트 */
	@Override
	public List<OrderDTO> getOrderList(Criteria cri) {
		return adminMapper.getOrderList(cri);
	}
	
	/* 주문 총 갯수 */
	@Override
	public int getOrderTotal(Criteria cri) {
		return adminMapper.getOrderTotal(cri);
	}


	@Override
	public List<ContentVO> contentGetList(Criteria cri) throws Exception {

		log.info("(service)contentGetList()......." + cri);

		return adminMapper.contentGetList(cri);
	}

	@Override
	public int contentGetTotal(Criteria cri) throws Exception {
		log.info("(service)contentGetTotal()......." + cri);
		return adminMapper.contentGetTotal(cri);
	}

	@Transactional
	@Override
	public void contentEnroll(ContentVO content) throws Exception {
		adminMapper.contentEnroll(content);

	}

	@Override
	public ContentVO contentGetDetail(int contentId) throws Exception {
		log.info("contentGetDetail........" + contentId);
		return adminMapper.contentGetDetail(contentId);
	}

	@Transactional
	@Override
	public int contentModify(ContentVO content) throws Exception {
		log.info("(service) contentModify........." + content);
		int result =  adminMapper.contentModify(content);
		return result;
	}

	@Transactional
	@Override
	public int contentDelete(int contentId) {
		int result = 0;
		log.info("Content Delete..........");
		try {
			ContentVO content = adminMapper.contentGetDetail(contentId);
			String tableName = content.getCode() + "_message";
			adminMapper.deleteMessageTable(tableName);
			result = adminMapper.contentDelete(contentId);
			return result;
		} catch (Exception e) {
			return result;
		}
	}

	@Transactional
	@Override
	public void createMessageTable(String tableName,String constraintName) throws Exception {
		log.info("insertMessageTable..........");
		adminMapper.createMessageTable(tableName,constraintName);

	}

	@Override
	public int getLastInsertedId() {
		return adminMapper.getLastInsertedId();
	}

	@Override
	public List<MessageVO> messageGetList(Criteria cri, String tableName) throws Exception {

		log.info("(service)messageGetList()......." + cri);

		return adminMapper.messageGetList(tableName, cri);
	}

	@Override
	public int messageGetTotal(String tableName,Criteria cri) throws Exception {
		log.info("(service)messageGetTotal()......." + cri);
		return adminMapper.messageGetTotal(tableName,cri);
	}

	@Transactional
	@Override
	public void addMessageTable(MessageVO message, String tableName) throws Exception {
		log.info("addMessageTable..........");
		adminMapper.addMessageTable(message,tableName);

	}

	@Override
	public MessageVO messageGetDetail(int messageId, String tableName) throws Exception {
		log.info("messageGetDetail........" + messageId);
		log.info("messageGetDetail........" + tableName);
		return adminMapper.messageGetDetail(messageId,tableName);
	}

	public List<String> getPackageList(String basePackage) throws IOException, ClassNotFoundException {
		// クラスローダを取得
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		// パッケージ配下のリソースを取得（複数の場合あり）
		Enumeration<URL> e = cl.getResources(basePackage.replace(".", "\\"));
		// 返却用のリストを宣言
		List<String> list = new ArrayList<>();

		// パッケージ配下のリソースの数だけループ
		for (; e.hasMoreElements() ;){
			URL url = e.nextElement();
			File dir = new File(url.getPath());
			for (String path : dir.list()) {
				list.add(basePackage + "." + path);
			}
		}
		List<String> packageList = new ArrayList<>();
		for (String item : list) {
			String checkString = ".class";
			if (!item.contains(checkString)) {
				packageList.add( item.substring(basePackage.length() + 1));
			}
		}

		return packageList;
	}



	@Override
	public int messageModify(MessageVO message, String tableName) throws Exception {
		log.info("(service) Modify........." + message);
		int result =  adminMapper.messageModify(message,tableName);
		return result;
	}

}

