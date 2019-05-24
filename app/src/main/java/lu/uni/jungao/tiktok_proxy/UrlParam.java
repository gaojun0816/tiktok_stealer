package lu.uni.jungao.tiktok_proxy;

import android.util.Pair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class UrlParam {
    private List<Pair<String, String>> params = new LinkedList<>();

    public UrlParam() {
        params.add(new Pair<>("app_language", "en"));
        params.add(new Pair<>("language", "en"));
        params.add(new Pair<>("region", "US"));
        params.add(new Pair<>("sys_region", "US"));
        params.add(new Pair<>("carrier_region", ""));
//        params.add(new Pair<>("carrier_region_v2", ""));
        params.add(new Pair<>("build_number", "7.3.0"));
        params.add(new Pair<>("timezone_offset", "3600"));
        params.add(new Pair<>("timezone_name", "Europe/Luxembourg"));
        params.add(new Pair<>("mcc_mnc", ""));
        params.add(new Pair<>("is_my_cn", "0"));
        params.add(new Pair<>("fp", "HSTrFrZIJYsZFlZ7JlU1LSFIJ2me")); // device fingerprint
//        params.add(new Pair<>("account_region", ""));
//        params.add(new Pair<>("pass-region", ""));
//        params.add(new Pair<>("pass-route", ""));
        params.add(new Pair<>("iid", "6640883823842199301")); // installation ID
        params.add(new Pair<>("device_id", "6635694014538450434"));
        params.add(new Pair<>("ac", "wifi"));
        params.add(new Pair<>("channel", "googleplay"));
        params.add(new Pair<>("aid", "1233"));
        params.add(new Pair<>("app_name", "musical_ly"));
        params.add(new Pair<>("version_code", "730"));
        params.add(new Pair<>("version_name", "7.3.0"));
        params.add(new Pair<>("device_platform", "android"));
        params.add(new Pair<>("ssmix", "a"));
        params.add(new Pair<>("device_type", "Nexus 5X"));
        params.add(new Pair<>("device_brand", "google"));
        params.add(new Pair<>("os_api", "27"));
        params.add(new Pair<>("os_version", "8.1.0"));
        params.add(new Pair<>("openudid", "9525ed244e85528a")); // a device related ID
        params.add(new Pair<>("manifest_version_code", "2018060103"));
        params.add(new Pair<>("resolution", "1080*1794"));
        params.add(new Pair<>("dpi", "420"));
        params.add(new Pair<>("update_version_code", "2018060103"));
        params.add(new Pair<>("_rticket", String.valueOf(getTimestamp(true))));
        params.add(new Pair<>("ts", String.valueOf(getTimestamp(false))));
    }

    private long getTimestamp(boolean longFormat) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (longFormat) {
            return timestamp.getTime();
        } else {
            return (long) Math.floor(timestamp.getTime() / 1000);
        }
    }

    public void addParam(String key, String value) {
        params.add(new Pair<>(key, value));
    }

    public void addParam(String key, String value, int position) {
        params.add(position, new Pair<>(key, value));
    }

    public String getQueryParams() {
        StringBuilder result = new StringBuilder();
        for (Pair<String, String> p : params) {
            result.append("&");
            result.append(p.first);
            result.append("=");
            String encoded = null;
            try {
                encoded = URLEncoder.encode(p.second, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            result.append(encoded);
        }
        result.deleteCharAt(0);
        return result.toString();
    }
}
