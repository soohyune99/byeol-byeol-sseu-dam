package com.app.byeolbyeolsseudam.service.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverService {

    public String getNaverAccessToken(String code){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://nid.naver.com/oauth2.0/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=yJSbjUG5YR4ZM9iBD1wE"); // TODO REST_API_KEY 입력
            sb.append("&client_secret=rDVn5KQxa2"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public MemberDTO naverProfile(String token) {
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        MemberDTO memberDTO = new MemberDTO();
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            JsonObject naverAccount = element.getAsJsonObject().get("response").getAsJsonObject();

            log.info("찐 엘리먼트 : " + element);

            String id = element.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();
            String memberName = naverAccount.get("name").getAsString();
//            String memberPhone = naverAccount.get("mobile").getAsString().replaceAll("-","");
            String memberProfile = naverAccount.get("profile_image").getAsString();

            log.info("id : " + id);
            log.info("name : " + memberName);
            log.info("profile_image : " + memberProfile);

            memberDTO.setMemberLoginType(MemberLoginType.NAVER);
            memberDTO.setMemberCategory(MemberCategory.일반회원);
            memberDTO.setMemberEmail(id);
            memberDTO.setMemberName(memberName);
            memberDTO.setMemberProfileName(memberProfile);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return memberDTO;
    }

    //  id
    public String getNaverIdByToken(String token) throws Exception{
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String naverId = null;
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            naverId = element.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();

            return naverId;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}
