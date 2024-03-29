package com.example.mybatis.repository;

import com.example.mybatis.domain.Board;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BoardDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public BoardDao(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Board> getList(){
        return sqlSessionTemplate.selectList("board.getList");
    }

    public int insertBoard(String title, String content, String writer) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("title",title);
        param.put("content",content);
        param.put("writer",writer);
        return sqlSessionTemplate.insert("board.insertBoard", param);
    }

    public Board detail(int id) {
        return sqlSessionTemplate.selectOne("board.detail",id);
    }

    public int delete(Long id) {
        return sqlSessionTemplate.delete("board.delete",id);
    }
}
