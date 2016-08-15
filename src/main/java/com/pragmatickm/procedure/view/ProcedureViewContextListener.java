/*
 * pragmatickm-procedure-view - SemanticCMS view of all procedures in the current page and all children.
 * Copyright (C) 2016  AO Industries, Inc.
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

import com.semanticcms.core.servlet.SemanticCMS;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Registers the \"" + ProcedureView.VIEW_NAME + "\" view in SemanticCMS.")
public class ProcedureViewContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		SemanticCMS.getInstance(event.getServletContext()).addView(new ProcedureView());
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}
}
