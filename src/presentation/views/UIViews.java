package src.presentation.views;

public enum UIViews {
    SIGNIN("signin"),
    SIGNUP("signup"),
    EXPLORE("explore"),
    PROFILE("profile"),
    NOTIFICATIONS("notifications"),
    IMAGEUPLOAD("imageupload"),
    HOME("home"),
    CHATS("chats"),
    SINGLE_CHAT("singlechat");


    private final String viewName;

    UIViews(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public static UIViews fromString(String viewName) {
        for (UIViews view : UIViews.values()) {
            if (view.getViewName().equals(viewName)) {
                return view;
            }
        }
        return null;
    }


}


