package com.vam.service;

import java.io.IOException;
import java.util.List;

import com.vam.model.*;
import org.springframework.transaction.annotation.Transactional;

public interface AdminService {

	/* 상품 등록 */
	public void bookEnroll(BookVO book);		
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();	
	
	/* 상품 리스트 */
	public List<BookVO> goodsGetList(Criteria cri);
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri);	
	
	/* 상품 조회 페이지 */
	public BookVO goodsGetDetail(int bookId);		

	/* 상품 수정 */
	public int goodsModify(BookVO vo);	
	
	/* 상품 정보 삭제 */
	public int goodsDelete(int bookId);	
	
	/* 지정 상품 이미지 정보 얻기 */
	public List<AttachImageVO> getAttachInfo(int bookId);	
	
	/* 주문 상품 리스트 */
	public List<OrderDTO> getOrderList(Criteria cri);
	
	/* 주문 총 갯수 */
	public int getOrderTotal(Criteria cri);

    public List<ContentVO> contentGetList(Criteria cri) throws Exception;

	public int contentGetTotal(Criteria cri) throws Exception;

	@Transactional
	public void contentEnroll(ContentVO content) throws Exception;

	public int getLastInsertedId();

	public ContentVO contentGetDetail(int contentId) throws Exception;

	public int contentModify(ContentVO content) throws Exception;

	public int contentDelete(int contentId);

	public List<MessageVO> messageGetList(Criteria cri, String tableName) throws Exception;

	public int messageGetTotal(String tableName,Criteria cri) throws Exception;

	public void createMessageTable(String tableName,String constraint) throws Exception;
	public void addMessageTable(MessageVO message, String tableName) throws Exception;

	public MessageVO messageGetDetail(int messageId, String tableName) throws Exception;

	public int messageModify(MessageVO message, String tableName) throws Exception;

	public List<String> getPackageList(String basePackage) throws IOException, ClassNotFoundException;
}
