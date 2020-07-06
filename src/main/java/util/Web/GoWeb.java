package util.Web;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class GoWeb extends Thread {
    public static String goPrsticideWeb() {
        //设置代理
        String proxy = "openproxy.huawei.com";
        int port = 8080;
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", Integer.toString(port));
        System.setProperty("proxyHost", proxy);
        System.setProperty("proxySet", "true");

//        String url = "http://www.chinapesticide.org.cn/myquery/querydetail?pdno=" + paramOne + "&pdrgno=" + paramTwo + "/";

//        String url = "https://www.baidu.com/";
        //String url="https://www.okex.com/api/futures/v3/instruments";
        String url ="https://www.youtube.com/";
        try {
            SSLContext sc = SSLContext.getInstance("SSL");

            //指定信任https
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            // 运行错误，好像只能用https
            // URL console = new URL(url);
            // 可用用http
            URL console= new URL(null, url, new sun.net.www.protocol.https.Handler());
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            System.out.println("返回结果：" + conn.getResponseMessage());

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String curLine = "";
            while ((curLine = reader.readLine()) != null) {
                System.out.println(curLine);
            }

            is.close();
            return conn.getResponseMessage();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}


