package mk.ukim.finki;
import java.util.List;

class Item {
    String name;
    String barcode; //numerical
    int price;
    float discount;

    public Item(String name, String code, int price, float discount) {
        this.name = name;
        this.barcode = code;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBarcode(String code) {
        this.barcode = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment){    //1
        if (allItems == null){                                             //2
            throw new RuntimeException("allItems list can't be null!");    //3
        }

        float sum = 0;  //4

        for (int i = 0; i < allItems.size(); i++){  //5.1,  5.2
            Item item = allItems.get(i);  //6
            if (item.getName() == null || item.getName().length() == 0){  //7
                item.setName("unknown");  //8
            }
            if (item.getBarcode() != null){   //9
                String allowed = "0123456789"; //10
                char chars[] = item.getBarcode().toCharArray(); //11
                for (int j = 0; j < item.getBarcode().length(); j++){  //12.1 , 12.2, 12.3
                    char c = item.getBarcode().charAt(j);  //13
                    if (allowed.indexOf(c) == -1){     //14, true=15 false=12.3(j++)
                        throw new RuntimeException("Invalid character in item barcode!"); //15 exit
                    }

                }
                if (item.getDiscount() > 0){ //16
                    sum += item.getPrice()*item.getDiscount(); //17  kje se odi 22
                }
                else { //18
                    sum += item.getPrice(); //19  kje se odi 22
                }
            }
            else { //20
                throw new RuntimeException("No barcode!"); //21  exit
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){ //22
                sum -= 30; //23
            }
        }
        if (sum <= payment){ //24
            return true; // 25
        }
        else { //26
            return false; //27
        }
    }



}