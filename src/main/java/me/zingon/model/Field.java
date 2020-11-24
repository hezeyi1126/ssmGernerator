package me.zingon.model;

import me.zingon.backup.util.MyUtil;

/**
 * Created by page on 2017/6/12.
 */
public class Field {
    String field;
    String field_;
    String type;
    String collation;
    String nvll;
    String key;
    String defaults;
    String extra;
    String privileges;
    String comment;
    
    String frontType;//前端类型
    String isnull;//是否必填
    String issearch;//是否查询条件
    
    /**
     *前端属性
     */
    private FrontAttr frontattr;

    //驼峰式
    public String getField() {
        return MyUtil.a_b2aB(field);
    }

    //下划线式
    public String getField_() {
        return field_;
    }

    public Field setField(String field) {
        this.field = field;
        this.field_ = field;
        return this;
    }

    public String getType() {
        if(type.contains("int"))
            return "BigDecimal";
        if(type.contains("bit"))
            return "Boolean";
        if(type.contains("enum"))
            return "String";
        if(type.contains("char"))
            return "String";
        if(type.contains("text"))
        	return "String";
        if(type.contains("date")||type.contains("time"))
            return "Date";
        if(type.contains("float")||type.contains("double")||type.contains("decimal"))
            return "BigDecimal";
        return type;
    }
    
    public String getTypeO() {
        return type;
  }
    
    public String getTypeFront() {
    	  if(type.contains("int"))
              return "input";
          if(type.contains("bit"))
              return "checkbox";
          if(type.contains("enum"))
              return "select";
          if(type.contains("char"))
              return "input";
          if(type.contains("longtext"))
          	return "textarea";
          if(type.contains("date")||type.contains("time"))
              return "date";
          if(type.contains("float")||type.contains("double")||type.contains("decimal"))
              return "input";
          return type;
    }
    
    
    

    public FrontAttr getFrontattr() {
		return frontattr;
	}

	public void setFrontattr(FrontAttr frontattr) {
		this.frontattr = frontattr;
	}

	public Field setType(String type) {
        this.type = type;
        return this;
    }

    public String getCollation() {
        return collation;
    }

    public Field setCollation(String collation) {
        this.collation = collation;
        return this;
    }

    public String getNvll() {
        return nvll;
    }

    public Field setNvll(String nvll) {
        this.nvll = nvll;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Field setKey(String key) {
        this.key = key;
        return this;
    }

    public String getDefaults() {
        return defaults;
    }

    public Field setDefaults(String defaults) {
        this.defaults = defaults;
        return this;
    }

    public String getExtra() {
        return extra;
    }

    public Field setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public String getPrivileges() {
        return privileges;
    }

    public Field setPrivileges(String privileges) {
        this.privileges = privileges;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Field setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String toString() {
        return field;
    }

	public String getFrontType() {
		return frontType;
	}

	public void setFrontType(String frontType) {
		this.frontType = frontType;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public String getIssearch() {
		return issearch;
	}

	public void setIssearch(String issearch) {
		this.issearch = issearch;
	}
    
    
}
