package Parse;

import java.util.HashMap;
import java.util.Map;

public class BusList {

    public static HashMap<Integer,String> busList;

    public static HashMap<Integer,String> getbuslist(){

        if(busList!=null) return busList;

        HashMap<Integer, String> busData = new HashMap<>();

        try {

            String src = new Get().getString(Get.LINE, Get.NO_NEED);

            while (src.contains("<LINE")) {
                String sub = src.substring(src.indexOf("<LINE NUM"), src.indexOf("</LINE>") + 7);
                // System.out.println("\n\n"+sub+"\n\n");

                String idData = sub.substring(sub.indexOf("<LINE_ID>") + 9, sub.indexOf("</LINE_ID>"));
                String nameData = sub.substring(sub.indexOf("<LINE_NAME>") + 11, sub.indexOf("</LINE_NAME>"));
                nameData = nameData.replace("-$gt;", "->");

                busData.put(Integer.parseInt(idData),nameData);

                src = src.substring(src.indexOf("<LINE NUM") + sub.length());
            }
        }catch (Exception e){
            System.out.println("정보 불러오기 오류");
        }
        busList=busData;
        return busData;
    }


    public static void print(HashMap<Integer,String> buslist){
        System.out.println("버스 ID: 노선 이름");
        int count=0;
        for (Map.Entry<Integer, String> entry: buslist.entrySet()){
            count++;
            if(count%8!=0) System.out.print(entry.getKey() + ": " + entry.getValue()+ "\t");
            else System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }





}
