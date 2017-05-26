package laiebei.terminal.plug.vo.bean;

/**
 * Created by Administrator on 2017/4/17.
 */
public class ReplaceBean {
    /**
     * id : 11111
     * name : 习酒大曲
     * quantity : 5
     * table : 5号桌
     * code : 123456
     */

    private int id;
    private String name;
    private boolean enable;
    private int quantity;
    private String table;
    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
