package eu.fivegmedia.aaa.service.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class UtilHttpClient {

	static OkHttpClient getUnsafeOkHttpClient() {
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
						}

						public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
						}

						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return new java.security.cert.X509Certificate[]{};
						}
					}
			};

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.connectTimeout(20, TimeUnit.SECONDS);
			builder.writeTimeout(20, TimeUnit.SECONDS);
			builder.readTimeout(50, TimeUnit.SECONDS);
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
			builder.hostnameVerifier(
					new HostnameVerifier() {
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					}
			);

			builder.cookieJar(new CookieJar() {
				private List<Cookie> storage = new ArrayList<Cookie>();

				public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
					storage.addAll(cookies);
				}

				public List<Cookie> loadForRequest(HttpUrl url) {

					// Remove expired Cookies
					//storage.removeIf(cookie -> cookie.expiresAt() < System.currentTimeMillis());

					// Only return matching Cookies
					return storage.stream().filter(cookie -> cookie.matches(url)).collect(Collectors.toList());
				}
			});

			OkHttpClient okHttpClient = builder.build();
			return okHttpClient;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
