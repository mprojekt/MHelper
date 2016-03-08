package com.dao.impl;

import com.dao.interfaces.CommentDaoInterface;
import com.models.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao extends CRUD<Comment> implements CommentDaoInterface{

}
