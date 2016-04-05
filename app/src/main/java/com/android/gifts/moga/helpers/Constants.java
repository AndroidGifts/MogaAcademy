package com.android.gifts.moga.helpers;

/**
 * Created by Mohamed Fareed on 3/22/2016.
 */
public class Constants {
    public static final String PREFERENCE_NAME = "mogaPrefs";
    public static final String USER_PREF_KEY = "userPref";

    public static final String NEWS_KEY = "news";
    public static final String SCHEDULES = "schedules";

    public static final String USER_DEVICE_ID = "device ID";

    public static final String MOBILE_PATTERN = "(^01[0-2]{1}[0-9]{8}$)|(^\u0660\u0661[\u0660-\u0662]{1}[\u0660-\u0669]{8}$)";
    public static final String REGULAR_FONT = "fonts/DroidKufi-Regular.ttf";
    public static final String BOLD_FONT = "fonts/DroidKufi-Bold.ttf";

    // flag to identify whether to show single line
    // or multi line text in push notification tray
    public static boolean appendNotificationMessages = true;

    // broadcast receiver intent filters
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification try
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    // All Shared Preferences Keys
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_NOTIFICATIONS = "notifications";
}
