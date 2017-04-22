package pl.java.scalatech.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SimpleFilter extends ZuulFilter {

	private static final String S_REQUEST_TO_S = "%s request to %s";
	private static final String PRE = "pre";

	@Override
	public String filterType() {
		return PRE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format(S_REQUEST_TO_S, request.getMethod(), request.getRequestURL().toString()));
		return null;
	}

}
