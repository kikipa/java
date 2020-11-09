package threads.patterns.immutable;

public final class MSInfo {

    private final String deviceId;

    private final String url;

    private final int maxAttachmentSizeInBytes;

    public MSInfo(String deviceId, String url, int maxAttachmentSizeInBytes){
        this.deviceId = deviceId;
        this.url = url;
        this.maxAttachmentSizeInBytes = maxAttachmentSizeInBytes;
    }

    public MSInfo(MSInfo msInfo) {
        this.deviceId = msInfo.deviceId;
        this.url = msInfo.url;
        this.maxAttachmentSizeInBytes = msInfo.maxAttachmentSizeInBytes;
    }

    public String getDeviceId(){
        return deviceId;
    }

    public String getUrl(){
        return url;
    }

    public int getMaxAttachmentSizeInBytes(){
        return maxAttachmentSizeInBytes;
    }
}
