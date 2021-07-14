//package homework.week2;
// 
//import cn.yuebei.game.core.common.email.Base64;
//
//import com.alibaba.fastjson.JSON;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
// 
//
//
//
//
//
//
//
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URI;
//import java.nio.charset.Charset;
//import java.util.*;
//import java.util.Map.Entry;
// 
//public class HttpClientUtil {
//	
//	protected final Log LOG = LogFactory.getLog(HttpClientUtil.class);
//	private static HttpClientUtil instance;
//	protected Charset charset;
//	
//	private HttpClientUtil(){}
//	
//	public static HttpClientUtil getInstance() {
//		return getInstance(Charset.defaultCharset());
//	}
//	
//	public static HttpClientUtil getInstance(Charset charset){
//        if(instance == null){
//            instance = new HttpClientUtil();
//        }
//        instance.setCharset(charset);
//        return instance;
//    }
//	
//	public void setCharset(Charset charset) {
//		this.charset = charset;
//	}
// 
//	/**
//	 * post璇锋眰 
//	 */
//    public String doPost(String url) throws Exception {
//    	return doPost(url, null, null);
//    }
//        
//	public String doPost(String url, Map<String, Object> params) throws Exception {
//		return doPost(url, params, null);
//	}
//	
//	public String doPost(String url, Map<String, Object> params, Map<String, String> header) throws Exception {
//		String body = null;
//		try {
//			// Post璇锋眰
//			LOG.debug(" protocol: POST");
//			LOG.debug("      url: " + url);
//	        HttpPost httpPost = new HttpPost(url.trim());
//	        // 璁剧疆鍙傛暟
//	        LOG.debug("   params: " + JSON.toJSONString(params));
//	        httpPost.setEntity(new UrlEncodedFormEntity(map2NameValuePairList(params), charset));
//	        // 璁剧疆Header
//	        if (header != null && !header.isEmpty()) {
//	        	LOG.debug("   header: " + JSON.toJSONString(header));
//	        	for (Iterator<Entry<String, String>> it = header.entrySet().iterator(); it.hasNext();) {
//	        		Entry<String, String> entry = (Entry<String, String>) it.next();
//	        		httpPost.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
//	        	}
//	        }
//	        // 鍙戦�佽姹�,鑾峰彇杩斿洖鏁版嵁
//	        body = execute(httpPost);
//		} catch (Exception e) {
//			throw e;
//		}
//		LOG.debug("   result: " + body);
//		return body;
//	}
// 
//	/**
//	 * postJson璇锋眰
//	 */
//	public String doPostJson(String url, Map<String, Object> params) throws Exception {
//		return doPostJson(url, params, null);
//	}
// 
//	public String doPostJson(String url, Map<String, Object> params, Map<String, String> header) throws Exception {
//		String json = null;
//        if (params != null && !params.isEmpty()) {
//        	for (Iterator<Entry<String, Object>> it = params.entrySet().iterator(); it.hasNext();) {
//				Entry<String, Object> entry = (Entry<String, Object>) it.next();
//				Object object = entry.getValue();
//				if (object == null) {
//					it.remove();
//				}
//			}
//        	json = JSON.toJSONString(params);
//		}
//		return postJson(url, json, header);
//	}
// 
//	public String doPostJson(String url, String json) throws Exception {
//		return doPostJson(url, json, null);
//	}
// 
//	public String doPostJson(String url, String json, Map<String, String> header) throws Exception {
//		return postJson(url, json, header);
//	}
// 
//	private String postJson(String url, String json, Map<String, String> header) throws Exception {
//		String body = null;
//		try {
//			// Post璇锋眰
//			LOG.debug(" protocol: POST");
//			LOG.debug("      url: " + url);
//	        HttpPost httpPost = new HttpPost(url.trim());
//	        // 璁剧疆鍙傛暟
//	        LOG.debug("   params: " + json);
//	        httpPost.setEntity(new StringEntity(json, ContentType.DEFAULT_TEXT.withCharset(charset)));
//	        httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));
//	        LOG.debug("     type: JSON");
//	        // 璁剧疆Header
//	        if (header != null && !header.isEmpty()) {
//	        	LOG.debug("   header: " + JSON.toJSONString(header));
//	        	for (Iterator<Entry<String, String>> it = header.entrySet().iterator(); it.hasNext();) {
//	        		Entry<String, String> entry = (Entry<String, String>) it.next();
//	        		httpPost.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
//	        	}
//	        }
//	        // 鍙戦�佽姹�,鑾峰彇杩斿洖鏁版嵁
//	        body = execute(httpPost);
//		} catch (Exception e) {
//			throw e;
//		}
//		LOG.debug("  result: " + body);
//		return body;
//	}
// 
//	/**
//	 * get璇锋眰
//	 */
//	public String doGet(String url) throws Exception {
//		return doGet(url, null, null);
//	}
// 
//	public String doGet(String url, Map<String, String> header) throws Exception {
//		return doGet(url, null, header);
//	}
// 
//	public String doGet(String url, Map<String, Object> params, Map<String, String> header) throws Exception {
//		String body = null;
//		try {
//			// Get璇锋眰
//			LOG.debug("protocol: GET");
//			HttpGet httpGet = new HttpGet(url.trim());
//			// 璁剧疆鍙傛暟
//			if (params != null && !params.isEmpty()) {
//	            String str = EntityUtils.toString(new UrlEncodedFormEntity(map2NameValuePairList(params), charset));
//	            String uri = httpGet.getURI().toString();
//	            if(uri.indexOf("?") >= 0){
//	            	httpGet.setURI(new URI(httpGet.getURI().toString() + "&" + str));
//	            }else {
//	            	httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
//	            }
//			}
//			LOG.debug("     url: " + httpGet.getURI());
//			// 璁剧疆Header
//	        if (header != null && !header.isEmpty()) {
//	        	LOG.debug("   header: " + header);
//	        	for (Iterator<Entry<String, String>> it = header.entrySet().iterator(); it.hasNext();) {
//	        		Entry<String, String> entry = (Entry<String, String>) it.next();
//	        		httpGet.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
//	        	}
//	        }
//			// 鍙戦�佽姹�,鑾峰彇杩斿洖鏁版嵁
//			body =  execute(httpGet);
//		} catch (Exception e) {
//			throw e;
//		}
//		LOG.debug("  result: " + body);
//		return body;
//	}
// 
//	/**
//	 * 涓嬭浇鏂囦欢
//	 */
//	public void doDownload(String url, String path) throws Exception {
//		download(url, null, path);
//	}
//	
//	public void doDownload(String url, Map<String, Object> params, String path) throws Exception {
//		download(url, params, path);
//	}
//	
//	/**
//	 * 涓婁紶鏂囦欢
//	 */
//	public String doUpload(String url, String name, String path) throws Exception {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put(name, new File(path));
//		return doUpload(url, params);
//	}
//	
//	public String doUpload(String url, Map<String, Object> params) throws Exception {
//		String body = null;
//		// Post璇锋眰
//        HttpPost httpPost = new HttpPost(url.trim());
//        // 璁剧疆鍙傛暟
//        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
//        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//        entityBuilder.setCharset(charset);
//        if (params != null && !params.isEmpty()) {
//        	Iterator<String> it = params.keySet().iterator();
//			while (it.hasNext()) {
//				String key = it.next();
//				Object value = params.get(key);
//				if (value instanceof File) {
//					FileBody fileBody = new FileBody((File) value);
//    		        entityBuilder.addPart(key, fileBody);
//				} else {
//					entityBuilder.addPart(key, new StringBody(String.valueOf(value), ContentType.DEFAULT_TEXT.withCharset(charset)));
//				}
//			}
//        }
//        HttpEntity entity = entityBuilder.build();
//        httpPost.setEntity(entity);
//        // 鍙戦�佽姹�,鑾峰彇杩斿洖鏁版嵁
//        body = execute(httpPost);
//		return body;
//	}
//	
//	private void download(String url, Map<String, Object> params, String path) throws Exception {
//		// Get璇锋眰
//		HttpGet httpGet = new HttpGet(url.trim());
//		if (params != null && !params.isEmpty()) {
//			// 璁剧疆鍙傛暟
//			String str = EntityUtils.toString(new UrlEncodedFormEntity(map2NameValuePairList(params)));
//			String uri = httpGet.getURI().toString();
//			if (uri.indexOf("?") >= 0) {
//				httpGet.setURI(new URI(httpGet.getURI().toString() + "&" + str));
//			} else {
//				httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
//			}
//		}
//        // 鍙戦�佽姹�,涓嬭浇鏂囦欢
//        downloadFile(httpGet, path);
//	}
// 
//	private void downloadFile(HttpRequestBase requestBase, String path) throws Exception {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		try {
//			CloseableHttpResponse response = httpclient.execute(requestBase);
//			try {
//				HttpEntity entity = response.getEntity();
//				
//				if (entity != null) {
//					byte[] b = EntityUtils.toByteArray(entity);
//					OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path)));
//					out.write(b);
//					out.flush();
//					out.close();
//                }
//				EntityUtils.consume(entity);
//			} catch (Exception e) {
//				throw e;
//			} finally {
//				response.close();
//			}
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			httpclient.close();
//		}
//	}
// 
//	private String execute(HttpRequestBase requestBase) throws Exception {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		String body = null;
//		try {
//			CloseableHttpResponse response = httpclient.execute(requestBase);
//			try {
//				HttpEntity entity = response.getEntity();
//				
//				if (entity != null) {
//					body = EntityUtils.toString(entity, charset.toString());
//                }
//				EntityUtils.consume(entity);
//			} catch (Exception e) {
//				throw e;
//			}finally {
//				response.close();
//			}
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			httpclient.close();
//		}
//		return body;
//	}
//	
//	private List<NameValuePair> map2NameValuePairList(Map<String, Object> params) {
//		if (params != null && !params.isEmpty()) {
//			List<NameValuePair> list = new ArrayList<NameValuePair>();
//			Iterator<String> it = params.keySet().iterator();
//			while (it.hasNext()) {
//				String key = it.next();
//				if(params.get(key) != null) {
//					String value = String.valueOf(params.get(key));
//					list.add(new BasicNameValuePair(key, value));
//				}
//			}
//			return list;
//		}
//		return null;
//	}
//	
//	public String getRenTiFenXiToken() throws Exception {
//		Map<String, Object> params = new HashMap<>();
//		params.put("grant_type", "client_credentials");
//		params.put("client_id", "u4uAh3qQwuCkGtqgZ8ouZZ3u");
//		params.put("client_secret", "kbpkk5Typl0B48nW8r8u0tNfnOyZTaYd");
//		String ack = doPost("https://aip.baidubce.com/oauth/2.0/token", params);
//		return ack;
//	}
//	
//	public String getTuXiangShiBieToken() throws Exception {
//		Map<String, Object> params = new HashMap<>();
//		params.put("grant_type", "client_credentials");
//		params.put("client_id", "oWVT1nzHFgbjUOTBHEEurgIA");
//		params.put("client_secret", "dmst71B6iPHRbXCPoYA6gai1fEOL3NGS");
//		String ack = doPost("https://aip.baidubce.com/oauth/2.0/token", params);
//		return ack;
//	}
//	
//	public String getWenZiShiBieToken() throws Exception {
//		Map<String, Object> params = new HashMap<>();
//		params.put("grant_type", "client_credentials");
//		params.put("client_id", "PRFrDnCrCBHS0YomrwSSpmM0");
//		params.put("client_secret", "C8EAYmjOdotATNo0gp7CAOabF6bORziV");
//		String ack = doPost("https://aip.baidubce.com/oauth/2.0/token", params);
//		return ack;
//	}
//	
//	public String imageBase64(String imagePath) throws IOException {
//		File file = new File(imagePath);
//		String encrypt = Base64.encodeFile(file);
//		return encrypt;
//	}
//	
//
//	public static void main(String[] args) throws Exception {
//		
//		String helper = "Please input your test id as follows and image path\r\n"
//				+ "1:鎵嬪娍璇嗗埆\r\n"
//				+ "2:浜轰綋鍏抽敭鐐硅瘑鍒玕r\n"
//				+ "3:浜轰綋灞炴�ц瘑鍒玕r\n"
//				+ "4:浜烘祦閲忕粺璁r\n"
//				+ "5:杞﹀瀷璇嗗埆\r\n"
//				+ "6:杞︾墝璇嗗埆";
//		
//		if(args.length != 2) {
//			System.out.println(helper);
//			return;
//		}
//		
//		int id = Integer.parseInt(args[0]);
//		
//		String tokenAck = "";
//		if(id == 5) {
//			tokenAck = HttpClientUtil.getInstance().getTuXiangShiBieToken();
//		} else if(id == 6) {
//			tokenAck = HttpClientUtil.getInstance().getWenZiShiBieToken();
//		} else {
//			tokenAck = HttpClientUtil.getInstance().getRenTiFenXiToken();
//		}
//		JSONObject jsonObject = JSONObject.fromObject(tokenAck);
//		String token = jsonObject.get("access_token").toString();
//		System.out.println("token=" + token);
//		
//		String imagePath = args[1];
//		String imageBase64 = HttpClientUtil.getInstance().imageBase64(imagePath);
//		if(imageBase64.length() > 4*1024*1024) {
//			System.out.println("image base64 too big\r\n");
//			return;
//		}
//
//		Map<String, Object> params = new HashMap<>();
//		params.put("access_token", token);
//		params.put("image", imageBase64);
//		String url = "";
//		
//		switch(id) {
//			case 1:
//				url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/gesture";
//				break;
//			case 2:
//				url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/body_analysis";
//				break;
//			case 3:
//				url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/body_attr";
//				break;
//			case 4:
//				url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/body_num";
//				break;
//			case 5:
//				url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/car";
//				break;
//			case 6:
//				url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
//				break;
//			default:
//				System.out.println(helper);
//				return;
//		}
//		String imageAck = HttpClientUtil.getInstance().doPost(url, params);
//		System.out.println("imageAck=" + imageAck);
//	}
//}
