/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.pacl.PACLExecutionTestListener;
import com.liferay.portal.security.pacl.PACLIntegrationJUnitTestRunner;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.blogs.service.BlogsEntryLocalService;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@ExecutionTestListeners(listeners = {PACLExecutionTestListener.class})
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class ClassLoaderTest {

	@Test
	public void create1() throws Exception {
		try {
			new URLClassLoader(new URL[0], getClass().getClassLoader());

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get1() throws Exception {
		try {
			BlogsEntryLocalService blogsEntryLocalService =
				BlogsEntryLocalServiceUtil.getService();

			Class<?> clazz = blogsEntryLocalService.getClass();

			clazz.getClassLoader();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get2() throws Exception {
		try {
			ClassLoader.getSystemClassLoader();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get3() throws Exception {
		try {
			User defaultUser = UserLocalServiceUtil.getDefaultUser(
				TestPropsValues.getCompanyId());

			defaultUser.toEscapedModel();
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void get4() throws Exception {
		try {
			BeanPropertyTest.class.getClassLoader();
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void get5() throws Exception {
		try {
			Class<?> clazz = getClass();

			clazz.getClassLoader();
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void get6() throws Exception {
		try {
			Object.class.getClassLoader();
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void get7() throws Exception {
		try {
			Object object = new Object();

			Class<?> clazz = object.getClass();

			clazz.getClassLoader();
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void get8() throws Exception {
		try {
			Portal portal = PortalUtil.getPortal();

			Class<?> clazz = portal.getClass();

			clazz.getClassLoader();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get9() throws Exception {
		try {
			PortalClassLoaderUtil.getClassLoader();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get10()
		throws Exception {

		try {
			PortalRuntimePermission.checkGetClassLoader("");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get11()
		throws Exception {

		try {
			PortalRuntimePermission.checkGetClassLoader("flash-portlet");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void get12()
		throws Exception {

		try {
			PortalRuntimePermission.checkGetClassLoader(null);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get13()
		throws Exception {

		try {
			PortalRuntimePermission.checkGetClassLoader("portal");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get14()
		throws Exception {

		try {
			PortalRuntimePermission.class.getClassLoader();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get15()
		throws Exception {

		try {
			PortletClassLoaderUtil.getClassLoader("1_WAR_chatportlet");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get16()
		throws Exception {

		try {
			PortletClassLoaderUtil.getClassLoader("1_WAR_flashportlet");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void get17()
		throws Exception {

		try {
			PortletClassLoaderUtil.getClassLoader("chat-portlet");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get18()
		throws Exception {

		try {
			PortletClassLoaderUtil.getClassLoader("flash-portlet");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void get19() throws Exception {
		try {
			Thread thread = Thread.currentThread();

			thread.getContextClassLoader();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get20() throws Exception {
		try {
			User.class.getClassLoader();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void get21() throws Exception {
		try {
			UserLocalService userLocalService =
				UserLocalServiceUtil.getService();

			Class<?> clazz = userLocalService.getClass();

			clazz.getClassLoader();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

}