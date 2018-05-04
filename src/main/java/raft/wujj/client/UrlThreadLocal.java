package raft.wujj.client;

public class UrlThreadLocal {
	
	private static ThreadLocal<String> urlLocal = new ThreadLocal<String>();
	
	public static void stashUrl(String url) {
		urlLocal.set(url);
	}
	
	public static String getUrl() {
		return urlLocal.get();
	}

}
