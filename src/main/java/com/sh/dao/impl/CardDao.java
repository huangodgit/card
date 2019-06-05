package com.sh.dao.impl;

import com.sh.dao.DaoHibernate;
import com.sh.domain.Card;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CardDao extends DaoHibernate<Card> {
    public List<Card> findByCondition(String condition, String flag) {
        String hql = "from Card";
        String cardFiles[] = {"name", "sex", "department", "mobile", "phone", "email", "address"};
        List<Card> cards1 = this.findByFields(hql, cardFiles, condition);
        List<Card> cards2 = new ArrayList<>();
        for (Card card : cards1) {
            if (card.getFlag().equals(flag)) {
                cards2.add(card);
            }

        }
        return cards2;
    }

    public Card findById(int id, String flag){
        Card card = findById(Card.class,id);
        if (card.getFlag().equals(flag)){
            return card;
        }else {
            return null;
        }
    }

    public int delete(int id){
        return this.delete(Card.class,id);
    }

    public int deleteList(int[] ids){
        return this.deleteList(Card.class,ids);
    }

    public int retrieve(int... ids){
        for (int id :ids) {
            Card card = this.findById(Card.class,id);
            card.setFlag("1");
            this.update(card);
        }
        return ids.length;
    }

    public int revert(int... ids){
        for (int id :ids) {
            Card card = this.findById(Card.class,id);
            card.setFlag("0");
            this.update(card);
        }
        return ids.length;
    }
}
