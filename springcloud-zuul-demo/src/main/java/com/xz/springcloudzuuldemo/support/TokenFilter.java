package com.xz.springcloudzuuldemo.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * token过滤
 * @author Huxiaozhi
 * @date 2019年9月12日 下午2:13:06
 */
@Component
public class TokenFilter extends ZuulFilter {

	/**
	 * 四种类型：pre,routing,error,post
	 * pre：主要用在路由映射的阶段是寻找路由映射表的
	 * routing:具体的路由转发过滤器是在routing路由器，具体的请求转发的时候会调用
	 * error:一旦前面的过滤器出错了，会调用error过滤器。
	 * post:当routing，error运行完后才会调用该过滤器，是在最后阶段的
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	/**
	 * 控制过滤器是否生效，可以在里面写一串逻辑来控制
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 定义过滤器执行顺序，数值越大越靠后，越小越先执行
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 执行过滤逻辑
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String token = request.getParameter("token");
		if (token == null) {
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			context.setResponseBody(HttpStatus.UNAUTHORIZED.getReasonPhrase());
			return null;
		}
		// TODO 其他业务逻辑
		return null;
	}

	
}
