package cn.dnui_dx602.dnuifood16110100602.bean;

public class FoodBean {

    /**
     * food_id : 1
     * foodname : 酸菜鱼
     * intro : 地道的川菜
     * pic : /images/food/bc.jpg
     * price : 23
     * shop_id : 1
     * type_id : 1
     * recommand : 1
     */

    private String food_id;
    private String foodname;
    private String intro;
    private String pic;
    private String price;
    private String shop_id;
    private String type_id;
    private String recommand;

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getRecommand() {
        return recommand;
    }

    public void setRecommand(String recommand) {
        this.recommand = recommand;
    }

    @Override
    public String toString() {
        return "FoodBean{" +
                "food_id='" + food_id + '\'' +
                ", foodname='" + foodname + '\'' +
                ", intro='" + intro + '\'' +
                ", pic='" + pic + '\'' +
                ", price='" + price + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", type_id='" + type_id + '\'' +
                ", recommand='" + recommand + '\'' +
                '}';
    }
}
