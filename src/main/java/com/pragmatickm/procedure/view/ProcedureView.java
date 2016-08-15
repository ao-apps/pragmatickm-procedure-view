/*
 * pragmatickm-procedure-view - SemanticCMS view of all procedures in the current page and all children.
 * Copyright (C) 2014, 2015, 2016  AO Industries, Inc.
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

import com.aoindustries.encoding.TextInXhtmlEncoder;
import com.pragmatickm.procedure.servlet.impl.ProcedureTreeImpl;
import com.semanticcms.core.model.Page;
import com.semanticcms.core.servlet.View;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

/**
 * SemanticCMS view of all procedures in the current page and all children.
 */
public class ProcedureView extends View {

	static final String VIEW_NAME = "procedures";

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
		return VIEW_NAME;
	}

	@Override
	public String getTitle(Page page) {
		return "All Procedures" + TITLE_SEPARATOR + page.getTitle() + TITLE_SEPARATOR + page.getPageRef().getBook().getTitle();
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
	public boolean getAllowRobots(Page page) {
		return false;
	}

	@Override
	public void doView(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException, SkipPageException {
		PrintWriter out = response.getWriter();
		out.print("<h1>All Procedures in ");
		TextInXhtmlEncoder.encodeTextInXhtml(page.getTitle(), out);
		out.println("</h1>");

		ProcedureTreeImpl.writeProcedureTree(servletContext, request, response, out, page);
	}
}
