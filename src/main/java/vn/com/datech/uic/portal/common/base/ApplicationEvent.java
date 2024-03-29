/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.base;

/**
 * The type Application event.
 */
public class ApplicationEvent {
    /**
     * The interface On create.
     */
    public interface OnCreate { }

    /**
     * The interface On update.
     */
    public interface OnUpdate { }

    /**
     * The interface On delete.
     */
    public interface OnDelete { }

    /**
     * The interface On search.
     */
    public interface OnSearch { }

    /**
     * The type On admin view.
     */
    public static class OnAdminView extends OnUserView { }

    /**
     * The type On user view.
     */
    public static class OnUserView extends OnNormalView { }

    /**
     * The type On normal view.
     */
    public static class OnNormalView { }
}
