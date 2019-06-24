package lu.uni.jungao.plagiarist;

import android.net.Uri;

import com.ss.android.common.applog.UserInfo;
import com.ss.sys.ces.a;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class URLMaker {
    private static final String API_URL_BASE = "https://api2.musical.ly";
//    private static final String API_URL_BASE = "https://api2-19.musical.ly";
//    private static final String API_URL_BASE = "https://api.tiktokv.com";
//    private static final String USER_AGENT = "com.zhiliaoapp.musically/2018060103 (Linux; U; Android 8.1.0; en_US; Nexus 5X; Build/OPM3.171019.013; Cronet/58.0.2991.0)";

    public URLMaker() {
    }

    public static String getPostURL(String user_id, String max_cursor, int count) {
        String url = API_URL_BASE + "/aweme/v1/aweme/post/";
        UrlParam params = new UrlParam();
        params.addParam("user_id", user_id, 0);
        params.addParam("max_cursor", max_cursor, 1);
        params.addParam("count", String.valueOf(count), 2);
        params.addParam("retry_type", "no_retry", 3);
        url += "?" + params.getQueryParams();
        String signing = sign(url);
//        String signing = "";
        return url + signing;
    }

    public static String getSearchUsersURL(String keyword, String cursor, int count) {
        String url = API_URL_BASE + "/aweme/v1/discover/search/";
        UrlParam params = new UrlParam();
        params.addParam("keyword", keyword, 0);
        params.addParam("type", "1", 1);
        params.addParam("cursor", cursor, 2);
        params.addParam("count", String.valueOf(count), 3);
        params.addParam("retry_type", "no_retry", 4);
        url += "?" + params.getQueryParams();
        String signing = sign(url);
        return url + signing;
    }

    private static String sign(String url) {
        Uri uri = Uri.parse(url);
        Set<String> args = uri.getQueryParameterNames();
        List<String> arr = new ArrayList<>();
        int aid = 1180;
        int ts = 0;
        for (String k : args) {
            String v = uri.getQueryParameter(k);
            if (k.equals("ts")) {
                ts = Integer.parseInt(v);
            }
            if (k.equals("aid")) {
                aid = Integer.parseInt(v);
            }
            arr.add(k);
            arr.add(v);
        }
        Invoker invoker = Invoker.getInstance();
        UserInfo.setAppId(aid);
        UserInfo.initUser("a3668f0afac72ca3f6c1697d29e0e1bb1fef4ab0285319b95ac39fa42c38d05f");
        String str22 = UserInfo.getUserInfo(ts, url, arr.toArray(new String[0]));
        int i2 = str22.length();
        String substring = str22.substring(0, i2 >> 1);
        String cp = str22.substring(i2 >> 1, i2);
        String m = invoker.byteArrayToHexStr(a.e(substring.getBytes()));
        return "&as=" + substring + "&cp=" + cp + "&mas=" + m;
    }
}
