package Parse;
/*

인터넷에서 원본 데이터(xml 파일)를 얻어오는 코드입니다.
이 코드는 공공데이터 API 상세정보의 샘플코드(Java)를 갖고 왔습니다.
코드 출처: https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15001102

"본 저작물은 광주광역시에서 2018년 작성하여 공공누리 제1유형으로 개방한 광주광역시 BIS 노선정보를 이용하였으며,
해당 저작물은 https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15001102에서 무료로 다운받으실 수 있습니다."
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Get {

    public static final String LINE="lineInfo";
    public static final String LINE_STATION="lineStationInfo";
    public static final String ARRIVE="arriveInfo";
    public static final String STATION="stationInfo";
    public static final String BUS_LOCATION="busLocationInfo";

    static final int NO_NEED=0;


    public String getString(String type, int number) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.gwangju.go.kr/xml/"+type); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("6NJ+9v8lLytvSPezq+1BfBxvNrXCxjoJBuGKqv0HCIC2JCguk1J7zsghyyfWnEZdXUsaVLsQFBMF6GPsYW4Wig==", StandardCharsets.UTF_8)); /**/

        if(type.equals(LINE_STATION) || type.equals(BUS_LOCATION))
            urlBuilder.append("&").append(URLEncoder.encode("LINE_ID", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(String.valueOf(number), StandardCharsets.UTF_8)); /*노선 ID*/
        else if(type.equals(ARRIVE))
            urlBuilder.append("&").append(URLEncoder.encode("BUSSTOP_ID", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(String.valueOf(number), StandardCharsets.UTF_8)); /*노선 ID*/


      //  System.out.println(urlBuilder);

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
      //  System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();

    }
}
