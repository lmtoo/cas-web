package cn.accessbright.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jasig.cas.client.util.AssertionHolder;

public class AutoSetUserAdapterFilter implements Filter {
	public static ThreadLocal<String> usernameHolder = new ThreadLocal<String>();

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean login = usernameHolder.get() != null;
		try {
			if (login == false) {
				String username = AssertionHolder.getAssertion().getPrincipal().getName();
				usernameHolder.set(username);
			}
			System.out.println("username:" + usernameHolder.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
