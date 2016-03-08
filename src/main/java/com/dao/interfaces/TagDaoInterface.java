package com.dao.interfaces;

import com.models.Tag;
import java.util.List;

public interface TagDaoInterface {
    
    public Tag findTag(String name);
    public boolean existTag(String name);
    public List<Tag> getAllTagsAsc(); 
}
