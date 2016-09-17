package com.github.q9029.memcached.main;

import java.util.ResourceBundle;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedTestMain {

	public static void main(String[] args) {

		try {
			System.out.println("start.");

			ResourceBundle props = ResourceBundle.getBundle("memcached");

			String propServer = props.getString("memcached.host");
			String propPort = props.getString("memcached.port");

			String server = propServer + ':' + propPort;
			System.out.println("connect to " + server);

			SockIOPool pool = SockIOPool.getInstance();

			pool.setServers(new String[]{server});
			pool.initialize();

			MemCachedClient mcc = new MemCachedClient();

			boolean addRes = mcc.add("1", "hoge");
			System.out.println("result add : " + addRes);

			Object getRes = mcc.get("1");
			System.out.println("result get : " + getRes);

			boolean delRes = mcc.delete("1");
			System.out.println("result delete : " + delRes);

			pool.shutDown();

			System.out.println("end.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
