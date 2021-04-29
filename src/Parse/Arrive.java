package Parse;

import Data.ArriveMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Parse.StopList.getstoplist;

public class Arrive {

    ArrayList<ArriveMap> busData;

    int stopId;


    public Arrive(int stopId) {

        this.stopId=stopId;

        try {

            busData = new ArrayList<>();

            String src = new Get().getString(Get.ARRIVE, stopId);
            // System.out.println(src);

            while (src.contains("<ARRIVE>")) {
                String sub = src.substring(src.indexOf("<ARRIVE>"), src.indexOf("</ARRIVE>") + 9);

                // System.out.println("\n\n"+sub+"\n\n");

                String idData = sub.substring(sub.indexOf("<LINE_ID>") + 9, sub.indexOf("</LINE_ID>"));
                String nameData = sub.substring(sub.indexOf("<LINE_NAME>") + 11, sub.indexOf("</LINE_NAME>"));
                nameData = nameData.replace("-&gt;", "->");
                String where = sub.substring(sub.indexOf("<BUSSTOP_NAME>") + 14, sub.indexOf("</BUSSTOP_NAME>"));
                String remainMin = sub.substring(sub.indexOf("<REMAIN_MIN>") + 12, sub.indexOf("</REMAIN_MIN>"));
                String remainStop = sub.substring(sub.indexOf("<REMAIN_STOP>") + 13, sub.indexOf("</REMAIN_STOP>"));
                busData.add(new ArriveMap(idData, nameData, where, remainMin, remainStop));

                src = src.substring(src.indexOf("<ARRIVE>") + sub.length());

            }

        }catch (Exception e){
            System.out.println("정보 불러오기 오류");
        }
    }

        public void print(){
            System.out.println("검색중입니다...");

            HashMap<Integer, String[]> StopList = getstoplist();
            for(Map.Entry<Integer, String[]> entry: StopList.entrySet()){
                if(entry.getKey().equals(stopId)) System.out.println(entry.getValue()[0]+"("+entry.getValue()[1]+")");
            }

            for (ArriveMap arriveMap : busData)
                System.out.println(arriveMap.idData+", "+arriveMap.nameData+", "+arriveMap.where+", "+arriveMap.remainMin+"분, "+arriveMap.remainStop+"정거장");
        }







}
