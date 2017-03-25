package pl.java.scalatech.zuul;

import static java.nio.charset.Charset.forName;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityZuulFilter extends ZuulFilter {

	private static final Object NULL = new Object();
	private static final String USERNAME = "admin"; // TODO
	private static final String PASSWORD = "admin";// TODO
	private static final String AUTHORIZATION_HEADER = "Basic "
			+ encodeBase64String((USERNAME + ":" + PASSWORD).getBytes(forName("UTF-8")));

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		final RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader("Authorization", AUTHORIZATION_HEADER);
		log.trace("{}:{} (uri: {})", "Authorization", AUTHORIZATION_HEADER, ctx.getRequest().getRequestURI());
		return NULL;
	}

}