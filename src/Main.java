import Parse.*;
import java.util.Scanner;

public class Main {

    static Arrive a;
    static BusLine l, li;
    static BusLocation o;

    public static void main(String[] args){

        Scanner scanner=new Scanner(System.in);
        busCmd(scanner);

    }

    static void busCmd(Scanner scanner){

        System.out.println("광주 버스 정보");
        System.out.println("숫자를 입력하세요.\n0: 정류장 목록\n1: 시내버스 목록\n2: 버스 도착 정보\n21: 버스 도착 정보(ID로)\n3: 노선 정류장 목록\n4: 노선 버스 위치\nq: 종료");

        String st=scanner.next();
        switch (st){
            case "0": {
                StopList.print(StopList.getstoplist());
                busCmd(scanner);
                break;
            }
            case "1": {
                BusList.print(BusList.getbuslist());
                busCmd(scanner);
                break;
            }
            case "2": {
                // Arrive: 특정 정류장의 도착 정보
                System.out.println("버스 도착 정보: 정류장 이름을 정확히 입력하세요.");
                int id=StopList.findIdFromString(scanner.next());
                if(id==0) System.out.println("정류장을 찾지 못했습니다.");
                else {
                    System.out.println("방향 1");
                    a=new Arrive(id);
                    a.print();
                }

                busCmd(scanner);
                break;
            }
            case "21": {
                // Arrive: 특정 정류장의 도착 정보
                System.out.println("버스 도착 정보: 정류장 ID를 정확히 입력하세요.");
                a=new Arrive(scanner.nextInt());
                a.print();
                busCmd(scanner);
                break;
            }
            case "3": {
                // BusLine: 특정 노선의 정류장 목록
                System.out.println("노선 정류장 목록: 노선 이름을 정확히 입력하세요.");
                int id=BusLine.findIdFromString(scanner.next());
                if(id==0) System.out.println("노선을 찾지 못했습니다.");
                else {
                    l=new BusLine(id);
                    l.print();
                }
                busCmd(scanner);
                break;
            }
            case "4": {
                // BusLocation: 특정 노선의 버스들의 위치
                System.out.println("노선 버스 위치: 노선 이름을 정확히 입력하세요.");

                int id=BusLine.findIdFromString(scanner.next());
                if(id==0) System.out.println("노선을 찾지 못했습니다.");
                else{
                    li=new BusLine(id);
                    o=new BusLocation(id, li);
                    o.print();
                }
                busCmd(scanner);
                break;
            }
            case "q": {
                break;
            }
            default:{
                System.out.println("잘못된 명령어입니다.\n");
                busCmd(scanner);
                break;
            }
        }

    }
}
