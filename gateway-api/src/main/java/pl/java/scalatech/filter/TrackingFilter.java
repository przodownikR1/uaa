package pl.java.scalatech.filter;

import static pl.java.scalatech.filter.FilterUtils.PRE_FILTER_TYPE;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
class TrackingFilter extends ZuulFilter {
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;

	private final FilterUtils filterUtils;

	@Override
	public String filterType() {
		return PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	private boolean isCorrelationIdPresent() {
		if (filterUtils.getCorrelationId() != null) {
			return true;
		}

		return false;
	}

	private String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}

	public Object run() {

		if (isCorrelationIdPresent()) {
			log.debug("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
		} else {
			filterUtils.setCorrelationId(generateCorrelationId());
			log.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
		}

		RequestContext ctx = RequestContext.getCurrentContext();
		log.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());
		return null;
	}
}