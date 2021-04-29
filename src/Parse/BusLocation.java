package Parse;

import Data.BusLocationMap;
import java.util.ArrayList;
import java.util.Map;


public class BusLocation {

    ArrayList<BusLocationMap> busData;

    public BusLocation(int lineid, BusLine l) {

        try {


            busData = new ArrayList<>();

            String src = new Get().getString(Get.BUS_LOCATION, lineid);
            // System.out.println(src);

            while (src.contains("<BUSLOCATION NUM")) {
                String sub = src.substring(src.indexOf("<BUSLOCATION NUM"), src.indexOf("</BUSLOCATION>") + 14);

                // System.out.println("\n\n"+sub+"\n\n");

                String idData = sub.substring(sub.indexOf("<LINE_ID>") + 9, sub.indexOf("</LINE_ID>"));
                String nameData = "노선 이름";

                String whereid = sub.substring(sub.indexOf("<CURR_STOP_ID>") + 14, sub.indexOf("</CURR_STOP_ID>"));
                String whereName = "정류장 이름";
                String busNo = sub.substring(sub.indexOf("<CARNO>") + 7, sub.indexOf("</CARNO>"));
                busData.add(new BusLocationMap(idData, nameData, whereid, whereName, busNo));

                src = src.substring(src.indexOf("<BUSLOCATION NUM") + sub.length());

            }

            this.matchName(l);
        }catch (Exception e){
            System.out.println("정보 불러오기 오류");
        }
    }

    private void matchName(BusLine busLine) {
        for (BusLocationMap busDatum : this.busData) {
            for (int i = 0; i < busLine.busData.size(); i++) {
                if (busLine.busData.get(i).idData.equals(busDatum.idData))
                    busDatum.nameData = busLine.busData.get(i).nameData;

                if (busLine.busData.get(i).stopId.equals(busDatum.whereid)) {
                    busDatum.whereName = busLine.busData.get(i).stopName;
                }
            }
        }
    }

    public void print(){
//        System.out.println(busData.get(0).nameData);
        System.out.println("정류장 이름: 버스 번호");
        for (BusLocationMap busLocationMap : busData)
            System.out.println(busLocationMap.whereName+": "+busLocationMap.busNo);

//        System.out.println(busLocationMap.idData+", "+busLocationMap.nameData+", "+busLocationMap.whereid+", "+busLocationMap.whereName+", "+busLocationMap.busNo);
    }





}
