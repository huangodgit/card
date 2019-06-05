package com.sh.service.impl;

import com.sh.dao.impl.CardDao;
import com.sh.domain.Card;
import com.sh.service.ICardService;
import com.sh.service.ICardService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cardService")
@Getter@Setter
public class CardService implements ICardService {
    @Autowired
    private CardDao cardDao;
    @Override
    public List<Card> findByCondition(String conndition, String flag) {
        return cardDao.findByCondition(conndition,flag);
    }

    @Override
    public Card findById(int id, String flag) {
        return cardDao.findById(id,flag);
    }

    @Override
    public int delete(int id) {
        return cardDao.delete(id);
    }

    @Override
    public int insert(Card card) {
        cardDao.insert(card);
        return 1;
    }

    @Override
    public int update(Card card) {
        return cardDao.update(card);
    }

    @Override
    public int insertList(List<Card> cardList) {
        return cardDao.insertList(cardList);
    }

    @Override
    public int deleteList(int[] ids) {
        return cardDao.deleteList(ids);
    }

    @Override
    public int retrieve(int... ids) {
        return cardDao.retrieve(ids);
    }

    @Override
    public int revert(int... ids) {
        return cardDao.revert(ids);
    }
}
