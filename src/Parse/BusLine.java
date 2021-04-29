package Parse;

import Data.BusLineMap;
import java.util.ArrayList;
import java.util.Map;


public class BusLine {

    ArrayList<BusLineMap> busData;

    public BusLine(int lineid) {

        try {

            busData = new ArrayList<>();

            String src = new Get().getString(Get.LINE_STATION, lineid);
            // System.out.println(src);

            while (src.contains("<BUSSTOP NUM")) {
                String sub = src.substring(src.indexOf("<BUSSTOP NUM"), src.indexOf("</BUSSTOP>") + 10);

                //System.out.println("\n\n"+sub+"\n\n");

                String idData = sub.substring(sub.indexOf("<LINE_ID>") + 9, sub.indexOf("</LINE_ID>"));
                String nameData = sub.substring(sub.indexOf("<LINE_NAME>") + 11, sub.indexOf("</LINE_NAME>"));
                nameData = nameData.replace("-$gt;", "->");
                String stopId = sub.substring(sub.indexOf("<BUSSTOP_ID>") + 12, sub.indexOf("</BUSSTOP_ID>"));
                String stopName = sub.substring(sub.indexOf("<BUSSTOP_NAME>") + 14, sub.indexOf("</BUSSTOP_NAME>"));
                busData.add(new BusLineMap(idData, nameData, stopId, stopName));

                src = src.substring(src.indexOf("<BUSSTOP NUM") + sub.length());

            }
        }catch (Exception e){
            System.out.println("정보 불러오기 오류");
        }
    }

        public void print(){
            int count=0;
            System.out.println(busData.get(0).nameData);
            System.out.println(busData.size()+"개 정류장");
            for (BusLineMap busLineMap : busData){
                count++;
                if(count==busData.size()) System.out.println(busLineMap.stopName+"\n");
                else if(count%5!=0) System.out.print(busLineMap.stopName+" -> ");
                else System.out.println(busLineMap.stopName+" -> ");
            }
//            System.out.println(busLineMap.idData+", "+busLineMap.nameData+", "+busLineMap.stopId+", "+busLineMap.stopName);
        }


    public static int findIdFromString(String name){

        int find=0;

        for (Map.Entry<Integer, String> entry: BusList.getbuslist().entrySet()){
            if(entry.getValue().contains(name)){
                find=entry.getKey();
                System.out.println(entry.getValue());
                break;
            }

        }
        return find;
    }

}
