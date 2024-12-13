public class UserInfo {

    String userUUID;
    String userLongUrl;
    String userShortUrl;
    int usageCounter;

    public UserInfo(){
        this.userUUID = userUUID;
        this.userLongUrl = userLongUrl;
        this.userShortUrl = userShortUrl;
        this.usageCounter = usageCounter;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUserLongUrl() {
        return userLongUrl;
    }

    public void setUserLongUrl(String userLongUrl) {
        this.userLongUrl = userLongUrl;
    }

    public String getUserShortUrl() {
        return userShortUrl;
    }

    public void setUserShortUrl(String userShortUrl) {
        this.userShortUrl = userShortUrl;
    }

    public int getUsageCounter() {
        return usageCounter;
    }

    public void setUsageCounter(int usageCounter) {
        this.usageCounter = usageCounter;
    }
}
