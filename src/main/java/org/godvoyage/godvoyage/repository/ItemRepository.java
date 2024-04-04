package org.godvoyage.godvoyage.repository;

import org.godvoyage.godvoyage.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //상품의 이름으로 조회하는 쿼리메소드 추가
    List<Item> findByItemNm(String itemNm);

    //or 조건으로 처리하기
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    //LessThan조건 : 매개변수보다 작은 값 조회
    List<Item> findByPriceLessThan(Integer price);

    //정렬 OrderBy필드명
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //쿼리어노테이션 활용하여 조회
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    //위의 쿼리 어노테이션과 같지만 sql문 작성해서 조회할 때 사용. 테이블명 이름 작성시 _사용 주의!
    @Query(value = "select * from Item i where i.item_detail like %:itemDetail% order " +
            "by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

    @Query("select i, m from Item i left outer join ItemImg m " +
            "on m.item= i where m.repimgYn= 'Y'")
    Page<Object[]> getListPage(Pageable pageable);

    //상품리스트 키워드 검색조회
    @Query("select i, m from Item i left outer join ItemImg m " +
            "on m.item= i where m.repimgYn= 'Y' and i.itemNm like %:keyword%")
    Page<Object[]> getList(Pageable pageable, @Param("keyword") String keyword);

    //상품리스트 키워드 검색조회
    @Query("select i,m from Item i left outer join ItemImg m " +
            "on m.item = i where m.repimgYn = 'Y' and i.itemNm like %:keyword%")
    Page<Object[]> getList(Pageable pageable, @Param("keyword") String keyword);

}
