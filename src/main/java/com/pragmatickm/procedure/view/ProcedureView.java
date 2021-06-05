/*
 * pragmatickm-procedure-view - SemanticCMS view of all procedures in the current page and all children.
 * Copyright (C) 2014, 2015, 2016, 2017, 2020, 2021  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of pragmatickm-procedure-view.
 *
 * pragmatickm-procedure-view is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * pragmatickm-procedure-view is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with pragmatickm-procedure-view.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pragmatickm.procedure.view;

import com.aoapps.html.servlet.FlowContent;
import com.pragmatickm.procedure.model.Procedure;
import com.pragmatickm.procedure.servlet.impl.ProcedureTreeImpl;
import com.semanticcms.core.model.Page;
import com.semanticcms.core.servlet.PageUtils;
import com.semanticcms.core.servlet.SemanticCMS;
import com.semanticcms.core.servlet.View;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

/**
 * SemanticCMS view of all procedures in the current page and all children.
 */
public class ProcedureView extends View {

	public static final String NAME = "procedures";

	@WebListener("Registers the \"" + NAME + "\" view in SemanticCMS.")
	public static class Initializer implements ServletContextListener {
		@Override
		public void contextInitialized(ServletContextEvent event) {
			SemanticCMS.getInstance(event.getServletContext()).addView(new ProcedureView());
		}
		@Override
		public void contextDestroyed(ServletContextEvent event) {
			// Do nothing
		}
	}

	private ProcedureView() {}

	@Override
	public Group getGroup() {
		return Group.VARIABLE;
	}

	@Override
	public String getDisplay() {
		return "Procedures";
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isApplicable(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		return PageUtils.hasElement(servletContext, request, response, page, Procedure.class, true);
	}

	@Override
	public String getTitle(
		ServletContext servletContext,
		HttpServletRequest request,
		HttpServletResponse response,
		Page page
	) {
		String bookTitle = page.getPageRef().getBook().getTitle();
		if(bookTitle != null && !bookTitle.isEmpty()) {
			return "All Procedures" + TITLE_SEPARATOR + page.getTitle() + TITLE_SEPARATOR + bookTitle;
		} else {
			return "All Procedures" + TITLE_SEPARATOR + page.getTitle();
		}
	}

	@Override
	public String getDescription(Page page) {
		return null;
	}

	@Override
	public String getKeywords(Page page) {
		return null;
	}

	/**
	 * This view does not provide additional information unobtainable from source content,
	 * exclude from search indexes.
	 */
	@Override
	public boolean getAllowRobots(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) {
		return false;
	}

	@Override
	public <__ extends FlowContent<__>> void doView(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, __ flow, Page page) throws ServletException, IOException, SkipPageException {
		flow.h1__(h1 -> h1
			.text("All Procedures in ").text(page.getTitle())
		);

		ProcedureTreeImpl.writeProcedureTree(servletContext, request, response, flow, page);
	}
}
