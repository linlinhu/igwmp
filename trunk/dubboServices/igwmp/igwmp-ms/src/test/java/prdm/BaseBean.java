package prdm;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * user : shimingliang
 * date : 2017/3/17
 * time : 18:00
 * des :
 */
public class BaseBean implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
