package com.sh.service;

import com.sh.domain.Card;

import java.util.List;

public interface ICardService {
    List<Card> findByCondition(String conndition,String flag);
    Card findById(int id,String flag);
    int delete(int id);
    int insert(Card card);
    int update(Card card);
    int insertList(List<Card> cardList);
    int deleteList(int[] ids);
    int retrieve(int... ids);
    int revert(int... ids);
}
