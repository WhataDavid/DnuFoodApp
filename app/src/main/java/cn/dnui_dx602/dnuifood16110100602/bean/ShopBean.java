package cn.dnui_dx602.dnuifood16110100602.bean;

public class ShopBean {

    /**
     * shop_id : 1
     * shopname : 满口香川菜
     * address : 东软食堂三期
     * phonenum : 12345678901
     * intro : 地道的川菜
     * pic : /images/shop/chuaicai.jpg
     * comment : null
     * level : 5
     */

    private int shop_id;
    private String shopname;
    private String address;
    private String phonenum;
    private String intro;
    private String pic;
    private Object comment;
    private int level;

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
