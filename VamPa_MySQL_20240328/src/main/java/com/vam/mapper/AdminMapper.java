package com.vam.mapper;

import java.util.List;

import com.vam.model.*;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

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
	
	/* 이미지 등록 */
	public void imageEnroll(AttachImageVO vo);	
	
	/* 지정 상품 이미지 전체 삭제 */
	public void deleteImageAll(int bookId);	
	
	/* 어제자 날짜 이미지 리스트 */
	public List<AttachImageVO> checkFileList();	
	
	/* 지정 상품 이미지 정보 얻기 */
	public List<AttachImageVO> getAttachInfo(int bookId);	
	
	/* 주문 상품 리스트 */
	public List<OrderDTO> getOrderList(Criteria cri);	
	
	/* 주문 총 갯수 */
	public int getOrderTotal(Criteria cri);

	public List<ContentVO> contentList();

	public List<ContentVO> contentGetList(Criteria cri);

	public int contentGetTotal(Criteria cri);

	public void contentEnroll(ContentVO content);

	public ContentVO contentGetDetail(int contentId);

	public int contentModify(ContentVO content);

	/* 작가 정보 삭제 */
	public int contentDelete(int contentId);

	public int getLastInsertedId();

	public void createMessageTable(@Param("tableName") String tableName,@Param("constraintName") String constraintName);

	public List<MessageVO> messageGetList(@Param("tableName") String tableName,@Param("cri") Criteria cri);

	public int messageGetTotal(@Param("tableName") String tableName,@Param("cri") Criteria cri);

	public void addMessageTable(@Param("message") MessageVO message,@Param("tableName") String tableName);

	public MessageVO messageGetDetail(@Param("messageId")int messageId, @Param("tableName") String tableName);

	public int deleteMessageTable(String tableName);

	public int messageModify(@Param("message") MessageVO message,@Param("tableName") String tableName);

}
