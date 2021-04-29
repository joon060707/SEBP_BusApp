package Data;

public class BusLocationMap {

    public String idData;
    public String nameData;
    public String whereid;
    public String whereName;
    public String busNo;

    public BusLocationMap(String idData, String nameData, String whereid, String whereName, String busNo){
        this.idData=idData;
        this.nameData=nameData;
        this.whereid=whereid;
        this.whereName=whereName;
        this.busNo=busNo;
    }

    void setStop(String st){
        whereName=st;
    }

    void setName(String st){
       nameData=st;
    }
}
/*
        9, 노선 이름, 539, 노선 이름, 광주77바2266
        9, 노선 이름, 1290, 노선 이름, 광주77바2025
        9, 노선 이름, 2990, 노선 이름, 광주77바2265
        9, 노선 이름, 498, 노선 이름, 광주77바2236
        9, 노선 이름, 3846, 노선 이름, 광주77바2026
        9, 노선 이름, 2628, 노선 이름, 광주77바2231
        9, 노선 이름, 1053, 노선 이름, 광주77바2230
        9, 노선 이름, 2895, 노선 이름, 광주77바2450
        9, 노선 이름, 1008, 노선 이름, 광주77바2224
        9, 노선 이름, 85, 노선 이름, 광주77바2179

        9, 문흥18, 84, 광주병원, 광주77바2266
        9, 문흥18, 867, 신안교, 광주77바2025
        9, 문흥18, 141, 동림I.C입구, 광주77바2265
        9, 문흥18, 1389, 산정중입구, 광주77바2236
        9, 문흥18, 4235, 광주그린카진흥원입구, 광주77바2026
        9, 문흥18, 3847, 다사로움1차아파트, 광주77바2231
        9, 문흥18, 2894, 동천마을1단지, 광주77바2230
        9, 문흥18, 2847, 운암시장, 광주77바2450
        9, 문흥18, 908, 전남대공과대학, 광주77바2224
        9, 문흥18, 538, 문흥1동행정복지센터, 광주77바2179

*/
