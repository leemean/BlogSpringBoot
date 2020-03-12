package com.liming.common.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Serializable {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

    @Override
    public boolean equals(Object obj){
        if(null == obj){
            return false;
        }

        if(this == obj){
            return true;
        }

        if(!getClass().equals(obj.getClass())){
            return false;
        }

        BaseEntity that = (BaseEntity)obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode(){
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }
}
