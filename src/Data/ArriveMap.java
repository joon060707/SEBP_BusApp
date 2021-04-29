package Data;

public class ArriveMap {

    public String idData;
    public String nameData;
    public String where;
    public String remainMin;
    public String remainStop;

    public ArriveMap(String idData, String nameData, String where, String remainMin, String remainStop){
        this.idData=idData;
        this.nameData=nameData;
        this.remainMin=remainMin;
        this.remainStop=remainStop;
        this.where=where;
    }
}
/*      6, 지원15, 소태역, 6분, 6정거장
        8, 진월17, 남광주역, 3분, 1정거장
        14, 봉선27, 양림휴먼시아2차, 4분, 2정거장
        15, 일곡28, 모아2차아파트, 8분, 6정거장
        18, 운림35, 학동무등파크, 4분, 3정거장
        68, 송정98, 양림휴먼시아1차, 5분, 3정거장
        */