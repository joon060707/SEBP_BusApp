package Parse;

import java.util.HashMap;
import java.util.Map;

public class StopList {

    public static HashMap<Integer,String[]> stopList;

    public static HashMap<Integer,String[]> getstoplist(){

        if(stopList!=null) return stopList;

        HashMap<Integer, String[]> stopData = new HashMap<>();

        try {

            String src = new Get().getString(Get.STATION, Get.NO_NEED);

            while (src.contains("<STATION NUM")) {
                String sub = src.substring(src.indexOf("<STATION NUM"), src.indexOf("</STATION>") + 10);
//                 System.out.println("\n\n"+sub+"\n\n");

                String idData = sub.substring(sub.indexOf("<BUSSTOP_ID>") + 12, sub.indexOf("</BUSSTOP_ID>"));
                String nameData = sub.substring(sub.indexOf("<BUSSTOP_NAME>") + 14, sub.indexOf("</BUSSTOP_NAME>"));
                String nextStop;
                if(sub.contains("<NEXT_BUSSTOP>"))
                    nextStop = sub.substring(sub.indexOf("<NEXT_BUSSTOP>") + 14, sub.indexOf("</NEXT_BUSSTOP>"))+" 방향";
                else nextStop = "다음 정류장 없음";

                stopData.put(Integer.parseInt(idData),new String[]{nameData, nextStop});

                src = src.substring(src.indexOf("<STATION NUM") + sub.length());
            }
        }catch (Exception e){
            System.out.println("정보 불러오기 오류");
        }

        stopList=stopData;
        return stopData;
    }


    public static void print(HashMap<Integer,String[]> stopList){
        System.out.println("정류장 ID: 정류장 이름(방향)");
        int count=0;
        for (Map.Entry<Integer, String[]> entry: stopList.entrySet()){
            count++;
            if(count%8!=0) System.out.print(entry.getKey() + ": " + entry.getValue()[0]+"("+entry.getValue()[1]+")\t");
            else System.out.println(entry.getKey() + ": " + entry.getValue()[0]+"("+entry.getValue()[1]+")");
        }

    }

    public static int findIdFromString(String name){

        int find=0;
        System.out.println("검색중입니다...");

        HashMap<Integer,String[]> stopList=getstoplist();

        for (Map.Entry<Integer, String[]> entry: stopList.entrySet()){
            if(entry.getValue()[0].contains(name)){
                find=entry.getKey();
                System.out.println(entry.getValue()[0]);
                break;
            }
        }

        return find;
    }

//    public static int findIdFromId(int id, HashMap<String,String> stopList){
//
//        String stopName= stopList.get(String.valueOf(id));
//        if(getstoplist().get(String.valueOf(id-1)).equals(stopName)) return id-1;
//        else return id+1;
//
//    }

}
